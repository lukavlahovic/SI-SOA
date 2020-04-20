package com.broker.broker.services.impl;

import com.broker.broker.domain.Endpoint;
import com.broker.broker.domain.Servis;
import com.broker.broker.mappers.EndpointMapper;
import com.broker.broker.mappers.ServiceMapper;
import com.broker.broker.model.EndpointDTO;
import com.broker.broker.repository.EndpointRepository;
import com.broker.broker.repository.ServisRepository;
import com.broker.broker.services.RegistracijaEndpointa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegistracijaEndpointaImpl implements RegistracijaEndpointa {

    private EndpointMapper endpointMapper;
    private EndpointRepository endpointRepository;
    private ServisRepository servisRepository;

    @Autowired
    public RegistracijaEndpointaImpl(EndpointMapper endpointMapper,EndpointRepository endpointRepository,ServisRepository servisRepository){
        this.endpointMapper = endpointMapper;
        this.endpointRepository = endpointRepository;
        this.servisRepository = servisRepository;
    }

    @Override
    public boolean registrujEndpoint(EndpointDTO endpointDTO,String servisId) {

        Servis servis = servisRepository.getOne(Long.parseLong(servisId));
        Endpoint endpoint = endpointMapper.endpointDTOToEndpoint(endpointDTO);
        endpoint.setServis(servis);
        endpointRepository.save(endpoint);

        return true;
    }
}
