package uz.weyx.apprelationships.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.weyx.apprelationships.entity.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {


    boolean existsByName(String name);
}
