package uz.weyx.apprelationships.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.weyx.apprelationships.entity.Faculty;
import uz.weyx.apprelationships.entity.University;
import uz.weyx.apprelationships.payload.FacultyDto;
import uz.weyx.apprelationships.repository.FacultyRepository;
import uz.weyx.apprelationships.repository.UniversityRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/faculty ")
public class FacultyController {

    @Autowired
    FacultyRepository facultyRepository;

    @Autowired
    UniversityRepository universityRepository;

    @PostMapping
    public String addFaculty(@RequestBody FacultyDto facultyDto) {
        boolean exists = facultyRepository.existsByNameAndUniversityId(facultyDto.getName(), facultyDto.getUniversityId());
        if (exists)
            return "This University such faculty exist";
        Faculty faculty = new Faculty();
        faculty.setName(facultyDto.getName());
        Optional<University> optionalUniversity = universityRepository.findById(facultyDto.getUniversityId());
        if (!optionalUniversity.isPresent())
            return "University not found";

        faculty.setUniversity(optionalUniversity.get());
        facultyRepository.save(faculty);
        return "saved";
    }

    //UNIVERSITET HODIMI UCHUN
    @GetMapping("byUniversity/{universityId}")
    public List<Faculty> getFacultiesByUniversityId(@PathVariable Integer universiyuId) {
        List<Faculty> allByUniversityId = facultyRepository.findAllByUniversityId(universiyuId);
        return allByUniversityId;
    }

    //VAZIRLIK UCHUN
    @GetMapping
    public List<Faculty> get() {
        return facultyRepository.findAll();
    }

    @DeleteMapping("{id}")
    public String deleteFaculyu(@PathVariable Integer id) {
        try {
            facultyRepository.deleteById(id);
            return "Deleted";
        } catch (Exception e) {
            return "Eror in deleting";
        }
    }
    @PutMapping("{id}")
    public String editFaculty(@PathVariable Integer id,@RequestBody FacultyDto facultyDto){
        Optional<Faculty> optionalFaculty = facultyRepository.findById(id);
        if(optionalFaculty.isPresent()){
            Faculty faculty = optionalFaculty.get();
            Optional<University> optionalUniversity = universityRepository.findById(facultyDto.getUniversityId());
            if(!optionalUniversity.isPresent()){
                return "Universitet not found";
            }
            faculty.setName(facultyDto.getName());
            faculty.setUniversity(optionalUniversity.get());
            facultyRepository.save(faculty);
            return "faculty edited";

        }
        return "faculty not found";
    }
}

