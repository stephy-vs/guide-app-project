package com.GuideAPP_AKS.mainHeading;

import com.GuideAPP_AKS.Language.DataType;
import com.GuideAPP_AKS.Language.DataTypeRepo;
import com.GuideAPP_AKS.mainHeading.mainEng.MainTitleEng;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/DataEntry1")
@CrossOrigin
public class MainTitleController {
    @Autowired
    private MainTitleService mainTitleService;
    @Autowired
    private DataTypeRepo dataTypeRepo;

    @PostMapping(path = "/mainT")
    public ResponseEntity<?> addMainTitle(@RequestParam Integer dId, @RequestBody MainDTO mainDTO){
        try {
            Optional<DataType> dataTypeOptional = dataTypeRepo.findById(dId);
            if (dataTypeOptional.isPresent()) {
                DataType dataType = dataTypeOptional.get();
                String tData = dataType.getTalk();
                if (tData != null && "English".equalsIgnoreCase(tData)) {
                    return mainTitleService.addMainTitleEng(mainDTO);
                } else if (tData != null && "Malayalam".equalsIgnoreCase(tData)) {
                    return mainTitleService.addMainTitleMal(mainDTO);
                } else {
                    return new ResponseEntity<>("Language is not present", HttpStatus.BAD_REQUEST);
                }
            } else {
                return new ResponseEntity<>("Language is not present", HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong! ",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(path = "/getMainComplete")
    public ResponseEntity<List<CombinedData>>getAllMainTitleData(@RequestParam Integer dtId){
        try {
            Optional<DataType> dataTypeOptional = dataTypeRepo.findById(dtId);
            if (dataTypeOptional.isPresent()){
                DataType dataType = dataTypeOptional.get();
                String tData = dataType.getTalk();
                if (tData != null && "English".equalsIgnoreCase(tData)){
                    return mainTitleService.getCombinedList();
                } else if (tData != null && "Malayalam".equalsIgnoreCase(tData)) {
                    return mainTitleService.getCombinedListMal();
                }else {
                    return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
                }
            }else {
                return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
