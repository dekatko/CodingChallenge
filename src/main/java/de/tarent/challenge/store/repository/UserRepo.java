package de.tarent.challenge.store.repository;

import de.tarent.challenge.store.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {

    User findUserByUsername (String username);

}
