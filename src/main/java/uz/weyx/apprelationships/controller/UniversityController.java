package uz.weyx.apprelationships.controller;


import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.weyx.apprelationships.entity.Address;
import uz.weyx.apprelationships.entity.University;
import uz.weyx.apprelationships.payload.UniversityDto;
import uz.weyx.apprelationships.repository.AddressRepository;
import uz.weyx.apprelationships.repository.UniversityRepository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/faculty")
public class UniversityController {

    @Autowired
    UniversityRepository universityRepository;
    @Autowired
    AddressRepository addressRepository;

    //read
    @GetMapping
    public List<University> getUniversityes() {
        return universityRepository.findAll();
    }

    //creat
    @PostMapping
    public String addUniversity(@RequestBody UniversityDto universityDto) {
        //yangi address ochdik
        Address address = new Address();
        address.setCity(universityDto.getCity());
        address.setDistrict(universityDto.getDistrict());
        address.setStreet(universityDto.getStreet());
        //yasab olingan addresni db ga save qildik
        Address savedAddress = addressRepository.save(address);

        //yangi univer ocdik
        University university = new University();
        university.setName(universityDto.getName());
        university.setAddress(savedAddress);
        universityRepository.save(university);

        return "university saved";
    }

    //delete
    @DeleteMapping("/university/{id}")
    public String deletedUniversity(@PathVariable Integer id) {
        universityRepository.deleteById(id);
        return "Deleted";
    }

    //update
    @PutMapping("/university/{id}")
    public String editUniversity(@PathVariable Integer id, @RequestBody UniversityDto universityDto){
        Optional<University> optionalUniversity = universityRepository.findById(id);
        if(optionalUniversity.isPresent()){
            University university = optionalUniversity.get();
            university.setName(universityDto.getName());

            Address address = university.getAddress();
            address.setCity(universityDto.getCity());
            address.setDistrict(universityDto.getDistrict());
            address.setStreet(universityDto.getStreet());
            addressRepository.save(address);

            universityRepository.save(university);
            return "edited";
        }
        return "University not foundet";
    }
}
