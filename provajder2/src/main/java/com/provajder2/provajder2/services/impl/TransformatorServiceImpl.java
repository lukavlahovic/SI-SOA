package com.provajder2.provajder2.services.impl;

import com.arangodb.ArangoCollection;
import com.arangodb.ArangoCursor;
import com.arangodb.ArangoDB;
import com.arangodb.ArangoDatabase;
import com.arangodb.entity.BaseDocument;
import com.arangodb.model.DocumentCreateOptions;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.provajder2.provajder2.arangoConfig.ArangoConfiguracija;
import com.provajder2.provajder2.bootstrap.Bootstrap;
import com.provajder2.provajder2.mongoConfig.MongoConfiguracija;
import com.provajder2.provajder2.services.TransformatorService;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public Boolean transform(String baza,String sqlCommand) { //mongo;{role:ULO_OZNAKA,ULO_NAZIV};
        if(sqlCommand!=null){
            return transform2(baza,sqlCommand);
        }
        String[] s = baza.split(";");
        String tip = s[0];
        String[] s1 = s[1].split("!");
        if(tip.equals("mongo"))
        {
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
                        BasicDBObject document = new BasicDBObject();
                        map.forEach((key, value) -> {
                            document.append((String)key,value);
                        });
                        collection.insertOne(document);
                    }
                }
                else{
                    System.out.println("VEC POSTOJI");
                }
            }
            return true;
        }
        else if(tip.equals("arango")){
            ArangoDB arango = ArangoConfiguracija.getConnection();
            ArangoDatabase db = arango.db("tim_402_1_arango_si2019");

            for(String s2:s1) {
                s2 = s2.replace("{", "");
                s2 = s2.replace("}", "");
                String[] tabelaPolja = s2.split(":");
                String tabela = tabelaPolja[0];
                String polja = tabelaPolja[1];
                ArangoCollection collection = db.collection(tabela);
                if(!collection.exists())
                {
                    db.createCollection(tabela);
                    String command = "SELECT " + polja + " FROM " + tabela.toUpperCase();
                    List results = bootstrap.getTemplate().queryForList(command);
                    for(Object result:results){
                        Map map = (Map) result;
                        BaseDocument document = new BaseDocument();
                        map.forEach((key, value) -> {
                            document.addAttribute((String)key,value);
                        });
                        collection.insertDocument(document, new DocumentCreateOptions());
                    }
                }
            }
            return true;
        }
        return false;
    }

    public Boolean transform2(String baza,String sqlCommand){
        System.out.println("SQL COMMAND PRE " + sqlCommand);
        sqlCommand = sqlCommand.replaceAll("`","");
        sqlCommand = sqlCommand.replaceAll("'","");
        sqlCommand = sqlCommand.replace(";","");
        System.out.println("SQL COMMAND POSLE " + sqlCommand);
        String[] s = baza.split(";");
        String tip = s[0];
        String[] s1 = s[1].split("!");
        String[] reci = sqlCommand.split(" ");
        System.out.println("TIP JE " + tip);
        if(tip.equals("mongo")) {
            System.out.println("UASO U MONGO");
            MongoDatabase db = MongoConfiguracija.getConnection().getDatabase("tim_402_1_mongo_si2019");
            for (String s2 : s1) {
                s2 = s2.replace("{", "");
                s2 = s2.replace("}", "");
                String[] tabelaPolja = s2.split(":");
                String tabela = tabelaPolja[0];
                String polja = "";
                    if (sqlCommand.startsWith("INSERT INTO") && db.listCollectionNames()
                            .into(new ArrayList<String>()).contains(reci[2])) {
                        Document document = new Document();
                        MongoCollection<Document> collection = db.getCollection(reci[2], Document.class);
                        MongoCursor<Document> cursor = collection.find().iterator();
                        Document tmp = cursor.next();
                        for(String key : tmp.keySet()){
                            polja += key + " ";
                        }
                        System.out.println("POLJA " + polja);
                        //append
                        String[] atributs = reci[3].replace("(","").replace(")","").split(",");
                        String[] values = reci[5].replace("(","").replace(")","").split(",");
                        for(int i=0;i<atributs.length;i++){
                            if(polja.indexOf(atributs[i])>-1)
                                document.append(atributs[i],values[i]);
                        }
                        collection.insertOne(document);
                        return true;
                    }
                    else if(sqlCommand.startsWith("DELETE FROM") && db.listCollectionNames()
                            .into(new ArrayList<String>()).contains(reci[2])){
                        MongoCollection<Document> collection = db.getCollection(reci[2], Document.class);
                        MongoCursor<Document> cursor = collection.find().iterator();
                        Document tmp = cursor.next();
                        for(String key : tmp.keySet()){
                            polja += key;
                        }
                        System.out.println("POLJA " + polja);
                        /*int nbAttr = StringUtils.countMatches(sqlCommand, "AND") + 1;
                        String[] atributs = new String[nbAttr];
                        String[] values = new String[nbAttr];*/
                        int j=0;
                        List<Document> lista = new ArrayList<>();
                        for(int i=4;i<reci.length;i++){
                            if(polja.indexOf(reci[i].split("=")[0])>-1) {
                                Document d = new Document(reci[i].split("=")[0], reci[i].split("=")[1]);
                                lista.add(d);
                            }
                            if(reci.length>i+1 && reci[i+1].equals("AND"))
                                i++;
                        }
                        /*if(polja.indexOf(atribut)>-1)
                            //collection.deleteOne(Filters.eq(atribut, value));
                            collection.deleteOne(and(eq(atribut, value)));*/

                        Document query =
                                new Document("$and", lista);
                        collection.deleteOne(query);
                        return true;
                    }
                    else if(sqlCommand.startsWith("UPDATE") && db.listCollectionNames()
                            .into(new ArrayList<String>()).contains(reci[1])){
                        System.out.println("UASO U UPDATE");
                        MongoCollection<Document> collection = db.getCollection(reci[1], Document.class);
                        MongoCursor<Document> cursor = collection.find().iterator();
                        Document tmp = cursor.next();
                        for(String key : tmp.keySet()){
                            polja += key;
                        }
                        System.out.println("POLJA " + polja);
                        Document updateQuery = new Document();
                        Document setData = new Document();
                        if(reci[3].indexOf(",")>-1){
                            for(String atributAndValue : reci[3].split(",")){
                                String atribut = atributAndValue.split("=")[0];
                                String value = atributAndValue.split("=")[1];
                                if(polja.indexOf(atribut)>-1)
                                    setData.append(atribut,value);
                            }
                        }
                        System.out.println("SET DATA JE " + setData.toJson());
                        updateQuery.append("$set", setData);
                        Document searchQuery = new Document();
                        for(int i=5;i<reci.length;i++) {
                            String searchAtribut = reci[i].split("=")[0];
                            String searchValue = reci[i].split("=")[1];
                            if(polja.indexOf(searchAtribut)>-1)
                                searchQuery.append(searchAtribut, searchValue);
                            if(reci.length>i+1 && reci[i+1].equals("AND"))
                                i++;
                        }
                        System.out.println("SEARCH JE " + searchQuery.toJson());
                        collection.updateOne(searchQuery, updateQuery);
                        return true;
                    }
            }
        }
        if(tip.equals("arango")){

            ArangoDB arango = ArangoConfiguracija.getConnection();
            ArangoDatabase db = arango.db("tim_402_1_arango_si2019");
            for(String s2:s1) {
                s2 = s2.replace("{", "");
                s2 = s2.replace("}", "");
                String[] tabelaPolja = s2.split(":");
                String tabela = tabelaPolja[0];
                String polja = "";
                ArangoCollection collection1 = db.collection(tabela);
                if (sqlCommand.startsWith("INSERT INTO")) {
                    ArangoCollection collection = db.collection(reci[2]);
                    if(collection.exists()) {
                        BaseDocument document = new BaseDocument();
                        //append
                        String[] atributs = reci[3].replace("(", "").replace(")", "").split(",");
                        String[] values = reci[5].replace("(", "").replace(")", "").split(",");
                        for (int i = 0; i < atributs.length; i++) {
                            if (polja.indexOf(atributs[i]) > -1)
                                document.addAttribute(atributs[i], values[i]);
                        }
                        collection.insertDocument(document, new DocumentCreateOptions());
                        return true;
                    }
                }
                else if(sqlCommand.startsWith("DELETE FROM")){
                    ArangoCollection collection = db.collection(reci[2]);
                    if(collection.exists()) {
                        String filter = "";
                        for(int i=5;i<reci.length;i++) {
                            if(polja.indexOf(reci[i].split("=")[0])>-1)
                                filter += "doc." + reci[i].split("=")[0] + " == " + "\"" + reci[i].split("=")[1] + "\" ";
                            if(reci.length>i+1 && reci[i+1].equals("AND")) {
                                filter += " and ";
                                i++;
                            }
                        }
                        ArangoCursor<BaseDocument> cursor = db.query("FOR doc IN " + tabela +
                                "  FILTER " + filter +
                                "RETURN doc", BaseDocument.class);
                        BaseDocument document = cursor.first();
                        if (document != null)
                            collection.deleteDocument(document.getKey());

                        return true;
                    }
                }
                else if(sqlCommand.startsWith("UPDATE")){
                    ArangoCollection collection = db.collection(reci[1]);
                    ArangoCursor<BaseDocument> cursor = db.query(
                            "for a in "+ reci[1] +
                                    "    limit 1" +
                                    "    return a", BaseDocument.class
                    );
                    for(String polje : cursor.next().getProperties().keySet()){
                        polja += polje;
                    }
                    System.out.println("POLJA " + polja);
                    if(collection.exists()) {
                        String filter = "";
                        for(int i=5;i<reci.length;i++) {
                            if(polja.indexOf(reci[i].split("=")[0])>-1)
                                filter += "doc." + reci[i].split("=")[0] + " == " + "\"" + reci[i].split("=")[1] + "\" ";
                            if(reci.length>i+1 && reci[i+1].equals("AND")) {
                                filter += " and ";
                                i++;
                            }
                        }
                        ArangoCursor<BaseDocument> cursor1 = db.query("FOR doc IN " + tabela +
                                "  FILTER " + filter +
                                "RETURN doc", BaseDocument.class);
                        BaseDocument document = cursor1.first();
                        if (document != null) {
                            if (reci[3].indexOf(",") > -1) {
                                for (String atributAndValue : reci[3].split(",")) {
                                    String atribut = atributAndValue.split("=")[0];
                                    String value = atributAndValue.split("=")[1];
                                    document.addAttribute(atribut, value);
                                }

                            }
                            collection.updateDocument(document.getKey(), document);
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
