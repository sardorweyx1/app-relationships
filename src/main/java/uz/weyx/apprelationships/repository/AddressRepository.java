package uz.weyx.apprelationships.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.weyx.apprelationships.entity.Address;

public interface AddressRepository extends JpaRepository<Address,Integer> {
}
