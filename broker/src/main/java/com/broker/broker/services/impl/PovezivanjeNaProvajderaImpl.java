package com.broker.broker.services.impl;

import com.broker.broker.domain.*;
import com.broker.broker.filter.Filter;
import com.broker.broker.mappers.EndpointMapper;
import com.broker.broker.repository.*;
import com.broker.broker.services.LoggerServis;
import com.broker.broker.services.PovezivanjeNaProvajdera;
import com.broker.broker.state.Context;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class PovezivanjeNaProvajderaImpl implements PovezivanjeNaProvajdera {

    private EndpointMapper endpointMapper;
    private EndpointRepository endpointRepository;
    private ProvajderRepository provajderRepository;
    private ServisRepository servisRepository;
    private SlozenRepository slozenRepository;
    private Context context;
    private LoggerServis loggerServis;
    private UserRepository userRepository;
    private Filter filter;

    public PovezivanjeNaProvajderaImpl(EndpointMapper endpointMapper, EndpointRepository endpointRepository,ProvajderRepository provajderRepository,
                                       ServisRepository servisRepository,SlozenRepository slozenRepository,
                                       LoggerServis loggerServis,UserRepository userRepository){
        this.endpointMapper = endpointMapper;
        this.endpointRepository = endpointRepository;
        this.provajderRepository = provajderRepository;
        this.servisRepository = servisRepository;
        this.slozenRepository = slozenRepository;
        this.context = new Context(servisRepository);
        this.loggerServis = loggerServis;
        this.userRepository = userRepository;
        this.filter = new Filter();
    }

    @Override
    public ResponseEntity<Object> pozoviProvajdera(String username, String servis, String ruta, Map<String,Object> map, String account) {
        System.out.println("USAO U FUNKCIJU");
        Provajder provajder = provajderRepository.findByUsername(username);
        UserBroker userBroker = userRepository.findByUsername(account);
        //provera da li je slozen servis
        SlozenServis slozenServis = slozenRepository.findByNameAndProvajderS(servis,provajder);

        if(slozenServis!=null){
            System.out.println("USAO U SLOZENI");
            CloseableHttpClient client = HttpClients.createDefault();
            CloseableHttpResponse response = null;
            ObjectMapper objectMapper = new ObjectMapper();
            Object o = null;
            int i=0;
            String tip = "";
            for(Servis s : slozenServis.getListaServisa()){
                if(!tip.equals(s.getTip()) && context.isAvailable(s.getProvajder().getHost() + s.getRuta())){
                    context.changeState(s.getProvajder().getHost() + s.getRuta());
                    tip = s.getTip();
                    Endpoint endpoint = s.getListaEndpointa().get(0);
                    String url = "http://" + s.getProvajder().getHost() + s.getRuta() + endpoint.getRuta();

                    if(loggerServis.upisi(userBroker,userBroker.getRoles(),endpoint))
                    {
                        System.out.println("URL JE " + url);
                        if(endpoint.getZahtev().equals("POST"))
                        {
                            HttpPost httpPost = new HttpPost(url);
                            try {
                                if(i==0){
                                    String json = objectMapper.writeValueAsString(map);
                                    StringEntity entity = new StringEntity(json);
                                    httpPost.setEntity(entity);
                                    httpPost.setHeader("Accept", "application/json");
                                    httpPost.setHeader("Content-type", "application/json");
                                }
                                if (i > 0) {
                                    String json = o.toString();
                                    StringEntity entity = new StringEntity(json);
                                    httpPost.setEntity(entity);
                                    httpPost.setHeader("Accept", "application/json");
                                    httpPost.setHeader("Content-type", "application/json");
                                }
                                if(endpoint.getTransformatorZa()!=null){
                                    for(String id : endpoint.getTransformatorZa().split(",")){
                                        Endpoint tmp = endpointRepository.findById(Long.parseLong(id)).get();
                                        System.out.println("BAZA ENDPOINTA " + tmp.getBaza());
                                        String[] s1 = tmp.getBaza().split(";");
                                        String baza = s1[0] + ";" + s1[1];
                                        httpPost.setHeader("baza",baza);
                                        response = client.execute(httpPost);
                                    }
                                }
                                else {
                                    response = client.execute(httpPost);
                                }
                                context.changeState(s.getProvajder().getHost() + s.getRuta());// da bi testirali zakomentarisati
                                String result = EntityUtils.toString(response.getEntity());
                                if (endpoint.getOutput().equals("json")) {
                                    Map<String, String> mapa = objectMapper.readValue(result, Map.class);
                                    o = filter.filterByRole(mapa,endpoint,userBroker);
                                } else {
                                    o = result;
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }else{
                            HttpGet httpGet = new HttpGet(url);
                            if(i>0){
                                String json = o.toString();
                                httpGet.setHeader("podaci",json);
                                httpGet.setHeader("Accept", "application/json");
                                httpGet.setHeader("Content-type", "application/json");
                            }
                            try {
                                response = client.execute(httpGet);
                                context.changeState(s.getProvajder().getHost() + s.getRuta());// da bi testirali zakomentarisati
                                String result = EntityUtils.toString(response.getEntity());
                                if (endpoint.getOutput().equals("json")) {
                                    Map<String, String> mapa = objectMapper.readValue(result, Map.class);
                                    o = filter.filterByRole(mapa,endpoint,userBroker);
                                } else {
                                    o = result;
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        i++;
                    }
                }
            }
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return new ResponseEntity<Object>(o, HttpStatus.OK);
        }
        else {
            //sastavim http zahtev za provajdera, return je response od provajdera
            System.out.println("USAO ZA KORISNIKA " + userBroker.getUsername());
            String url = "http://" + provajder.getHost() + servisRepository.findByNameAndProvajder(servis, provajder).getRuta();//localhost:8081 + /teski
            Endpoint endpoint = endpointRepository.findByRuta("/" + ruta);
            url += endpoint.getRuta(); //localhost:8081/api/teski + /add
            if(loggerServis.upisi(userBroker,userBroker.getRoles(),endpoint)) {
                CloseableHttpClient client = HttpClients.createDefault();
                if (endpoint.getZahtev().equals("POST")) {
                    String query = null;
                    boolean hasRezim = false;
                    if(map!=null) {
                        for (Map.Entry<String, Object> entry : map.entrySet()) {
                            if (entry.getKey().equals("rezim")) {
                                hasRezim = true;
                            }
                        }
                    }
                    if(endpoint.getBaza()!=null){
                        if(!hasRezim||((String)map.get("rezim")).equals("1")) {
                            System.out.println("USAO ZA TRANSFORMATORA");
                            String[] s = endpoint.getBaza().split(";");
                            String baza;
                            String urlTransformator;
                            if (map.containsKey("sablon")) {
                                baza = s[0];
                                urlTransformator = s[1];
                            } else {
                                baza = s[0] + s[1];
                                urlTransformator = s[3];
                            }
                            HttpPost httpPost = new HttpPost(urlTransformator);
                            httpPost.setHeader("baza", baza);
                            if (map.containsKey("sablon")) {
                                httpPost.setHeader("sablon", (String) map.get("sablon"));
                            } else {
                                query = s[3];
                            }
                            try {
                                CloseableHttpResponse response = client.execute(httpPost);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    HttpPost httpPost = new HttpPost(url);
                    ObjectMapper objectMapper = new ObjectMapper();
                    String json = null;
                    try {
                        json = objectMapper.writeValueAsString(map);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                    try {
                        StringEntity entity = new StringEntity(json);
                        httpPost.setEntity(entity);
                        httpPost.setHeader("Accept", "application/json");
                        httpPost.setHeader("Content-type", "application/json");
                        if(query!=null) {
                            httpPost.setHeader("query", query);
                        }
                        if(hasRezim){
                            httpPost.setHeader("rezim",(String)map.get("rezim"));
                            if(((String)map.get("rezim")).equals("2")){
                                query = "";
                                query += (String)map.get("izvestaj") + ";";
                                query += (String)map.get("kolone") + ";";
                                query += (String)map.get("filter") + ";";
                                query += (String)map.get("sort");
                                httpPost.setHeader("query",query);
                            }
                        }
                        CloseableHttpResponse response = client.execute(httpPost);
                        String result = EntityUtils.toString(response.getEntity());
                        System.out.println(result);
                        Object o = null;
                        if (endpoint.getOutput().equals("json")) {
                            Map<String, String> mapa = objectMapper.readValue(result, Map.class);
                            o = filter.filterByRole(mapa,endpoint,userBroker);
                        } else {
                            o = result;
                        }
                        client.close();
                        return new ResponseEntity<Object>(o, HttpStatus.OK);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    } catch (ClientProtocolException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (endpoint.getZahtev().equals("GET")) {
                    System.out.println("MAPA JE " + map.toString());
                    HttpGet httpGet = new HttpGet(url);
                    ObjectMapper objectMapper = new ObjectMapper();
                    String json = (String) map.get("podaci");
                    try {
                        httpGet.setHeader("podaci", json);
                        httpGet.setHeader("Accept", "application/json");
                        httpGet.setHeader("Content-type", "application/json");
                        CloseableHttpResponse response = client.execute(httpGet);
                        String result = EntityUtils.toString(response.getEntity());
                        System.out.println(result);
                        Object o = null;
                        if (endpoint.getOutput().equals("json")) {
                            Map<String, String> mapa = objectMapper.readValue(result, Map.class);
                            o = filter.filterByRole(mapa,endpoint,userBroker);
                        } else {
                            o = result;
                        }
                        client.close();
                        return new ResponseEntity<Object>(o, HttpStatus.OK);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    } catch (ClientProtocolException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return null;
    }
}