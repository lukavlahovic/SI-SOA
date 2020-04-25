package com.broker.broker.services;

import com.broker.broker.model.ProvajderDTO;
import com.broker.broker.model.ServiceDTO;
import com.broker.broker.model.SlozenDTO;

public interface RegistracijaServisa {

    boolean registrujServis(ServiceDTO serviceDTO,String username);

    boolean registrujSlozenServis(SlozenDTO slozenDTO, String username,String servisi);

}
