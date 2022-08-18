package uz.weyx.apprelationships.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.weyx.apprelationships.entity.Faculty;
import uz.weyx.apprelationships.entity.Group;
import uz.weyx.apprelationships.payload.GroupDto;
import uz.weyx.apprelationships.repository.FacultyRepository;
import uz.weyx.apprelationships.repository.GroupRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/groups")
public class GroupController {

    @Autowired
    GroupRepository groupRepository;
    @Autowired
    FacultyRepository facultyRepository;

    //Yuqori turuvchi uchun
    //read
    @GetMapping
    public List<Group> getGroups(){
       return groupRepository.findAll();
    }


    //xodim uchun
    //read
    @GetMapping("/byUniversityId/{universityId}")
    public  List<Group> getGroupsByUniversiyId(@PathVariable Integer universityId){
        List<Group> allByFaculty_university_id = groupRepository.findAllByFaculty_University_Id(universityId);
        return allByFaculty_university_id;
    }

    @PostMapping
    public String add(@RequestBody GroupDto groupDto){
        Group group=new Group();
        group.setName(groupDto.getName());
        Optional<Faculty> optionalFaculty = facultyRepository.findById(groupDto.getFacultyId());
        if(!optionalFaculty.isPresent())
            return "such faculty not found";

        group.setFaculty(optionalFaculty.get());
        groupRepository.save(group);
        return "added group";
    }
}
