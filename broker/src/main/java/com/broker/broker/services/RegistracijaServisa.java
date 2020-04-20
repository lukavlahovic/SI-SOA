package com.broker.broker.services;

import com.broker.broker.model.ProvajderDTO;
import com.broker.broker.model.ServiceDTO;

public interface RegistracijaServisa {

    boolean registrujServis(ServiceDTO serviceDTO,String username);

}
