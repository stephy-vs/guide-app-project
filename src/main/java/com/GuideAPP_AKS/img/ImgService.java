package com.GuideAPP_AKS.img;

import com.GuideAPP_AKS.img.firstSubHeading.ImgSubFirst;
import com.GuideAPP_AKS.img.firstSubHeading.ImgSubFirstRepo;
import com.GuideAPP_AKS.img.mainHeading.ImgData;
import com.GuideAPP_AKS.img.mainHeading.ImgRepo;
import com.GuideAPP_AKS.mainHeading.mainEng.MainTitleEngRepo;
import com.GuideAPP_AKS.mainHeading.mainMal.MainTitleMalRepo;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;

@Service
@Slf4j
public class ImgService {
    @Autowired
    private ImgRepo imgRepo;
    @Value("${aws.s3.bucketName}")
    private String bucketName;
    @Autowired
    private AmazonS3 s3Client;
    @Autowired
    private ImgSubFirstRepo imgSubFirstRepo;

    private File convertMultiPartFileToFile(MultipartFile file){
        File convertedFile = new File(file.getOriginalFilename());
        try(FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());

        }catch (Exception e){
            log.error("Error converting multipartFile to file",e);
        }
        return convertedFile;
    }
    public ImgData uploadJPG(MultipartFile file, String englishUId, String malUid) {
        File fileObj = convertMultiPartFileToFile(file);
        String fileName =System.currentTimeMillis()+"_"+file.getOriginalFilename();
        s3Client.putObject(new PutObjectRequest(bucketName,fileName,fileObj));
        fileObj.delete();
        String fileUrl = s3Client.getUrl(bucketName,fileName).toString();
        ImgData imgData = new ImgData(fileName,fileUrl,englishUId,malUid);
        imgRepo.save(imgData);
        return imgData;
    }

    public ImgSubFirst uploadData1(MultipartFile file, String englishUId, String malUid) {
        File fileObj = convertMultiPartFileToFile(file);
        String fileName =System.currentTimeMillis()+"_"+file.getOriginalFilename();
        s3Client.putObject(new PutObjectRequest(bucketName,fileName,fileObj));
        fileObj.delete();
        String fileUrl = s3Client.getUrl(bucketName,fileName).toString();
        ImgSubFirst imgSubFirst = new ImgSubFirst(fileName,fileUrl,englishUId,malUid);
        imgSubFirstRepo.save(imgSubFirst);
        return imgSubFirst;
    }
}
