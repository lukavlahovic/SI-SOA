package com.broker.broker.controller;

import com.broker.broker.model.ServiceDTO;
import com.broker.broker.services.RegistracijaServisa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegServiceController {

    private RegistracijaServisa registracijaServisa;

    @Autowired
    public RegServiceController(RegistracijaServisa registracijaServisa){

        this.registracijaServisa = registracijaServisa;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/registracija/servisa")
    public ResponseEntity<Boolean> addRow(@RequestBody ServiceDTO serviceDTO, @RequestHeader("provajder") String username ){
        return new ResponseEntity<Boolean>(registracijaServisa.registrujServis(serviceDTO,username), HttpStatus.OK);
    }


}
