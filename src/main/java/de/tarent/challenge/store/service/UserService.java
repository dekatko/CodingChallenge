package de.tarent.challenge.store.service;

import de.tarent.challenge.store.model.User;
import de.tarent.challenge.store.repository.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) { this.userRepo = userRepo; }

    public List<User> retrieveAllUsers() {
        return userRepo.findAll();
    }

    public User retrieveUserByUsername(String username) {
        return userRepo.findUserByUsername(username);
    }
}
