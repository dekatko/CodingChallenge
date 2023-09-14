package de.tarent.challenge.store.repository;

import de.tarent.challenge.store.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<Customer, Long> {

    Customer findUserByUsername (String username);

}
