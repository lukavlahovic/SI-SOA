package com.provajder2.provajder2.services.impl;

import com.provajder2.provajder2.services.Services;
import org.springframework.stereotype.Service;

@Service
public class ServicesImpl implements Services {


    @Override
    public boolean prosek(String test) {
        System.out.println("OBJEKAT IZ DRUGOG SERVISA " + test);
        System.out.println("Racunam prosek");
        return true;
    }

    @Override
    public boolean gmail_slanje() {
        for(int i=0;i<1000000000;i++){
        }
        System.out.println("Saljem na gmail");
        return true;
    }

    @Override
    public boolean yahoo_mail() {
        for(int i=0;i<1000000000;i++){
        }
        System.out.println("Saljem na yahoo");
        return true;
    }
}
