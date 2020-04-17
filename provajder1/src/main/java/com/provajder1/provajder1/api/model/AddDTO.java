package com.provajder1.provajder1.api.model;

import lombok.Data;

import java.util.Map;

@Data
public class AddDTO {
    String entitet;
    Map<String,String> atributi; //naziv atributa vrednost
}
