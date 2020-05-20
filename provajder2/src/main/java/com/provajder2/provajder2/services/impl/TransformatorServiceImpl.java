package com.provajder2.provajder2.services.impl;

import com.arangodb.ArangoCollection;
import com.arangodb.ArangoCursor;
import com.arangodb.ArangoDB;
import com.arangodb.ArangoDatabase;
import com.arangodb.entity.BaseDocument;
import com.arangodb.model.DocumentCreateOptions;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
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
        if(tip.equals("mongo")) {
            MongoDatabase db = MongoConfiguracija.getConnection().getDatabase("tim_402_1_mongo_si2019");
            for (String s2 : s1) {
                s2 = s2.replace("{", "");
                s2 = s2.replace("}", "");
                String[] tabelaPolja = s2.split(":");
                String tabela = tabelaPolja[0];
                String polja = tabelaPolja[1];
                if (db.listCollectionNames()
                        .into(new ArrayList<String>()).contains(tabela)) {
                    if (sqlCommand.startsWith("INSERT INTO") && reci[2].equalsIgnoreCase(tabela)) {
                        BasicDBObject document = new BasicDBObject();
                        MongoCollection<BasicDBObject> collection = db.getCollection(tabela, BasicDBObject.class);
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
                    else if(sqlCommand.startsWith("DELETE FROM") && reci[2].equalsIgnoreCase(tabela)){
                        MongoCollection<BasicDBObject> collection = db.getCollection(tabela, BasicDBObject.class);
                        String atribut = reci[4].split("=")[0];
                        String value = reci[4].split("=")[1];
                        if(polja.indexOf(atribut)>-1)
                            collection.deleteOne(Filters.eq(atribut, value));
                        return true;
                    }
                    else if(sqlCommand.startsWith("UPDATE") && reci[1].equalsIgnoreCase(tabela)){
                        MongoCollection<Document> collection = db.getCollection(tabela);
                        Document updateQuery = new Document();
                        Document setData = new Document();
                        if(reci[3].indexOf(",")>-1){
                            for(String atributAndValue : reci[3].split(",")){
                                String atribut = atributAndValue.split("=")[0];
                                String value = atributAndValue.split("=")[1];
                                setData.append(atribut,value);
                            }

                        }
                        updateQuery.append("$set", setData);
                        Document searchQuery = new Document();
                        String searchAtribut = reci[5].split("=")[0];
                        String searchValue = reci[5].split("=")[1];
                        searchQuery.append(searchAtribut, searchValue);
                        collection.updateOne(searchQuery, updateQuery);
                        return true;
                    }
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
                String polja = tabelaPolja[1];
                ArangoCollection collection = db.collection(tabela);
                if(collection.exists())
                {
                    if (sqlCommand.startsWith("INSERT INTO") && reci[2].equalsIgnoreCase(tabela)) {
                        BaseDocument document = new BaseDocument();
                        //append
                        String[] atributs = reci[3].replace("(","").replace(")","").split(",");
                        String[] values = reci[5].replace("(","").replace(")","").split(",");
                        for(int i=0;i<atributs.length;i++){
                            if(polja.indexOf(atributs[i])>-1)
                                document.addAttribute(atributs[i],values[i]);
                        }
                        collection.insertDocument(document,new DocumentCreateOptions());
                        return true;
                    }
                    else if(sqlCommand.startsWith("DELETE FROM") && reci[2].equalsIgnoreCase(tabela)){
                        String atribut = reci[4].split("=")[0];
                        String value = reci[4].split("=")[1];
                        if(polja.indexOf(atribut)>-1){
                            ArangoCursor<BaseDocument> cursor = db.query("FOR doc IN " +tabela+
                                            "  FILTER doc."+atribut+ " == "+"\""+value+"\" " +
                                            "RETURN doc",BaseDocument.class);
                            BaseDocument document = cursor.first();
                            if(document!=null)
                                collection.deleteDocument(document.getKey());
                        }
                        return true;
                    }
                    else if(sqlCommand.startsWith("UPDATE") && reci[1].equalsIgnoreCase(tabela)){
                        String searchAtribut = reci[5].split("=")[0];
                        String searchValue = reci[5].split("=")[1];
                        ArangoCursor<BaseDocument> cursor = db.query("FOR doc IN " +tabela+
                                "  FILTER doc."+searchAtribut+ " == "+"\""+searchValue+"\" " +
                                "RETURN doc",BaseDocument.class);
                        BaseDocument document = cursor.first();
                        if(document!=null)
                        {
                            if(reci[3].indexOf(",")>-1){
                                for(String atributAndValue : reci[3].split(",")){
                                    String atribut = atributAndValue.split("=")[0];
                                    String value = atributAndValue.split("=")[1];
                                    document.addAttribute(atribut,value);
                                }

                            }


                            collection.updateDocument(document.getKey(),document);
                            return true;
                        }


                    }
                }


            }
        }
        return false;
    }
}
