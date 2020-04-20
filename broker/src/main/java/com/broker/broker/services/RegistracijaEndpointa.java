package com.broker.broker.services;

import com.broker.broker.model.EndpointDTO;

public interface RegistracijaEndpointa {

    boolean registrujEndpoint(EndpointDTO endpointDTO,String servisId);
}
