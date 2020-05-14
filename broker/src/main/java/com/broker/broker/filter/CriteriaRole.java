package com.broker.broker.filter;

import com.broker.broker.domain.Endpoint;
import com.broker.broker.domain.Role;
import com.broker.broker.domain.UserBroker;


import java.util.*;

public class CriteriaRole implements Criteria{



    @Override
    public Map<String,String> meetCriteria(Map<String,String> map, Endpoint endpoint, UserBroker userBroker) {

        Map<String,String> newMap = new HashMap<String,String>();

        List<String> meetsCriteria = new ArrayList<>();
        String[] attributesWithRoles = endpoint.getFilter().split(";");
        List<Role> rolesUser = userBroker.getRoles();
        for(String attributeWithRole: attributesWithRoles){
            String attribute = attributeWithRole.split(":")[0];
            List<String> roles = Arrays.asList(attributeWithRole.split(":")[1].split(","));
            for(Role roleUser:rolesUser){
                if(roles.contains(roleUser.getName())){
                    meetsCriteria.add(attribute);
                    break;
                }
            }
        }
        for (Map.Entry<String,String> entry : map.entrySet())
            if(meetsCriteria.contains(entry.getKey()))
                newMap.put(entry.getKey(),entry.getValue());

        return newMap;
    }
}
/*
     ime:USER,ADMIN;prezime:USER,ADMIN;jmbg:ADMIN;adresa:ADMIN
*/