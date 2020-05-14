package com.broker.broker.services;

import com.broker.broker.model.Podaci;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface PovezivanjeNaProvajdera {
    ResponseEntity<Object> pozoviProvajdera(String provajder, String servis, String endpoint, Map<String,Object> map,String account);
}