package com.broker.broker.controller;


import com.broker.broker.model.ProvajderDTO;
import com.broker.broker.services.PovezivanjeNaProvajdera;
import com.broker.broker.services.RegistarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Controller
public class RegistarController {

    private RegistarService registarService;
    private PovezivanjeNaProvajdera povezivanjeNaProvajdera;
    private HttpServletRequest request;

    @Autowired
    public RegistarController(RegistarService registarService,PovezivanjeNaProvajdera povezivanjeNaProvajdera, HttpServletRequest request){

        this.registarService = registarService;
        this.povezivanjeNaProvajdera = povezivanjeNaProvajdera;
        this.request = request;
    }


    @RequestMapping(method = RequestMethod.POST, value = "/registracija")
    public ResponseEntity<Boolean> addRow(@RequestBody ProvajderDTO provajderDTO, @RequestHeader("role") String role,@RequestHeader("host") String host){
        return new ResponseEntity<Boolean>(registarService.registruj(provajderDTO,role,host), HttpStatus.OK);
    }
    //localhost:8080/provajder1/teski/add               localhost:8080/provajder1/teski/delete
    @PreAuthorize("hasRole('USER')")
    @RequestMapping(method = RequestMethod.POST, value = "/{provajder}/{servis}/{endpoint}")
    public ResponseEntity<Object> pozoviProvajdera(@PathVariable String provajder, @PathVariable String servis, @PathVariable String endpoint,
                                                   @RequestBody(required = false) Map<String,Object> map){
        return povezivanjeNaProvajdera.pozoviProvajdera(provajder,servis,endpoint,map);
    }
    @PreAuthorize("hasRole('USER')")
    @RequestMapping(method = RequestMethod.GET, value = "/{provajder}/{servis}/{endpoint}")
    public ResponseEntity<Object> pozoviProvajdera1(@PathVariable String provajder, @PathVariable String servis, @PathVariable String endpoint){
        Map<String,Object> map = getHeadersInfo();
        return povezivanjeNaProvajdera.pozoviProvajdera(provajder,servis,endpoint,map);
    }


    private Map<String, Object> getHeadersInfo() {

        Map<String, Object> map = new HashMap<String, Object>();

        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            Object value = request.getHeader(key);
            map.put(key, value);
        }
        return map;
    }
}
