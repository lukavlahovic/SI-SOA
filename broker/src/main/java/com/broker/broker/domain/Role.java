package com.broker.broker.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @OneToOne(mappedBy = "role")
    private Log log;

    @ManyToMany(mappedBy = "listaRolova")
    private List<Endpoint> listaEndpointa = new ArrayList<>();

}
