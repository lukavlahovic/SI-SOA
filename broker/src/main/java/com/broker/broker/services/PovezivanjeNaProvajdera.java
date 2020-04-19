package com.broker.broker.services;

import com.broker.broker.model.Podaci;
import org.springframework.http.ResponseEntity;

public interface PovezivanjeNaProvajdera {
    ResponseEntity<Object> pozoviProvajdera(String provajder, String servis, String endpoint, Podaci podaci);
}
