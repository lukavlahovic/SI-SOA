package com.broker.broker.services.impl;

import com.broker.broker.domain.Endpoint;
import com.broker.broker.domain.Provajder;
import com.broker.broker.mappers.EndpointMapper;
import com.broker.broker.model.Podaci;
import com.broker.broker.repository.EndpointRepository;
import com.broker.broker.repository.ProvajderRepository;
import com.broker.broker.repository.ServisRepository;
import com.broker.broker.services.PovezivanjeNaProvajdera;
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

    public PovezivanjeNaProvajderaImpl(EndpointMapper endpointMapper, EndpointRepository endpointRepository,ProvajderRepository provajderRepository,
                                       ServisRepository servisRepository){
        this.endpointMapper = endpointMapper;
        this.endpointRepository = endpointRepository;
        this.provajderRepository = provajderRepository;
        this.servisRepository = servisRepository;
    }

    @Override
    public ResponseEntity<Object> pozoviProvajdera(String username, String servis, String ruta, Map<String,Object> map) {
        //sastavim http zahtev za provajdera, return je response od provajdera
        Provajder provajder = provajderRepository.findByUsername(username);
        String url = "http://" + provajder.getHost() + servisRepository.findByNameAndProvajder(servis,provajder).getRuta();//localhost:8081 + /teski
        Endpoint endpoint = endpointRepository.findByRuta("/" + ruta);
        url += endpoint.getRuta(); //localhost:8081/api/teski + /add
        CloseableHttpClient client = HttpClients.createDefault();
        if(endpoint.getZahtev().equals("POST")){
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
                //System.out.println("Output from Server .... \n");
                output = br.readLine();
                Object o = Boolean.parseBoolean(output);
                client.close();
                return new ResponseEntity<Object>(o,HttpStatus.OK);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(endpoint.getZahtev().equals("GET")){
            System.out.println("MAPA JE " + map.toString());
            HttpGet httpPost = new HttpGet(url);
            ObjectMapper objectMapper = new ObjectMapper();
            String json = null;
            try {
                json = objectMapper.writeValueAsString(map);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

            try {
                StringEntity entity = new StringEntity(json);
                for(String kljuc : map.keySet()){
                    httpPost.setHeader(kljuc,(String)map.get(kljuc));
                }
                httpPost.setHeader("Accept", "application/json");
                httpPost.setHeader("Content-type", "application/json");
                CloseableHttpResponse response = client.execute(httpPost);
                String result = EntityUtils.toString(response.getEntity());
                Map<String,String> mapa = objectMapper.readValue(result,Map.class);


                Object o = mapa;
                client.close();
                return new ResponseEntity<Object>(o,HttpStatus.OK);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        return null;
    }
}
