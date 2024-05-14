package com.GuideAPP_AKS.firstSubHeading.malayalam;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "firstSubMal")
public class FirstSubMalayalam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "ref")
    private String ref;

    @Column(name = "fsUid")
    private String fsUid;

    @Column(name ="mainUid")
    private String mainUid;

    public FirstSubMalayalam() {
    }

    @PrePersist
    @PreUpdate
    public void setDefault(){
        if (description==null){
            description="No Data";
        }if (ref==null){
            ref="No Data";
        }
    }
}
