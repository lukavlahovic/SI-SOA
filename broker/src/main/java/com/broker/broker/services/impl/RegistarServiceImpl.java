package com.broker.broker.services.impl;


import com.broker.broker.domain.Provajder;
import com.broker.broker.domain.UserBroker;
import com.broker.broker.mappers.EndpointMapper;
import com.broker.broker.mappers.ProvajderMapper;
import com.broker.broker.model.ProvajderDTO;
import com.broker.broker.repository.EndpointRepository;
import com.broker.broker.repository.ProvajderRepository;
import com.broker.broker.repository.UserRepository;
import com.broker.broker.services.RegistarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistarServiceImpl implements RegistarService {

    private ProvajderMapper provajderMapper;
    private UserRepository userRepository;

    @Autowired
    public RegistarServiceImpl(ProvajderMapper provajderMapper,UserRepository userRepository){
        this.provajderMapper = provajderMapper;
        this.userRepository = userRepository;
    }

    @Override
    public boolean registruj(ProvajderDTO provajderDTO) {

        UserBroker provajder = provajderMapper.provajderDTOtoprovajder(provajderDTO);
        userRepository.save(provajder);

        return true;
    }

}
