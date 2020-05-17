package com.provajder2.provajder2.services.impl;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.provajder2.provajder2.mongoConfig.MongoConfiguracija;
import com.provajder2.provajder2.services.MongoService;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MongoServiceImpl implements MongoService {
    @Override
    public Boolean test() {
        //login
        /*CloseableHttpClient client1 = HttpClients.createDefault();
        HttpPost httpPost1 = new HttpPost("http://localhost:8080/login");
        String json = "{\"username\":\"provajder2\",\"password\":\"123\"}";
        CloseableHttpResponse response1 = null;
        try {
            StringEntity entity = new StringEntity(json);
            httpPost1.setEntity(entity);
            response1 = client1.execute(httpPost1);
            client1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String token = "";
        for (Header header: response1.getAllHeaders()) {
            if (header.getName().equalsIgnoreCase("authorization")) {
                token = header.getValue();
            }
        }
        System.out.println(token);


        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://localhost:8080/provajder2/service/transform");
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");
        httpPost.setHeader("Authorization", token);
        CloseableHttpResponse response = null;
        try {
            response = client.execute(httpPost);
            String result = EntityUtils.toString(response.getEntity());
            System.out.println("ODGOVOR TRANSFORMATORA " + result);
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        MongoClient mongoClient = MongoConfiguracija.getConnection();
        for(String s:mongoClient.listDatabaseNames())
            mongoClient.getDatabase(s);
        mongoClient.close();*/
        System.out.println("RADI MONGO");
        return true;
    }
}
