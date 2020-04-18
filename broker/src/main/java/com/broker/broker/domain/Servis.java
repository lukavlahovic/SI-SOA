package com.broker.broker.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Servis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String ruta;

    @Column
    private String name;

    @ManyToOne
    private Provajder provajder;

    @OneToMany(mappedBy = "servis")
    private List<Endpoint> listaEndpointa = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "slozenServis_servis",
            joinColumns = @JoinColumn(name = "servis_id"),
            inverseJoinColumns = @JoinColumn(name = "miniServis_id"))
    private List<SlozenServis> listaSlozenihServisa = new ArrayList<>();


}
