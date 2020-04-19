package com.broker.broker.model;

import lombok.Data;

import java.util.Map;
@Data
public class Podaci {
    String entitet;
    Map<String,String> atributi; //naziv atributa vrednost
}
