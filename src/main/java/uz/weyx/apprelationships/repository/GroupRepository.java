package uz.weyx.apprelationships.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.weyx.apprelationships.entity.Group;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Integer> {

    List<Group> findAllByFaculty_University_Id(Integer faculty_university_id);
}
