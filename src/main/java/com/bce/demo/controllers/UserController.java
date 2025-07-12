package com.bce.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bce.demo.services.UserService;

import jakarta.annotation.PostConstruct;

import com.bce.demo.entity.User;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/say-hello")
    public String sayHello() {
        return "Hola Mundo Spring";
    }

    @GetMapping("/get-users")
    public List<User> getAll() {
        return userService.findAll();
    }

    /*
     * @PostMapping("/add-user")
     * public User createUser(@RequestBody User user){
     * return userService.create(user);
     * }
     */

    @PostMapping("/add-user")
    public ResponseEntity createUser(@RequestBody User user) {
        return new ResponseEntity<User>(userService.create(user), HttpStatus.CREATED);
    }

    @PutMapping("/update-user/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Integer userId, @RequestBody User user) {
        user.setId(userId);
        User updated = userService.update(user);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/get-user-by-id/{userId}")
    public User getById(@PathVariable Integer userId) {
        return userService.getById(userId);
    }

    @DeleteMapping("/delete-user/{userId}")
    public void deleteById(@PathVariable Integer userId) {
        userService.deleteById(userId);
    }

}
