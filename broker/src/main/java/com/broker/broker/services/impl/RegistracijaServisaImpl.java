package com.broker.broker.services.impl;


import com.broker.broker.domain.Provajder;
import com.broker.broker.domain.Servis;
import com.broker.broker.mappers.ServiceMapper;
import com.broker.broker.model.ServiceDTO;
import com.broker.broker.repository.ProvajderRepository;
import com.broker.broker.repository.ServisRepository;
import com.broker.broker.services.RegistracijaServisa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistracijaServisaImpl implements RegistracijaServisa {

    private ServiceMapper serviceMapper;
    private ServisRepository servisRepository;
    private ProvajderRepository provajderRepository;

    @Autowired
    public RegistracijaServisaImpl(ServiceMapper serviceMapper, ServisRepository servisRepository, ProvajderRepository provajderRepository){
        this.serviceMapper = serviceMapper;
        this.servisRepository = servisRepository;
        this.provajderRepository = provajderRepository;
    }


    @Override
    public boolean registrujServis(ServiceDTO serviceDTO,String username) {


        Provajder provajder = provajderRepository.findByUsername(username);
        serviceDTO.setProvajder(provajder);
        Servis servis = serviceMapper.serviceDTOtoService(serviceDTO);
        servisRepository.save(servis);

        return true;
    }
}
