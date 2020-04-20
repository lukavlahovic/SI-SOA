package com.provajder1.provajder1.services.impl;

import com.provajder1.provajder1.api.mappers.AddMapper;
import com.provajder1.provajder1.api.model.AddDTO;
import com.provajder1.provajder1.api.model.SelectDTO;
import com.provajder1.provajder1.api.model.UpdateDTO;
import com.provajder1.provajder1.bootstrap.Bootstrap;
import com.provajder1.provajder1.services.AddService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AddServiceImpl implements AddService {

    private AddMapper addMapper;
    private Bootstrap bootstrap;

    @Autowired
    public AddServiceImpl(AddMapper addMapper, Bootstrap bootstrap){
        this.addMapper = addMapper;
        this.bootstrap = bootstrap;
    }

//    @Override
//    public boolean addTK(String ulo_oznaka, String ulo_naziv) {
//        String command = "INSERT INTO `ROLE` (`ULO_OZNAKA`,`ULO_NAZIV`) VALUES ('" + ulo_oznaka + "','" + ulo_naziv + "');";
//        System.out.println("COMANADA " + command);
//        bootstrap.getTemplate().execute(command);
//        return true;
//    }
    public boolean addTK(AddDTO addDTO) {
        String command = "INSERT INTO `" + addDTO.getEntitet() + "` (`"; //ULO_OZNAKA`,`ULO_NAZIV`) VALUES ('" + ulo_oznaka + "','" + ulo_naziv + "');";
        int i = 0;
        for(Map.Entry<String,String> entry : addDTO.getAtributi().entrySet())
        {
            command += entry.getKey() + "`";
            if(i < addDTO.getAtributi().size() - 1)
            {
                command += ",`";
            }
            i++;
        }
        command += ") VALUES ('";
        int j = 0;
        for(Map.Entry<String,String> entry : addDTO.getAtributi().entrySet())
        {
            command += entry.getValue() + "'";
            if(j < addDTO.getAtributi().size() - 1)
            {
                command += ",'";
            }
            j++;
        }
        command += ");";

        //System.out.println("Entitet " + addDTO.getEntitet() + " atributi " + addDTO.getAtributi().toString());
        bootstrap.getTemplate().execute(command);
        return true;
    }

    @Override
    public boolean deleteTK(AddDTO addDTO) {

        String command = "DELETE FROM `" + addDTO.getEntitet() + "` WHERE "; //ULO_OZNAKA`,`ULO_NAZIV`) VALUES ('" + ulo_oznaka + "','" + ulo_naziv + "');";

        for(Map.Entry<String,String> entry : addDTO.getAtributi().entrySet())
        {
            command += "`"+entry.getKey() + "`";
            command += "=";
            command += "'"+entry.getValue()+"';";

        }
        bootstrap.getTemplate().execute(command);
        return true;
    }

    public Map<String,Object> selectTK(SelectDTO addDTO) {
        String command = "SELECT " + addDTO.getAtributi() + " FROM " + addDTO.getEntitet(); //ULO_OZNAKA`,`ULO_NAZIV`) VALUES ('" + ulo_oznaka + "','" + ulo_naziv + "');";


        //System.out.println("Entitet " + addDTO.getEntitet() + " atributi " + addDTO.getAtributi().toString());
        List results = bootstrap.getTemplate().queryForList(command);
        Map<String,Object> mapa = new HashMap<>();
        for(Object r : ((Map)results.get(0)).keySet())
        {
            mapa.put((String)r,new ArrayList<String>());
        }
        for(Object result: results)
        {
            Map map = (Map) result;
            for(Object key: map.keySet())
            {
                ((ArrayList<String>)mapa.get(key)).add((String) map.get(key));
            }
        }
        System.out.println(mapa);
        return mapa;
    }

    public boolean updateTK(UpdateDTO addDTO) {
        String command = "UPDATE `" + addDTO.getEntitet() + "` SET `"; //ULO_OZNAKA`,`ULO_NAZIV`) VALUES ('" + ulo_oznaka + "','" + ulo_naziv + "');";
        int i = 0;
        for(Map.Entry<String,String> entry : addDTO.getAtributi().entrySet())
        {
            command += entry.getKey() + "`";
            command += "=";
            command += "'"+entry.getValue()+"'";
            if(i < addDTO.getAtributi().size() - 1)
            {
                command += ",`";
            }
            i++;
        }

        command += " WHERE ";
        System.out.println(addDTO.toString());
        for(Map.Entry<String,String> entry : addDTO.getStaravrednost().entrySet())
        {
            command += "`"+entry.getKey() + "`";
            command += "=";
            command += "'"+entry.getValue()+"';";
        }

        //System.out.println("Entitet " + addDTO.getEntitet() + " atributi " + addDTO.getAtributi().toString());
        bootstrap.getTemplate().execute(command);
        return true;
    }
}
