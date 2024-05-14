package com.GuideAPP_AKS.mpFileData.mp4.firstSub;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.web.bind.annotation.CrossOrigin;

@Data
@Entity
@Table(name = "mp4Data1")
@CrossOrigin
public class Mp4Data1 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "fName")
    private String fName;

    @Column(name = "fUrl")
    private String fUrl;

    @Column(name = "dtId")
    private String dtId;
    @PrePersist
    @PreUpdate
    public void setDefault(){
        if (fName==null){
            fName="No Data";
        }if (fUrl==null){
            fUrl="No Data";
        }if (dtId==null){
            dtId="No Data";
        }
    }

    public Mp4Data1() {
    }

    public Mp4Data1(String fName, String fUrl, String dtId) {
        this.fName = fName;
        this.fUrl = fUrl;
        this.dtId = dtId;
    }
}
