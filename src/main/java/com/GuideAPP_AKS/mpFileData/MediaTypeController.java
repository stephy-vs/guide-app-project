package com.GuideAPP_AKS.mpFileData;

import com.GuideAPP_AKS.mpType.FileType;
import com.GuideAPP_AKS.mpType.FileTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/mediaData")
@CrossOrigin
public class MediaTypeController {
    @Autowired
    private MediaTypeService mediaTypeService;
    @Autowired
    private FileTypeRepo fileTypeRepo;

    @PostMapping(path = "/mpData")
    public ResponseEntity<List<MediaTypeDTO>> addMp3Data(@RequestParam String uId,
                                                         @RequestParam Integer mtId,
                                                         @RequestParam MultipartFile[] files){
        try {
            Optional<FileType> fileTypeOptional =fileTypeRepo .findById(mtId);
            if (fileTypeOptional.isPresent()) {
                FileType fileType = fileTypeOptional.get();
                String fData = fileType.getFileType();
                if (fData != null && "Audio".equalsIgnoreCase(fData)) {
                    List<MediaTypeDTO> responses = new ArrayList<>();
                    for (MultipartFile file : files){
                        responses.add(mediaTypeService.uploadMp3(file,uId));
                    }
                    return new ResponseEntity<>(responses,HttpStatus.OK);
                    //return mediaTypeService.addMp3(files,dtId);
                } else if (fData != null && "Video".equalsIgnoreCase(fData)) {
                    List<MediaTypeDTO> responses = new ArrayList<>();
                    for (MultipartFile file : files){
                        responses.add(mediaTypeService.uploadMp4(file,uId));
                    }
                    return new ResponseEntity<>(responses,HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
                }
            } else {
                return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(path = "/mpData1")
    public ResponseEntity<List<MediaTypeDTO>> addMp3Data1(@RequestParam String uId,
                                                         @RequestParam Integer mtId,
                                                         @RequestParam MultipartFile[] files){
        try {
            Optional<FileType> fileTypeOptional =fileTypeRepo .findById(mtId);
            if (fileTypeOptional.isPresent()) {
                FileType fileType = fileTypeOptional.get();
                String fData = fileType.getFileType();
                if (fData != null && "Audio".equalsIgnoreCase(fData)) {
                    List<MediaTypeDTO> responses = new ArrayList<>();
                    for (MultipartFile file : files){
                        responses.add(mediaTypeService.uploadMp3fs(file,uId));
                    }
                    return new ResponseEntity<>(responses,HttpStatus.OK);
                    //return mediaTypeService.addMp3(files,dtId);
                } else if (fData != null && "Video".equalsIgnoreCase(fData)) {
                    List<MediaTypeDTO> responses = new ArrayList<>();
                    for (MultipartFile file : files){
                        responses.add(mediaTypeService.uploadMp4fs(file,uId));
                    }
                    return new ResponseEntity<>(responses,HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
                }
            } else {
                return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
