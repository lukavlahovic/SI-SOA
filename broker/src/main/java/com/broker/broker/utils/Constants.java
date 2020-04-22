package com.broker.broker.utils;

public class Constants {
    public static final String SECRET_KEY = "SISecretKeyForJWT";
    public static final String HEADER_STRING = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final long EXPIRATION_TIME = 86400;
    public static String token;

    void setToken(String s){
        this.token = s;
    }
}
