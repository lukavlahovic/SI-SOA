package com.broker.broker.model;

import lombok.Data;

@Data
public class EndpointDTO {

    private Long id;
    private String ruta;
    private String zahtev;
    private String input;
    private String output;
    private String filter;

}
