package uz.weyx.apprelationships.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.weyx.apprelationships.entity.Subject;
import uz.weyx.apprelationships.repository.SubjectRepository;

import javax.persistence.OneToOne;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/subjects")
public class SubjectController {

    @Autowired
    SubjectRepository subjectRepository;


    //read
    @GetMapping
    public List<Subject> getSubjects() {
        return subjectRepository.findAll();
    }

    //create
    @PostMapping
    public String addSubject(@RequestBody Subject subject) {
        boolean name = subjectRepository.existsByName(subject.getName());
        if(name){
            return "bunday fan mavjud";
        }
        subjectRepository.save(subject);
        return "saved";
    }

    //delete
    @DeleteMapping("/{id}")
    public String deleteSubject(@PathVariable Integer id) {
        subjectRepository.deleteById(id);
        return "Deleted";
    }

    //update
    @PutMapping("/{id}")
    public String editSubject(@PathVariable Integer id, @RequestBody Subject subject) {
        Optional<Subject> subjectOptional = subjectRepository.findById(id);
        if (subjectOptional.isPresent()) {
            Subject subject1 = subjectOptional.get();
            subject1.setName(subject.getName());
            subjectRepository.save(subject1);
            return "Edited";
        }
        return "Not found";
    }
}
