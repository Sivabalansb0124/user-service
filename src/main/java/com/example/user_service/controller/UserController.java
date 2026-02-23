package com.example.user_service.controller;

import com.example.user_service.model.User;
import com.example.user_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository repo;

    @PostMapping
    public User addUser(@RequestBody User user) {
        return repo.save(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User user) {
        user.setId(id);
        return repo.save(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        repo.deleteById(id);
    }
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id)
    {
        return repo.findById(id).orElse(null);
    }
    @GetMapping
    public List<User> getAllUsers() {
        return repo.findAll();
    }
}
