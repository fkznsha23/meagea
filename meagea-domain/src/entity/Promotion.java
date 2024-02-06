package entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class Promotion {
    public Promotion(Animal animal) {
        setAnimal(animal);
    }
    public Promotion(){};

    // 입양 홍보 게시글 정보
    private int no;
    private int animalNo;
    private String introduction;
    private String condition;
    private LocalDate createDate;
    private LocalDate modifyDate;
    private int adoptionUserNo;
    private List<Integer> adoptionFormNo;
    private List<Integer> diaryNo;
}
