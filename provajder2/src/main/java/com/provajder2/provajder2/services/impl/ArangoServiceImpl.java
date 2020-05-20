package com.provajder2.provajder2.services.impl;

import com.provajder2.provajder2.arangoConfig.ArangoConfiguracija;
import com.provajder2.provajder2.services.ArangoService;
import org.springframework.stereotype.Service;

@Service
public class ArangoServiceImpl implements ArangoService {
    @Override
    public String test(String query) {

        ArangoConfiguracija.getConnection();

        return "ok";
    }
}
