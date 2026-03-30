package com.example.hospital.services;

import com.example.hospital.entities.User;
import com.example.hospital.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepo;

    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }
    public User authenticate(String username, String password) {

        User user = userRepo.findByUsername(username);

        if (user == null) {
            throw new RuntimeException("Invalid credentials");
        }

        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid credentials");
        }

        return user;
    }

    // =========================
    // CREATE OR UPDATE USER
    // =========================
    public User saveUser(User user) {
        return userRepo.save(user);
    }

    // =========================
    // GET USERS
    // =========================
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public Optional<User> getUserById(Integer id) {
        return userRepo.findById(id);
    }

    // =========================
    // DELETE USER (ADMIN ONLY)
    // =========================
    public boolean deleteUser(Integer targetUserId, String actingUsername) {
        User actingUser = userRepo.findByUsername(actingUsername);
        if (actingUser != null && actingUser.getRole() == User.Role.ADMIN) {
            userRepo.deleteById(targetUserId);
            return true;
        }
        return false; // not admin, cannot delete
    }

    // =========================
    // AUTHENTICATE USER
    // =========================


    // =========================
    // CHANGE PASSWORD
    // =========================
    public boolean changePassword(String username, String oldPassword, String newPassword) {
        User user = userRepo.findByUsername(username);
        if (user != null && user.getPassword().equals(oldPassword)) {
            user.setPassword(newPassword);
            userRepo.save(user);
            return true;
        }
        return false; // old password incorrect
    }

    // =========================
    // FIND BY USERNAME
    // =========================
    public User findByUsername(String username) {
        return userRepo.findByUsername(username);
    }
}