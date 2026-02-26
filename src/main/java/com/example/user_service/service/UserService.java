package com.example.user_service.service;

import com.example.user_service.model.User;
import com.example.user_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository repo;

    // Signup: just save the user with plain password
    public User signup(User user) {
        return repo.save(user);
    }

    // Login: check plain text password
    public Optional<User> login(String email, String password) {
        Optional<User> user = repo.findByEmail(email);
        if (user.isPresent() && password.equals(user.get().getPassword())) {
            return user;
        }
        return Optional.empty();
    }

    // Admin adds seller
    public User addSeller(User user) {
        user.setRole("seller");
        return repo.save(user);
    }

    // Get all users
    public List<User> getAllUsers() {
        return repo.findAll();
    }

    // Get user by ID
    public Optional<User> getUserById(Long id) {
        return repo.findById(id);
    }

    // Update user
    public User updateUser(Long id, User updated) {
        return repo.findById(id).map(user -> {
            user.setName(updated.getName());
            user.setEmail(updated.getEmail());
            user.setRole(updated.getRole());
            user.setPassword(updated.getPassword()); // plain text update
            return repo.save(user);
        }).orElse(null);
    }

    // Delete user
    public void deleteUser(Long id) {
        repo.deleteById(id);
    }
}
