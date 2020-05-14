package com.broker.broker.filter;

import com.broker.broker.domain.Endpoint;
import com.broker.broker.domain.UserBroker;

import java.util.Map;

public interface Criteria {

    public Map<String,String> meetCriteria(Map<String,String> map, Endpoint endpoint, UserBroker userBroker);

}