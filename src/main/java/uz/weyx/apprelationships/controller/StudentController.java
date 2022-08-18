package uz.weyx.apprelationships.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uz.weyx.apprelationships.entity.Student;
import uz.weyx.apprelationships.repository.StudentRepository;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentRepository studentRepository;
    //VAZIRLIK UCHUN
    @GetMapping("/forMinsty")
    public Page<Student> getStudentForMinistry(@RequestParam int page){
        Pageable pageable=PageRequest.of(page,10);
        Page<Student> studentPage = studentRepository.findAll(pageable);
        return studentPage;
    }
    //UNIVERSITET UCHUN
    @GetMapping("/forUniversity/{universityId}")
    public Page<Student> getStudentForUniversity(@PathVariable Integer universityId, @RequestParam int page){
        Pageable pageable=PageRequest.of(page,10);
        Page<Student> studentPageUniv = studentRepository.findAllByGroup_Faculty_UniversityId(universityId, pageable);
        return studentPageUniv;
    }
      @GetMapping("/forFaculty/{facultyId}")
    public Page<Student> getStudentForFaculty(@PathVariable Integer facultyId, @RequestParam int page){
        Pageable pageable=PageRequest.of(page,10);
          return studentRepository.findAllByGroup_FacultyId(facultyId, pageable);
    }

    @GetMapping("/student/{groupId}")
    public Page<Student> getStudentForGroup(@PathVariable Integer groupId,@RequestParam int page){
        Pageable pageable=PageRequest.of(page,10);
        return studentRepository.findAllByGroupId(groupId, pageable);
    }

}
