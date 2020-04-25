package com.broker.broker.services.impl;

import com.broker.broker.domain.Endpoint;
import com.broker.broker.domain.Provajder;
import com.broker.broker.domain.Servis;
import com.broker.broker.domain.SlozenServis;
import com.broker.broker.mappers.EndpointMapper;
import com.broker.broker.model.Podaci;
import com.broker.broker.repository.EndpointRepository;
import com.broker.broker.repository.ProvajderRepository;
import com.broker.broker.repository.ServisRepository;
import com.broker.broker.repository.SlozenRepository;
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
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Map;

@Service
public class PovezivanjeNaProvajderaImpl implements PovezivanjeNaProvajdera {

    private EndpointMapper endpointMapper;
    private EndpointRepository endpointRepository;
    private ProvajderRepository provajderRepository;
    private ServisRepository servisRepository;
    private SlozenRepository slozenRepository;
    private Context context;

    public PovezivanjeNaProvajderaImpl(EndpointMapper endpointMapper, EndpointRepository endpointRepository,ProvajderRepository provajderRepository,
                                       ServisRepository servisRepository,SlozenRepository slozenRepository){
        this.endpointMapper = endpointMapper;
        this.endpointRepository = endpointRepository;
        this.provajderRepository = provajderRepository;
        this.servisRepository = servisRepository;
        this.slozenRepository = slozenRepository;
        this.context = new Context(servisRepository);
    }

    @Override
    public ResponseEntity<Object> pozoviProvajdera(String username, String servis, String ruta, Map<String,Object> map) {
        Provajder provajder = provajderRepository.findByUsername(username);
        //provera da li je slozen servis
        SlozenServis slozenServis = slozenRepository.findByNameAndProvajderS(servis,provajder);
        if(slozenServis!=null){
            CloseableHttpClient client = HttpClients.createDefault();
            CloseableHttpResponse response;
            ObjectMapper objectMapper = new ObjectMapper();
            for(Servis s : slozenServis.getListaServisa()){
                if(context.isAvailable(provajder.getHost() + s.getRuta())){
                    Endpoint endpoint = s.getListaEndpointa().get(0);
                    String url = "http://" + provajder.getHost() + s.getRuta() + endpoint.getRuta();
                    System.out.println("URL JE " + url);
                    HttpPost httpPost = new HttpPost(url);
                    try {
                        response = client.execute(httpPost);
                        String result = EntityUtils.toString(response.getEntity());
                        System.out.println("RESULT JE " + result);
                        Map<String, String> mapa = objectMapper.readValue(result, Map.class);//ovo je visak treba da postoji samo ako je neka mapa ili json neka kolekcija, kao je samo boolean ili slicno onda samo String result
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        else {
            //sastavim http zahtev za provajdera, return je response od provajdera
            String url = "http://" + provajder.getHost() + servisRepository.findByNameAndProvajder(servis, provajder).getRuta();//localhost:8081 + /teski
            Endpoint endpoint = endpointRepository.findByRuta("/" + ruta);
            url += endpoint.getRuta(); //localhost:8081/api/teski + /add
            CloseableHttpClient client = HttpClients.createDefault();
            if (endpoint.getZahtev().equals("POST")) {
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
                    CloseableHttpResponse response = client.execute(httpPost);
                    BufferedReader br = new BufferedReader(
                            new InputStreamReader((response.getEntity().getContent())));
                    String output;
                    output = br.readLine();
                    Object o = Boolean.parseBoolean(output);
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
                    Map<String, String> mapa = objectMapper.readValue(result, Map.class);
                    System.out.println("MAPA U GETU " + mapa.toString());
                    Object o = mapa;
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

        return null;
    }
}
