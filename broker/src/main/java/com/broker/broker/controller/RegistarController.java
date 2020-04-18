package com.broker.broker.controller;


import com.broker.broker.model.ProvajderDTO;
import com.broker.broker.services.RegistarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/registracija")
public class RegistarController {

    private RegistarService registarService;

    @Autowired
    public RegistarController(RegistarService registarService){

        this.registarService = registarService;
    }


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Boolean> addRow(@RequestBody ProvajderDTO provajderDTO){
        return new ResponseEntity<Boolean>(registarService.registruj(provajderDTO), HttpStatus.OK);
    }


}
