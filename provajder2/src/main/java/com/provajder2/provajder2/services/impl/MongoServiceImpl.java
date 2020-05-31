package com.provajder2.provajder2.services.impl;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.provajder2.provajder2.mongoConfig.MongoConfiguracija;
import com.provajder2.provajder2.services.MongoService;
import org.bson.Document;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;



@Service
public class MongoServiceImpl implements MongoService {
    @Override
    public String test(String query) {
        System.out.println("RADI MONGO");
        //SELECT DR_IDENTIFIKATOR,NM_NAZIV FROM POPULATED_PLACES WHERE DR_IDENTIFIKATOR="srb" AND NM_PTT_CODE>20000            NM_IDENTIFIKATOR>2
        String[] reci = query.split(" ");
        String kolekcija = reci[3];
        String kolone = reci[1];
        String conditionAtrubut = reci[5].split(">")[0];
        String conditionValue = reci[5].split(">")[1];
        MongoDatabase db = MongoConfiguracija.getConnection().getDatabase("tim_402_1_mongo_si2019");
        MongoCollection<Document> collection = db.getCollection(kolekcija, Document.class);
        /*FindIterable<BasicDBObject> find = collection.find();

        for(BasicDBObject f : find){
            mapa.get("ULO_OZNAKA").add(f.getString("ULO_OZNAKA"));
            mapa.get("ULO_NAZIV").add(f.getString("ULO_NAZIV"));
        }*/
        MongoCursor<Document> cursor = collection.find().iterator();
        String json = "";
        try {
            Document doc;
            while (cursor.hasNext()) {
                doc = cursor.next();
                if(Integer.parseInt(doc.get(conditionAtrubut).toString())>Integer.parseInt(conditionValue)){
                    if(kolone.equals("*"))
                        json = doc.toJson();
                    else{
                        int i=0;
                        for(Map.Entry<String,Object> set : doc.entrySet()){
                            if(kolone.contains(set.getKey())) {
                                json += set.toString();
                                if (i < doc.size() - 1) {
                                    json += ", ";
                                }
                            }
                            i++;
                        }
                    }
                    if(cursor.hasNext())
                        json += ",";
                }
                //json += cursor.next().get();
            }
        } finally {
            cursor.close();
        }
        System.out.println("JSON JE " + json);
        return json;
    }
}
