package uz.weyx.apprelationships.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.weyx.apprelationships.entity.University;

@Repository
public interface UniversityRepository extends JpaRepository<University, Integer> {
}
