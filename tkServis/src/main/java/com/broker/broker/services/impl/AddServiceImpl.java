package com.broker.broker.services.impl;

import com.broker.broker.api.mappers.AddMapper;
import com.broker.broker.bootstrap.Bootstrap;
import com.broker.broker.services.AddService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddServiceImpl implements AddService {

    private AddMapper addMapper;
    private Bootstrap bootstrap;

    @Autowired
    public AddServiceImpl(AddMapper addMapper, Bootstrap bootstrap){
        this.addMapper = addMapper;
        this.bootstrap = bootstrap;
    }

    @Override
    public boolean addTK(String ulo_oznaka, String ulo_naziv) {
        String command = "INSERT INTO `ROLE` (`ULO_OZNAKA`,`ULO_NAZIV`) VALUES ('" + ulo_oznaka + "','" + ulo_naziv + "');";
        System.out.println("COMANADA " + command);
        bootstrap.getTemplate().execute(command);
        return true;
    }
}
