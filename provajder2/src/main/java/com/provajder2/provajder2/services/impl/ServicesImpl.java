package com.provajder2.provajder2.services.impl;

import com.provajder2.provajder2.services.Services;
import org.springframework.stereotype.Service;

@Service
public class ServicesImpl implements Services {


    @Override
    public boolean prosek() {

        System.out.println("Racunam prosek");
        return true;
    }

    @Override
    public boolean gmail_slanje() {

        System.out.println("Saljem na gmail");
        return true;
    }

    @Override
    public boolean yahoo_mail() {
        System.out.println("Saljem na yahoo");
        return true;
    }
}
