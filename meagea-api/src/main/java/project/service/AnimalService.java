package project.service;

import entity.Animal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.dto.AnimalForm;
import project.repository.AnimalFileRepository;
import project.repository.AnimalRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnimalService {
    private final AnimalRepository animalRepo;

    public Animal addAnimal(AnimalForm form){
        Animal animal = new Animal(form.getName(), form.getAge(), form.getGender(), form.getWeight(),
                                    form.isNeuter(), form.getKind(), form.getDetail(),form.getPlace(), form.getHealthState(),
                                    form.getActivity(), form.getSociality(), form.getFriendly());

        return animalRepo.save(animal);
    }

    public Animal findAnimalByNo(int no){
        Optional<Animal> animal = animalRepo.findById(no);
        if(animal.isEmpty()){
            throw new NullPointerException("조회 결과 없음");
        }

        return animal.get();
    }
}
