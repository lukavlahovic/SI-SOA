package com.provajder1.provajder1.services.impl;

import com.provajder1.provajder1.api.mappers.AddMapper;
import com.provajder1.provajder1.api.model.AddDTO;
import com.provajder1.provajder1.bootstrap.Bootstrap;
import com.provajder1.provajder1.services.AddService;
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

//    @Override
//    public boolean addTK(String ulo_oznaka, String ulo_naziv) {
//        String command = "INSERT INTO `ROLE` (`ULO_OZNAKA`,`ULO_NAZIV`) VALUES ('" + ulo_oznaka + "','" + ulo_naziv + "');";
//        System.out.println("COMANADA " + command);
//        bootstrap.getTemplate().execute(command);
//        return true;
//    }
    public boolean addTK(AddDTO addDTO) {
        System.out.println("Entitet " + addDTO.getEntitet() + " atributi " + addDTO.getAtributi().toString());
        return true;
    }
}
