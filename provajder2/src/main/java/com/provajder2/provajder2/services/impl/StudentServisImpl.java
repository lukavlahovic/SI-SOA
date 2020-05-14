package com.provajder2.provajder2.services.impl;

import com.provajder2.provajder2.services.StudentServis;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class StudentServisImpl implements StudentServis {


    @Override
    public Map<String, Object> prikazStudenta() {

        Map<String,Object> mapa = new HashMap<>();
        mapa.put("ime","Pera");
        mapa.put("prezime","Petrovic");
        mapa.put("jmbg","151665164165");
        mapa.put("adresa","askjdhasjd12");
        return mapa;
    }
}
