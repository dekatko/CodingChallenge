package de.tarent.challenge.store.service;

import de.tarent.challenge.store.model.Customer;
import de.tarent.challenge.store.repository.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) { this.userRepo = userRepo; }

    public List<Customer> retrieveAllUsers() {
        return userRepo.findAll();
    }

    public Customer retrieveUserByUsername(String username) {
        return userRepo.findUserByUsername(username);
    }
}
