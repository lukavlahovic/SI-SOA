package com.broker.broker.services.impl;

import com.broker.broker.domain.Endpoint;
import com.broker.broker.domain.Log;
import com.broker.broker.domain.Role;
import com.broker.broker.domain.UserBroker;
import com.broker.broker.repository.LogRepository;
import com.broker.broker.services.LoggerServis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoggerServisImpl implements LoggerServis {

    LogRepository logRepository;

    @Autowired
    public LoggerServisImpl(LogRepository logRepository)
    {
        this.logRepository = logRepository;
    }


    @Override
    public Boolean upisi(UserBroker userbroker, List<Role> roles, Endpoint endpoint) {

        Boolean pristup = false;

        for(Role r: roles) {
            pristup = endpoint.getListaRolova().contains(r);
            if(pristup)
            {
                Log log = new Log();
                log.setPristup(pristup);
                log.setRole(r);
                log.setUserBroker(userbroker);
                log.setEndpoint(endpoint);
                logRepository.save(log);
                return true;
            }
        }
        if (!pristup) {
            Log log = new Log();
            log.setPristup(pristup);
            log.setRole(userbroker.getRoles().get(0));
            log.setUserBroker(userbroker);
            log.setEndpoint(endpoint);
            logRepository.save(log);
            return false;
        }
        return false;
    }
}
