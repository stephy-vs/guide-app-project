package com.GuideAPP_AKS.Language;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/dataType1")
@CrossOrigin
public class DataTypeController {
    @Autowired
    private DataTypeService dataTypeService;
    @PostMapping(path = "/addtalk")
    public ResponseEntity<?> addTalk(@RequestBody DataType dataType){
        DataType dataType1 = dataTypeService.addTalk(dataType);
        return ResponseEntity.ok(dataType1);
    }

    @GetMapping(path = "/getTalk")
    public ResponseEntity<List<DataType>> getAllTalk(){
        return dataTypeService.getAllTalk();
    }

}
