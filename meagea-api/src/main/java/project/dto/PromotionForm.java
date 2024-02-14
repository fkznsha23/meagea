package project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.List;

@Getter
public class PromotionForm implements Serializable {
    public PromotionForm(String title, String name, List<MultipartFile> multiList, int age, double weight,
                         boolean neuter, String kind, String detail, String place, int healthState, int activity,
                         int sociality, int friendly, String introduction, String condition) {
        this.title = title;
        this.name = name;
        this.multiList = multiList;
        this.age = age;
        this.weight = weight;
        this.neuter = neuter;
        this.kind = kind;
        this.detail = detail;
        this.place = place;
        this.healthState = healthState;
        this.activity = activity;
        this.sociality = sociality;
        this.friendly = friendly;
        this.adoptionState = false;
        this.introduction = introduction;
        this.condition = condition;
    }

    public PromotionForm(){}

    private String title;
    private String name;
    @JsonProperty
    private List<MultipartFile> multiList;
    private int age;
    private double weight;
    private boolean neuter;
    private String kind;
    private String detail;
    private String place;
    private int healthState;
    private int activity;
    private int sociality;
    private int friendly;
    private boolean adoptionState;
    private String introduction;
    private String condition;
}
