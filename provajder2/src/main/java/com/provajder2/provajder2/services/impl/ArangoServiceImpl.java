package com.provajder2.provajder2.services.impl;

import com.arangodb.ArangoCursor;
import com.arangodb.ArangoDB;
import com.arangodb.ArangoDatabase;
import com.arangodb.entity.BaseDocument;
import com.provajder2.provajder2.arangoConfig.ArangoConfiguracija;
import com.provajder2.provajder2.services.ArangoService;
import org.bson.Document;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ArangoServiceImpl implements ArangoService {
    @Override
    public String test(String query) {
        System.out.println("RADI ARANDJKp");
        //SELECT DR_IDENTIFIKATOR,NM_NAZIV FROM POPULATED_PLACES WHERE NM_PTT_CODE>20000            NM_IDENTIFIKATOR>2
        String[] reci = query.split(" ");
        String kolekcija = reci[3];
        String kolone = reci[1];
        String returnString = "a";
        if(!kolone.equals("*"))
        {
           String[] koloneMulty = kolone.split(",");
           returnString="{";
           int j= koloneMulty.length;
           int i = 0;
           for(String kolona: koloneMulty){
               returnString+=(kolona+":a."+kolona);
               if(i<j-1)
                   returnString+=",";
               i++;
           }
           returnString+="}";
        }
        System.out.println(returnString);
        String conditionAtrubut = reci[5].split(">")[0];
        String conditionValue = reci[5].split(">")[1];
        ArangoDB arango = ArangoConfiguracija.getConnection();
        ArangoDatabase db = arango.db("tim_402_1_arango_si2019");
        ArangoCursor<BaseDocument> cursor = db.query(
        "for a in "+kolekcija +
                "    filter TO_NUMBER(a." +conditionAtrubut+")>"+conditionValue+
                "    return "+returnString, BaseDocument.class
        );
        String json ="";
        while(cursor.hasNext()){
            json+=cursor.next().getProperties().entrySet().toString()+",";
        }
        json=json.substring(0,json.length()-1);

        return json;
    }
}
