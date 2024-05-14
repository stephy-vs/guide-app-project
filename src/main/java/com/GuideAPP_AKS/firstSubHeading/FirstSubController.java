package com.GuideAPP_AKS.firstSubHeading;

import com.GuideAPP_AKS.Language.DataType;
import com.GuideAPP_AKS.Language.DataTypeRepo;
import com.GuideAPP_AKS.firstSubHeading.english.FirstSubEnglish;
import com.GuideAPP_AKS.firstSubHeading.english.FirstSubEnglishRepo;
import com.GuideAPP_AKS.firstSubHeading.malayalam.FirstSubMalayalamRepo;
import com.GuideAPP_AKS.mainHeading.CombinedData;
import com.GuideAPP_AKS.mainHeading.MainDTO;
import com.GuideAPP_AKS.mainHeading.mainEng.MainTitleEng;
import com.GuideAPP_AKS.mainHeading.mainEng.MainTitleEngRepo;
import com.GuideAPP_AKS.mainHeading.mainMal.MainTitleMal;
import com.GuideAPP_AKS.mainHeading.mainMal.MainTitleMalRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/DataEntry2")
@CrossOrigin
public class FirstSubController {
    @Autowired
    private FirstSubService firstSubService;
    @Autowired
    private FirstSubEnglishRepo firstSubEnglishRepo;
    @Autowired
    private FirstSubMalayalamRepo firstSubMalayalamRepo;
    @Autowired
    private MainTitleMalRepo mainTitleMalRepo;
    @Autowired
    private MainTitleEngRepo mainTitleEngRepo;
    @Autowired
    private DataTypeRepo dataTypeRepo;

    @PostMapping(path = "/firstSub")
    public ResponseEntity<?>addFirstSubData(@RequestParam String uId,
                                            @RequestBody MainDTO mainDTO){
        try {
            Optional<MainTitleMal> malOptional =mainTitleMalRepo.findBymMalUid(uId);



            MainTitleEng mainTitleEng = mainTitleEngRepo.findBymEngUid(uId);
            if (mainTitleEng!=null){
                String engId = mainTitleEng.getMEngUid();
                if (engId.equals(uId)){
                    return firstSubService.addSubDataMalayalam(uId,mainDTO);
                }
            }else {
                if (malOptional.isPresent()){
                    MainTitleMal mainTitleMal = malOptional.get();
                    String malId = mainTitleMal.getMMalUid();
                    if (uId.equals(malId)){
                        return firstSubService.addSubDataEnglish(uId,mainDTO);
                    }
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(path = "/getFirstSubComplete")
    public ResponseEntity<List<CombinedDataSub>>getAllMainTitleData(@RequestParam Integer dtId){
        try {
            Optional<DataType> dataTypeOptional = dataTypeRepo.findById(dtId);
            if (dataTypeOptional.isPresent()){
                DataType dataType = dataTypeOptional.get();
                String tData = dataType.getTalk();
                if (tData != null && "English".equalsIgnoreCase(tData)){
                    return firstSubService.getCombinedListEng();
                } else if (tData != null && "Malayalam".equalsIgnoreCase(tData)) {
                    return firstSubService.getCombinedListMal();
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
