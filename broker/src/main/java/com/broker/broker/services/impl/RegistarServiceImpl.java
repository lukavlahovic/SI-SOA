package com.broker.broker.services.impl;

import com.broker.broker.domain.Provajder;
import com.broker.broker.mappers.ProvajderMapper;
import com.broker.broker.model.ProvajderDTO;
import com.broker.broker.repository.ProvajderRepository;
import com.broker.broker.services.RegistarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistarServiceImpl implements RegistarService {

    private ProvajderMapper provajderMapper;
    private ProvajderRepository provajderRepository;

    @Autowired
    public RegistarServiceImpl(ProvajderMapper provajderMapper,ProvajderRepository provajderRepository){
        this.provajderMapper = provajderMapper;
        this.provajderRepository = provajderRepository;
    }

    @Override
    public boolean registruj(ProvajderDTO provajderDTO) {

        Provajder provajder = provajderMapper.provajderDTOtoprovajder(provajderDTO);
        provajderRepository.save(provajder);

        return true;
    }
}
