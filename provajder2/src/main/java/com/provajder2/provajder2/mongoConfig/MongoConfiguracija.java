package com.provajder2.provajder2.mongoConfig;

import com.mongodb.MongoCredential;
import com.mongodb.*;


import java.util.Arrays;


public class MongoConfiguracija {
    private static String user = "tim_402_1_mongo_si2019"; // the user name
    private static String database = "tim_402_1_mongo_si2019"; // the name of the database in which the user is defined
    private static String password = "wspmvUEv";//wspmvUEv

    public static MongoClient getConnection() {
        MongoCredential credential = MongoCredential.createCredential(user, database, password.toCharArray());
        MongoClient mongoClient = new MongoClient(new ServerAddress("64.225.110.65"), Arrays.asList(credential));
        return mongoClient;
    }

}