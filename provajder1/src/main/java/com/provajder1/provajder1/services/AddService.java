package com.provajder1.provajder1.services;

import com.provajder1.provajder1.api.model.AddDTO;
import com.provajder1.provajder1.api.model.UpdateDTO;

import java.util.Map;

public interface AddService {
    //boolean addTK(String ulo_oznaka, String ulo_naziv);
    boolean addTK(AddDTO addDTO);
    boolean deleteTK(AddDTO addDTO);
    boolean updateTK(UpdateDTO updateDTO);
    Map<String,Object> selectTK(Map<String,String> map);
}
