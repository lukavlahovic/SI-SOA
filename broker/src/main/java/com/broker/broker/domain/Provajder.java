package com.broker.broker.domain;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Provajder {
//localhost:8081/teski/service?functions=AVG

    @Id
    private String  username;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private UserBroker userBroker;


    @Column
    private String host;

    @OneToMany(mappedBy = "provajder")
    private List<Servis> listaServisa = new ArrayList<>();

    @OneToMany(mappedBy = "provajderS")
    private List<SlozenServis> listaSlozenihServisa = new ArrayList<>();

//    @Column
//    private String username;
//
//    @Column
//    private String password;

}
