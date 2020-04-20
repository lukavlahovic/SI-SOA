package com.broker.broker.model;

import com.broker.broker.domain.Provajder;
import lombok.Data;

@Data
public class ServiceDTO {

    private Long id;
    private String ruta;
    private String name;
    private Provajder provajder;
}
