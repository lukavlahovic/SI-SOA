package com.broker.broker.controller;

import com.broker.broker.model.ServiceDTO;
import com.broker.broker.model.SlozenDTO;
import com.broker.broker.services.RegistracijaServisa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('PROVAJDER')")
    @RequestMapping(method = RequestMethod.POST, value = "/registracija/servisa")
    public ResponseEntity<Boolean> addRow(@RequestBody ServiceDTO serviceDTO, @RequestHeader("provajder") String username ){
        return new ResponseEntity<Boolean>(registracijaServisa.registrujServis(serviceDTO,username), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('PROVAJDER')")
    @RequestMapping(method = RequestMethod.POST, value = "/registracija/slozenservis")
    public ResponseEntity<Boolean> slozenServis(@RequestBody SlozenDTO slozenDTO, @RequestHeader("provajder") String username,@RequestHeader("servisi") String servisi ){
        return new ResponseEntity<Boolean>(registracijaServisa.registrujSlozenServis(slozenDTO,username,servisi), HttpStatus.OK);
    }


}
