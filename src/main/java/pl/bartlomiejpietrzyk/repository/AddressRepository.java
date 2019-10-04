package pl.bartlomiejpietrzyk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.bartlomiejpietrzyk.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
