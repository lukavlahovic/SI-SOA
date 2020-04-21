package com.broker.broker.services.impl;


import com.broker.broker.domain.Provajder;
import com.broker.broker.domain.Role;
import com.broker.broker.domain.UserBroker;
import com.broker.broker.mappers.EndpointMapper;
import com.broker.broker.mappers.ProvajderMapper;
import com.broker.broker.model.ProvajderDTO;
import com.broker.broker.repository.EndpointRepository;
import com.broker.broker.repository.ProvajderRepository;
import com.broker.broker.repository.RoleRepository;
import com.broker.broker.repository.UserRepository;
import com.broker.broker.services.RegistarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistarServiceImpl implements RegistarService {

    private ProvajderMapper provajderMapper;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private ProvajderRepository provajderRepository;

    @Autowired
    public RegistarServiceImpl(ProvajderMapper provajderMapper,UserRepository userRepository, RoleRepository roleRepository, ProvajderRepository provajderRepository){
        this.provajderMapper = provajderMapper;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.provajderRepository = provajderRepository;
    }

    @Override
    public boolean registruj(ProvajderDTO provajderDTO, String role, String host) {

        UserBroker provajder = provajderMapper.provajderDTOtoprovajder(provajderDTO);
        Role uloga = roleRepository.findByName(role);
        provajder.getRoles().add(uloga);

        if(role.equals("PROVAJDER"))
        {
            Provajder p = new Provajder();
            p.setUserBroker(provajder);
            p.setHost(host);
            provajderRepository.save(p);

        }
        userRepository.save(provajder);
        return true;
    }

}
