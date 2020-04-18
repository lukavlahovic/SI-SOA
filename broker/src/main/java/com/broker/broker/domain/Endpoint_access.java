package com.broker.broker.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
public class Endpoint_access {

    @Id
    private String access;

    @ManyToOne
    private Endpoint endpoint;

}
