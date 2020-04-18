package com.broker.broker.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class SlozenServis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Provajder provajderS;

    @ManyToMany(mappedBy = "listaSlozenihServisa")
    private List<Servis> listaServisa = new ArrayList<>();
}
