package com.GuideAPP_AKS.mpType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/fileType")
@CrossOrigin
public class FileTypeController {
    @Autowired
    private FileTypeService fileTypeService;

    @PostMapping(path = "/addFiletype")
    public ResponseEntity<?> addFileType(@RequestBody FileType fileType){
        FileType fileType1 = fileTypeService.addFileType(fileType);
        return ResponseEntity.ok(fileType1);
    }

    @GetMapping(path = "/getFileType")
    public ResponseEntity<List<FileType>> getAllType(){
        return fileTypeService.getAllType();
    }
}
