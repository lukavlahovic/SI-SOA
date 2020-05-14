package com.broker.broker.filter;

import com.broker.broker.domain.Endpoint;
import com.broker.broker.domain.UserBroker;

import java.util.Map;

public class Filter {

    private CriteriaRole criteriaRole;

    public Filter()
    {
        criteriaRole = new CriteriaRole();
    }

    public Map<String,String> filterByRole(Map<String,String> map, Endpoint endpoint, UserBroker userBroker)
    {
        return criteriaRole.meetCriteria(map,endpoint,userBroker);
    }

/*
     {
       output:[
       {String: ime},
       {String: prezime},
       {String: jmbg},
       {String:  adresa}
       ]

       role:"USER,ADMIN"
       output_role:[
       {ime,prezime},
       {ime,prezime,jmbg,adresa}
       ]
     }
*/


}