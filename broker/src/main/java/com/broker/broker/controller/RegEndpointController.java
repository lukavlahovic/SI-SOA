package com.broker.broker.controller;

import com.broker.broker.model.EndpointDTO;
import com.broker.broker.services.RegistracijaEndpointa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegEndpointController {

    private RegistracijaEndpointa registracijaEndpointa;

    @Autowired
    public RegEndpointController(RegistracijaEndpointa registracijaEndpointa){

        this.registracijaEndpointa = registracijaEndpointa;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/registracija/endpointa")
    public ResponseEntity<Boolean> addRow(@RequestBody EndpointDTO endpointDTO, @RequestHeader("id") String servisId){
        return new ResponseEntity<Boolean>(registracijaEndpointa.registrujEndpoint(endpointDTO,servisId), HttpStatus.OK);
    }


}
