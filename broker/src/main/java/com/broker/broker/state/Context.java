package com.broker.broker.state;

import com.broker.broker.domain.Servis;
import com.broker.broker.repository.ServisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

public class Context {

    private ArrayList<ContextAttribute> contextAttributes;
    private ServisRepository servisRepository;

    @Autowired
    public Context(ServisRepository servisRepository){
        this.contextAttributes = new ArrayList<ContextAttribute>();
        this.servisRepository = servisRepository;
        for(Servis servis:servisRepository.findAll()){
            contextAttributes.add(new ContextAttribute(servis.getProvajder().getHost() + servis.getRuta()));
        }
    }

    public boolean isAvailable(String servis){
        for(ContextAttribute contextAttribute: contextAttributes){
            if(contextAttribute.getServis().equals(servis)){
                return contextAttribute.getService();
            }
        }
        System.out.println("Service not found");
        return false;
    }

    public boolean changeState(String servis){
        for(ContextAttribute contextAttribute: contextAttributes){
            if(contextAttribute.getServis().equals(servis)){
                return contextAttribute.changeState();
            }
        }
        System.out.println("Service not found");
        return false;
    }


}
