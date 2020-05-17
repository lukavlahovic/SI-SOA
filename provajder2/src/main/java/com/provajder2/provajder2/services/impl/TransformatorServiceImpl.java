package com.provajder2.provajder2.services.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.provajder2.provajder2.bootstrap.Bootstrap;
import com.provajder2.provajder2.mongoConfig.MongoConfiguracija;
import com.provajder2.provajder2.services.TransformatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.persistence.Basic;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TransformatorServiceImpl implements TransformatorService {

    private Bootstrap bootstrap;

    @Autowired
    public TransformatorServiceImpl(Bootstrap bootstrap){
        this.bootstrap = bootstrap;
    }
    @Override
    public Boolean transform(String baza) { //mongo;{role:ULO_OZNAKA,ULO_NAZIV};
        String[] s = baza.split(";");
        String tip = s[0];
        String[] s1 = s[1].split("!");
        MongoDatabase db = MongoConfiguracija.getConnection().getDatabase("tim_402_1_mongo_si2019");
        for(String s2:s1){
            s2 = s2.replace("{","");
            s2 = s2.replace("}","");
            String[] tabelaPolja = s2.split(":");
            String tabela = tabelaPolja[0];
            String polja = tabelaPolja[1];
            if(!db.listCollectionNames()
                    .into(new ArrayList<String>()).contains(tabela)){
                System.out.println("TRANSFORMACIJA " + polja);
                MongoCollection<BasicDBObject> collection = db.getCollection(tabela, BasicDBObject.class);
                String command = "SELECT " + polja + " FROM " + tabela.toUpperCase();

                List results = bootstrap.getTemplate().queryForList(command);
                for(Object result:results){
                    Map map = (Map) result;
                    map.forEach((key, value) -> {
                        BasicDBObject document = new BasicDBObject((String)key,value);
                        collection.insertOne(document);
                    });
                }
            }
            else{
                System.out.println("VEC POSTOJI");
            }
        }
        return true;
    }
}
