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
import com.sun.deploy.security.SelectableSecurityManager;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class TransformatorServiceImpl implements TransformatorService {

    private Bootstrap bootstrap;
    private static MongoDatabase dbMongo;
    private static ArangoDatabase dbArango;

    @Autowired
    public TransformatorServiceImpl(Bootstrap bootstrap) {
        this.bootstrap = bootstrap;
    }

    @Override
    public Boolean transform(String baza, String sqlCommand) { //mongo;{role:ULO_OZNAKA,ULO_NAZIV};
        if (sqlCommand != null) {
            return transform2(baza, sqlCommand);
        }
        String[] s = baza.split(";");
        String tip = s[0];
        String[] s1 = s[1].split("!");
        if (tip.equals("mongo")) {
            dbMongo = MongoConfiguracija.getConnection().getDatabase("tim_402_1_mongo_si2019");
            for (String s2 : s1) {
                s2 = s2.replace("{", "");
                s2 = s2.replace("}", "");
                String[] tabelaPolja = s2.split(":");
                String tabela = tabelaPolja[0];
                String polja = tabelaPolja[1];
                if (!dbMongo.listCollectionNames()
                        .into(new ArrayList<String>()).contains(tabela)) {
                    System.out.println("TRANSFORMACIJA " + polja);
                    MongoCollection<BasicDBObject> collection = dbMongo.getCollection(tabela, BasicDBObject.class);
                    String command = "SELECT " + polja + " FROM " + tabela.toUpperCase();
                    List results = bootstrap.getTemplate().queryForList(command);
                    for (Object result : results) {
                        Map map = (Map) result;
                        BasicDBObject document = new BasicDBObject();
                        map.forEach((key, value) -> {
                            document.append((String) key, value);
                        });
                        collection.insertOne(document);
                    }
                } else {
                    System.out.println("VEC POSTOJI");
                }
            }
            return true;
        } else if (tip.equals("arango")) {

            ArangoDatabase db = ArangoConfiguracija.getConnection().db("tim_402_1_arango_si2019");

            for (String s2 : s1) {
                s2 = s2.replace("{", "");
                s2 = s2.replace("}", "");
                String[] tabelaPolja = s2.split(":");
                String tabela = tabelaPolja[0];
                String polja = tabelaPolja[1];
                ArangoCollection collection = db.collection(tabela);
                if (!collection.exists()) {
                    db.createCollection(tabela);
                    String command = "SELECT " + polja + " FROM " + tabela.toUpperCase();
                    List results = bootstrap.getTemplate().queryForList(command);
                    for (Object result : results) {
                        Map map = (Map) result;
                        BaseDocument document = new BaseDocument();
                        map.forEach((key, value) -> {
                            document.addAttribute((String) key, value);
                        });
                        collection.insertDocument(document, new DocumentCreateOptions());
                    }
                }
            }
            return true;
        }
        return false;
    }

    public Boolean transform2(String baza, String sqlCommand) {
        System.out.println("SQL COMMAND PRE " + sqlCommand);
        sqlCommand = sqlCommand.replaceAll("`", "");
        sqlCommand = sqlCommand.replaceAll("'", "");
        sqlCommand = sqlCommand.replace(";", "");
        System.out.println("SQL COMMAND POSLE " + sqlCommand);
        String[] s = baza.split(";");
        String tip = s[0];
        System.out.println("TIP JE " + tip);
        String tabela = "";
        String polja;
        if (sqlCommand.startsWith("INSERT INTO ")) {
            int i = 12;
            for (; sqlCommand.charAt(i) != ' '; i++) {
                tabela += sqlCommand.charAt(i);
            }
            if (ContainCheck(tabela,tip)) {
                i += 2;
                String attributesString = "";
                for (; sqlCommand.charAt(i) != ')'; i++) {
                    attributesString += sqlCommand.charAt(i);
                }
                i += 10;
                polja = getPola(tabela, tip);
                System.out.println("POLJA " + polja);
                //append
                String[] atributs;
                if (attributesString.contains(","))
                    atributs = attributesString.split(",");
                else
                    atributs = new String[]{attributesString};

                String valuesString = sqlCommand.substring(i).replace(")", "");
                String[] values;
                if (valuesString.contains(","))
                    values = valuesString.split(",");
                else
                    values = new String[]{valuesString};
                if (tip.equals("mongo")) {
                    MongoCollection<Document> collection = dbMongo.getCollection(tabela, Document.class);
                    Document document = new Document();
                    for (int j = 0; j < atributs.length; j++) {
                        if (polja.indexOf(atributs[j]) > -1)
                            document.append(atributs[j], values[j]);
                    }
                    System.out.println(values);
                    collection.insertOne(document);
                    return true;
                } else if (tip.equals("arango")) {
                    BaseDocument document = new BaseDocument();
                    ArangoCollection collection = dbArango.collection(tabela);
                    for (int j = 0; j < atributs.length; j++) {
                        if (polja.indexOf(atributs[j]) > -1)
                            document.addAttribute(atributs[j], values[j]);
                    }
                    System.out.println(values);
                    collection.insertDocument(document, new DocumentCreateOptions());
                    return true;
                }
            }
            } else if (sqlCommand.startsWith("DELETE FROM")) {

                int i = 12;
                for (; sqlCommand.charAt(i) != ' '; i++) {
                    tabela += sqlCommand.charAt(i);
                }
                if (ContainCheck(tabela,tip)) {
                    i += 7;
                    String deleteAtributAndValueString = sqlCommand.substring(i);
                    String[] deleteAtributAndValues;
                    if (deleteAtributAndValueString.contains(" AND ")) {
                        deleteAtributAndValues = deleteAtributAndValueString.split(" AND ");
                    } else {
                        deleteAtributAndValues = new String[]{deleteAtributAndValueString};
                    }
                    polja = getPola(tabela, tip);
                    System.out.println("POLJA " + polja);
                    if (tip.equals("mongo"))
                    {
                        MongoCollection<Document> collection = dbMongo.getCollection(tabela, Document.class);
                        List<Document> lista = new ArrayList<>();
                        for (String deleteAtributAndValue : deleteAtributAndValues) {
                            if (polja.indexOf(deleteAtributAndValue.split("=")[0]) > -1) {
                                Document d = new Document(deleteAtributAndValue.split("=")[0], deleteAtributAndValue.split("=")[1]);
                                lista.add(d);
                            }
                        }
                        Document query =
                                new Document("$and", lista);
                        collection.deleteOne(query);
                        return true;
                    }
                    else if(tip.equals("arango")) {
                        String filter = "";
                        int number = 0;
                        int count = deleteAtributAndValues.length;
                        for (String deleteAtributAndValue:deleteAtributAndValues) {
                            if (polja.indexOf(deleteAtributAndValue.split("=")[0]) > -1) {
                                filter += "doc." + deleteAtributAndValue.split("=")[0] + " == " + "\"" + deleteAtributAndValue.split("=")[1] + "\" ";
                                if(number<count-1)
                                    filter += "AND ";
                            }
                            number++;
                        }
                        ArangoCollection collection = dbArango.collection(tabela);
                        ArangoCursor<BaseDocument> cursor = dbArango.query("FOR doc IN " + tabela +
                                "  FILTER " + filter +
                                "RETURN doc", BaseDocument.class);
                        BaseDocument document = cursor.first();
                        if (document != null)
                            collection.deleteDocument(document.getKey());
                        return true;
                    }
                }
            } else if (sqlCommand.startsWith("UPDATE ")) {
                System.out.println("USAO U UPDATE");
                int i = 7;
                for (; sqlCommand.charAt(i) != ' '; i++) {
                    tabela += sqlCommand.charAt(i);
                }
                if (ContainCheck(tabela,tip)) {
                    i += 5;
                    polja = getPola(tabela,tip);
                    System.out.println("POLJA " + polja);
                    String setAtributAndValueString = sqlCommand.substring(i, sqlCommand.indexOf("WHERE") - 1);
                    String[] setAtributAndValue;
                    if (setAtributAndValueString.contains(","))
                        setAtributAndValue = setAtributAndValueString.split(",");
                    else
                        setAtributAndValue = new String[]{setAtributAndValueString};
                    i = sqlCommand.indexOf("WHERE") + 6;
                    String searchAtributAndValueString = sqlCommand.substring(i);
                    String[] searchAtributAndValues;
                    if (searchAtributAndValueString.contains(" AND ")) {
                        searchAtributAndValues = searchAtributAndValueString.split(" AND ");
                    } else {
                        searchAtributAndValues = new String[]{searchAtributAndValueString};
                    }
                    if(tip.equals("mongo"))
                    {
                        Document updateQuery = new Document();
                        Document setData = new Document();
                        MongoCollection<Document> collection = dbMongo.getCollection(tabela, Document.class);
                        for (String atributAndValue : setAtributAndValue) {
                            String atribut = atributAndValue.split("=")[0];
                            String value = atributAndValue.split("=")[1];
                            if (polja.indexOf(atribut) > -1)
                                setData.append(atribut, value);
                        }
                        System.out.println("SET DATA JE " + setData.toJson());
                        updateQuery.append("$set", setData);
                        Document searchQuery = new Document();
                        for (String searchAtributAndValue : searchAtributAndValues) {
                            String searchAtribut = searchAtributAndValue.split("=")[0];
                            String searchValue = searchAtributAndValue.split("=")[1];
                            if (polja.indexOf(searchAtribut) > -1)
                                searchQuery.append(searchAtribut, searchValue);
                        }
                        System.out.println("SEARCH JE " + searchQuery.toJson());
                        collection.updateOne(searchQuery, updateQuery);
                        return true;
                    }
                    else if(tip.equals("arango"))
                    {
                        String filter = "";
                        int number = 0;
                        int count = searchAtributAndValues.length;
                        for (String searchAtributAndValue:searchAtributAndValues) {
                            if (polja.indexOf(searchAtributAndValue.split("=")[0]) > -1) {
                                filter += "doc." + searchAtributAndValue.split("=")[0] + " == " + "\"" + searchAtributAndValue.split("=")[1] + "\" ";
                                if(number<count-1)
                                    filter += "AND ";
                            }
                            number++;
                        }
                        ArangoCollection collection = dbArango.collection(tabela);
                        ArangoCursor<BaseDocument> cursor = dbArango.query("FOR doc IN " + tabela +
                                "  FILTER " + filter +
                                " RETURN doc", BaseDocument.class);
                        BaseDocument document = cursor.first();
                        if (document != null) {

                            for (String atributAndValue : setAtributAndValue) {
                                String atribut = atributAndValue.split("=")[0];
                                String value = atributAndValue.split("=")[1];
                                document.addAttribute(atribut, value);
                            }


                            collection.updateDocument(document.getKey(), document);
                            return true;
                        }
                    }
                }
            }

        return false;
        }



    public String getPola (String tableName, String dbType){
        String polja = "";
        if (dbType.equals("mongo"))
        {
            MongoCollection<Document> collection = dbMongo.getCollection(tableName, Document.class);
            MongoCursor<Document> cursor = collection.find().iterator();
            Document tmp = cursor.next();
            for (String key : tmp.keySet()) {
                polja += key + " ";
            }
        }
        else if(dbType.equals("arango"))
        {
            ArangoCursor<BaseDocument> cursor = dbArango.query(
                    "for a in " + tableName +
                            "    limit 1" +
                            "    return a", BaseDocument.class
            );
            for (String polje : cursor.next().getProperties().keySet()) {
                polja += polje;
            }
        }
            return polja;
    }

    public boolean ContainCheck(String tableName, String dbType){
        if (dbType.equals("mongo")) {
            dbMongo = MongoConfiguracija.getConnection().getDatabase("tim_402_1_mongo_si2019");
            if (dbMongo.listCollectionNames().into(new ArrayList<String>()).contains(tableName))
                return true;

        } else if (dbType.equals("arango")) {
            dbArango = ArangoConfiguracija.getConnection().db("tim_402_1_arango_si2019");
            if(dbArango.collection(tableName).exists())
                return true;
        }
        return false;
    }

}