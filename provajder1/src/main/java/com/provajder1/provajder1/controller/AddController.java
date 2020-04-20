package com.provajder1.provajder1.controller;

import com.provajder1.provajder1.api.model.AddDTO;
import com.provajder1.provajder1.api.model.SelectDTO;
import com.provajder1.provajder1.api.model.UpdateDTO;
import com.provajder1.provajder1.services.AddService;
import com.provajder1.provajder1.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping(Constants.CATEGORY_BASE_URL)
public class AddController {

    private AddService addService;


    @Autowired
    public AddController(AddService addService){
        this.addService = addService;
    }

    //localhost:8080/api/teski/add?ulo_oznaka=bb&ulo_naziv=cc
//    @RequestMapping(method = RequestMethod.PUT, value = "/add")
//    public ResponseEntity<Boolean> addRow(@RequestParam(value = "ulo_oznaka", required = true)
//                                                  String ulo_oznaka, @RequestParam (value = "ulo_naziv", required = true) String ulo_naziv){
//        return new ResponseEntity<Boolean>(addService.addTK(ulo_oznaka,ulo_naziv), HttpStatus.OK);
//    }
    @RequestMapping(method = RequestMethod.POST , value = "/add")
        public ResponseEntity<Boolean> addRow(@RequestBody AddDTO addDTO){
            return new ResponseEntity<Boolean>(addService.addTK(addDTO), HttpStatus.OK);
        }
    @RequestMapping(method = RequestMethod.POST , value = "/delete")
    public ResponseEntity<Boolean> deleteRow(@RequestBody AddDTO addDTO){
        return new ResponseEntity<Boolean>(addService.deleteTK(addDTO), HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.POST , value = "/update")
    public ResponseEntity<Boolean> updateRow(@RequestBody UpdateDTO addDTO){
        return new ResponseEntity<Boolean>(addService.updateTK(addDTO), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET , value = "/select")
    public ResponseEntity<Map<String,Object>> selectRow(@RequestBody SelectDTO selectDTO){
        return new ResponseEntity<Map<String,Object>>(addService.selectTK(selectDTO), HttpStatus.OK);
    }

}
