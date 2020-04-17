package com.provajder1.provajder1.bootstrap;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

@Component
@Data
public class Bootstrap implements CommandLineRunner {

    JdbcTemplate template;

    @Autowired
    public Bootstrap(){}

    @Override
    public void run(String... args) throws Exception {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        //ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://64.225.110.65:3306/tim_402_1_si2019");
        ds.setUsername("tim_402_1_si2019");
        ds.setPassword("PYzqSrMC");
        template = new JdbcTemplate(ds);
    }

}
