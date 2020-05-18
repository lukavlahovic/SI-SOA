package com.provajder2.provajder2.services.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.provajder2.provajder2.mongoConfig.MongoConfiguracija;
import com.provajder2.provajder2.services.MongoService;
import org.bson.Document;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@Service
public class MongoServiceImpl implements MongoService {
    @Override
    public String test() {
        System.out.println("RADI MONGO");
        Map<String, ArrayList<String>> mapa = new HashMap<>();
        mapa.put("ULO_OZNAKA", new ArrayList<>());
        mapa.put("ULO_NAZIV", new ArrayList<>());
        MongoDatabase db = MongoConfiguracija.getConnection().getDatabase("tim_402_1_mongo_si2019");
        MongoCollection<Document> collection = db.getCollection("role", Document.class);
        /*FindIterable<BasicDBObject> find = collection.find();

        for(BasicDBObject f : find){
            mapa.get("ULO_OZNAKA").add(f.getString("ULO_OZNAKA"));
            mapa.get("ULO_NAZIV").add(f.getString("ULO_NAZIV"));
        }*/
        MongoCursor<Document> cursor = collection.find().iterator();
        String json = "";
        try {
            while (cursor.hasNext()) {
                json += cursor.next().toJson();
                if(cursor.hasNext())
                    json += ",";
            }
        } finally {
            cursor.close();
        }
        System.out.println("JSON JE " + json);
        return json;
    }
}
