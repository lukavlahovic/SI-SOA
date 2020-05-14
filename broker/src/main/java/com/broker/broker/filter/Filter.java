package com.broker.broker.filter;

public class Filter {

    private CriteriaRole criteriaRole;

    public Filter()
    {
        criteriaRole = new CriteriaRole();
    }

    public String povratnaVrednost(String json)
    {
        return criteriaRole.meetCriteria(json);
    }


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



}
