package com.broker.broker.services;

import com.broker.broker.domain.Endpoint;
import com.broker.broker.domain.Role;
import com.broker.broker.domain.UserBroker;

import java.util.ArrayList;
import java.util.List;

public interface LoggerServis {

    public Boolean upisi(UserBroker userbroker, List<Role> roles, Endpoint endpoint);
}
