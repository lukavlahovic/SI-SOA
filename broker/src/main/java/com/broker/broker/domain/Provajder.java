package com.broker.broker.domain;

import lombok.Data;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Provajder {
//localhost:8081/teski/service?functions=AVG
    @Id
    private String  username;

    @Column
    private String host;

    @Column
    private String password;

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
