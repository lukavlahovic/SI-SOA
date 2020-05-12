package com.broker.broker.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



@Entity
@Data
public class Log {

    public Log()
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        this.datum = dtf.format(now);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Boolean pristup;

    @Column
    private String datum;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userbroker_username", referencedColumnName = "username")
    private UserBroker userBroker;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endpoint_id", referencedColumnName = "id")
    private Endpoint endpoint;




}
