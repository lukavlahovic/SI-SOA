package com.provajder2.provajder2.controller;

import com.provajder2.provajder2.services.*;
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
import java.util.ArrayList;
import java.util.Map;

@Controller
public class ControllerRequest {

    private Services services;
    private StudentServis studentServis;
    private MongoService mongoService;
    private ArangoService arangoService;
    private TransformatorService transformatorService;

    @Autowired
    public ControllerRequest(Services services, StudentServis studentServis, MongoService mongoService, TransformatorService transformatorService, ArangoService arangoService){

        this.services = services;
        this.studentServis = studentServis;
        this.mongoService = mongoService;
        this.arangoService = arangoService;
        this.transformatorService = transformatorService;
    }


    @RequestMapping(method = RequestMethod.POST, value = "/gmail/send")
    public ResponseEntity<Boolean> sendGmail(){
        return new ResponseEntity<Boolean>(services.gmail_slanje(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/yahoo/send")
    public ResponseEntity<Boolean> sendYahoo(){
        return new ResponseEntity<Boolean>(services.yahoo_mail(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/prosek/avg")
    public ResponseEntity<Boolean> prosek(@RequestBody String test){
        return new ResponseEntity<Boolean>(services.prosek(test), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/service/student")
    public ResponseEntity<Map<String,Object>> prosek(){
        return new ResponseEntity<Map<String,Object>>(studentServis.prikazStudenta(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/mongo/mongo")
    public ResponseEntity<String> mongoDB(){
        return new ResponseEntity<String>(mongoService.test(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/arango/arango")
    public ResponseEntity<String> arangoDB(){
        return new ResponseEntity<String>(arangoService.test(), HttpStatus.OK);}

    @RequestMapping(method = RequestMethod.POST, value = "/transform/transform")
    public ResponseEntity<Boolean> transform(@RequestHeader("baza") String baza, @RequestBody(required = false) String sqlCommand){
        return new ResponseEntity<Boolean>(transformatorService.transform(baza,sqlCommand), HttpStatus.OK);
    }
}