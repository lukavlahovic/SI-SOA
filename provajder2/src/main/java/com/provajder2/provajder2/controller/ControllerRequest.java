package com.provajder2.provajder2.controller;

import com.provajder2.provajder2.services.Services;
import com.provajder2.provajder2.services.impl.ServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ControllerRequest {

    private Services services;


    @Autowired
    public ControllerRequest(Services services){
        this.services = services;
    }


    @RequestMapping(method = RequestMethod.POST, value = "/gmail")
    public ResponseEntity<Boolean> sendGmail(){
        return new ResponseEntity<Boolean>(services.gmail_slanje(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/yahoo")
    public ResponseEntity<Boolean> sendYahoo(){
        return new ResponseEntity<Boolean>(services.yahoo_mail(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/prosek")
    public ResponseEntity<Boolean> prosek(){
        return new ResponseEntity<Boolean>(services.prosek(), HttpStatus.OK);
    }

}
