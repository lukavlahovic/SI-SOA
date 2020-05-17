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

    @Column
    private String baza;

    @ManyToOne
    private Servis servis;

    @OneToMany(mappedBy = "endpoint")
    private List<Endpoint_access> listaEndpointaPristupa = new ArrayList<>();

    @OneToOne(mappedBy = "endpoint")
    private Log log;

    @ManyToMany
    @JoinTable(
            name = "endpoint_role",
            joinColumns = @JoinColumn(name = "endpoint_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> listaRolova = new ArrayList<>();

    @Column
    private String filter;

}
