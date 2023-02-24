package jwttest.jwt.Repositories;

import jwttest.jwt.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
Customer findCusomerByEmail(String email);
}
