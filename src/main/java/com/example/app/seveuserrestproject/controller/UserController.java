package com.example.app.seveuserrestproject.controller;

import com.example.app.seveuserrestproject.entity.User;
import com.example.app.seveuserrestproject.service.UserService;
import lombok.experimental.Delegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService service;


    @GetMapping("/allUsers")
    public ResponseEntity<List<User>> getAllUsers(){
        return service.redAllUser();
    }

    @GetMapping
    public ResponseEntity<String> hello(){
        return ResponseEntity.status(HttpStatus.OK).body("Hello bro!");
    }



    @PostMapping("/create")
    public ResponseEntity<User> create(@RequestBody User user){
        return service.saveUser(User.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build());
    }
    @GetMapping("/getById/{id}")
    public ResponseEntity<User> getById(@PathVariable Long id){
        return service.redUserByID(id);
    }

    @DeleteMapping("/delet/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        return service.deletionUser(id);
    }

}
