package com.broker.broker.domain;


import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Endpoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String ruta;

    @Column
    private String zahtev;

    @Column
    private String input;

    @Column
    private String output;

    @ManyToOne
    private Servis servis;

    @OneToMany(mappedBy = "endpoint")
    private List<Endpoint_access> listaEndpointaPristupa = new ArrayList<>();

}
