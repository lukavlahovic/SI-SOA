package com.provajder2.provajder2.arangoConfig;


import com.arangodb.ArangoDB;

public class ArangoConfiguracija {
    private static String user = "tim_402_1_arango_si2019"; // the user name
    private static String database = "tim_402_1_arango_si2019"; // the name of the database in which the user is defined
    private static String password = "wspmvUEv";//wspmvUEv

    public static ArangoDB getConnection(){
        ArangoDB arangoDB = new ArangoDB.Builder()
                .host("64.225.110.65", 8529)
                .user(user)
                .password(password)
                .build();
        return arangoDB;
    }

}
