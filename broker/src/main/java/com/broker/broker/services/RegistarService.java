package com.broker.broker.services;

import com.broker.broker.model.EndpointDTO;
import com.broker.broker.model.ProvajderDTO;
import org.springframework.http.ResponseEntity;


public interface RegistarService {

    boolean registruj(ProvajderDTO provajderDTO);

}
