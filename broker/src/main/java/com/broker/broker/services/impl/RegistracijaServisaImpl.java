package com.broker.broker.services.impl;


import com.broker.broker.domain.Provajder;
import com.broker.broker.domain.Servis;
import com.broker.broker.domain.SlozenServis;
import com.broker.broker.mappers.ServiceMapper;
import com.broker.broker.mappers.SlozenMapper;
import com.broker.broker.model.ServiceDTO;
import com.broker.broker.model.SlozenDTO;
import com.broker.broker.repository.ProvajderRepository;
import com.broker.broker.repository.ServisRepository;
import com.broker.broker.repository.SlozenRepository;
import com.broker.broker.services.RegistracijaServisa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistracijaServisaImpl implements RegistracijaServisa {

    private ServiceMapper serviceMapper;
    private ServisRepository servisRepository;
    private ProvajderRepository provajderRepository;
    private SlozenMapper slozenMapper;
    private SlozenRepository slozenRepository;

    @Autowired
    public RegistracijaServisaImpl(ServiceMapper serviceMapper, ServisRepository servisRepository,
                                   ProvajderRepository provajderRepository,SlozenRepository slozenRepository,
                                   SlozenMapper slozenMapper){
        this.serviceMapper = serviceMapper;
        this.servisRepository = servisRepository;
        this.provajderRepository = provajderRepository;
        this.slozenRepository = slozenRepository;
        this.slozenMapper = slozenMapper;
    }


    @Override
    public boolean registrujServis(ServiceDTO serviceDTO,String username) {


        Provajder provajder = provajderRepository.findByUsername(username);
        serviceDTO.setProvajder(provajder);
        Servis servis = serviceMapper.serviceDTOtoService(serviceDTO);
        servisRepository.save(servis);

        return true;
    }

    @Override
    public boolean registrujSlozenServis(SlozenDTO slozenDTO, String username,String servisi) {


        Provajder provajder = provajderRepository.findByUsername(username);
        SlozenServis slozenServis = slozenMapper.slozenDTOtoslozen(slozenDTO);
        slozenServis.setProvajderS(provajder);
        String[] s = servisi.split(",");
        for(String services: s )
        {
            Servis ser = servisRepository.findByNameAndProvajder(services,provajder);
            slozenServis.getListaServisa().add(ser);
        }
        slozenRepository.save(slozenServis);
        for(String services: s )
        {
            Servis ser = servisRepository.findByNameAndProvajder(services,provajder);
            ser.getListaSlozenihServisa().add(slozenServis);
            servisRepository.save(ser);
        }

        return true;
    }
}
