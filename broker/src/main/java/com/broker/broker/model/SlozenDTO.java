package com.broker.broker.model;

import com.broker.broker.domain.Provajder;
import lombok.Data;

@Data
public class SlozenDTO {

    private Long id;
    private String name;
    private Provajder provajder;

}
