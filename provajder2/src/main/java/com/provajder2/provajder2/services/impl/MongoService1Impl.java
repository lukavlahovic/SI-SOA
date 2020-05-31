package com.provajder2.provajder2.services.impl;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.provajder2.provajder2.mongoConfig.MongoConfiguracija;
import com.provajder2.provajder2.services.MongoService1;
import org.bson.Document;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

import static com.mongodb.client.model.Filters.regex;

@Service
public class MongoService1Impl implements MongoService1 {

    private MongoDatabase dbMongo;

    @Override
    public Map<String,String> test(String query, String rezim) {
        Map<String,String> map = new LinkedHashMap<>();
        if(rezim.equals("1")){
            System.out.println("REZIM GENERISANJA");
            map.put("rezultat","true");
            return map;
        }
        if(rezim.equals("2")){
            System.out.println("REZIM STAMPANJA");
            System.out.println("QUERY JE " + query);
            dbMongo = MongoConfiguracija.getConnection().getDatabase("tim_402_1_mongo_si2019");
            //ime kolekcije + koje kolone + filter + sort
            // POPULATED_PLACES 2020/05/30 16:05:29;*;;ASC NM_PTT_CODE
            // POPULATED_PLACES 2020/05/30 16:05:29;*;NM_NAZIV startsWith Novi;ASC NM_PTT_CODE
            String[] polja = query.split(";");
            MongoCollection<Document> collection = dbMongo.getCollection(polja[0], Document.class);
            MongoCursor<Document> cursor;
            Document filter = new Document();
            System.out.println("POLJA " + Arrays.toString(polja));
            if(polja.length>=3) {
                String[] filterString = polja[2].split(" ");
                String filterKolona = filterString[0];
                String filterVrednost = "";
                for (int i = 2; i < filterString.length; i++) {
                    if (i == filterString.length - 1)
                        filterVrednost += filterString[i];
                    else
                        filterVrednost += filterString[i] + " ";
                }
                String[] sortString = null;
                String sortOrder = "";
                String sortKolona = "";
                if(polja.length==4) {
                    sortString = polja[3].split(" ");
                    sortOrder = sortString[0];
                    sortKolona = sortString[1];
                }
                if (polja[2].contains("startsWith")) {
                    filter.append(filterKolona, new Document("$regex", "^(?)" + Pattern.quote(filterVrednost)));
                } else if (polja[2].contains("endsWith")) {
                    filter.append(filterKolona, new Document("$regex", "(?)" + Pattern.quote(filterVrednost) + "$"));
                    //filter = regex(filterKolona,)
                } else if (polja[2].contains("equals")) {
                    filter.append(filterKolona, new Document("$regex", "^(?)" + Pattern.quote(filterVrednost) + "$"));
                } else if (polja[2].contains("contains")) {
                    filter.append(filterKolona, new Document("$regex", "(?)" + Pattern.quote(filterVrednost)));
                } else if (polja[2].contains(">")) {
                    filter.append(filterKolona, new Document("$gt", Long.parseLong(filterVrednost)));
                } else if (polja[2].contains("<")) {
                    filter.append(filterKolona, new Document("$lt", Long.parseLong(filterVrednost)));
                } else if (polja[2].contains("=")) {
                    filter.append(filterKolona, new Document("$eq", Long.parseLong(filterVrednost)));
                }
                System.out.println("FILTER JE " + filter.toJson());
                System.out.println("FILTER JE " + Arrays.toString(filterString));
                System.out.println("SORT JE " + Arrays.toString(sortString));
                if(polja.length==4) {
                    if (sortOrder.equals("ASC"))
                        cursor = collection.find(filter).sort(new Document(sortKolona, 1)).iterator();
                    else
                        cursor = collection.find(filter).sort(new Document(sortKolona, -1)).iterator();
                }
                else
                    cursor = collection.find(filter).iterator();
            }
            else
                cursor = collection.find().iterator();
            Document doc;
            while (cursor.hasNext()) {
                doc = cursor.next();
                for(Map.Entry<String,Object> set : doc.entrySet()){
                    if(set.getKey().equals("_id")) {
                        map.put(doc.get("_id").toString(), "");
                        System.out.println("ID JE " + doc.get("_id").toString());
                    }
                    if((polja[1].equals("*")||polja[1].contains(set.getKey()))&&!set.getKey().equals("_id")) {
                        if(map.get(doc.get("_id").toString()).isEmpty()) {
                            System.out.println("USAO SAMO JEDNOM");
                            map.put(doc.get("_id").toString(), set.getKey() + ":" + set.getValue().toString());
                        }
                        else {
                            map.put(doc.get("_id").toString(), map.get(doc.get("_id").toString()) + "," + set.getKey() + ":" + set.getValue().toString());
                        }
                    }
                }
            }
            System.out.println("MAPA JE " + map.toString());
            return map;
        }
        return null;
    }
}
