package com.example.app.seveuserrestproject.service;

import com.example.app.seveuserrestproject.entity.User;
import com.example.app.seveuserrestproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public ResponseEntity<User> saveUser(User user){
        User savedUser  = repository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    public ResponseEntity<User> redUserByID(Long idUser){
        Optional<User> userFind = repository.findById(idUser);

        // very interesting solution
        return userFind.map(user -> ResponseEntity.ok().body(user))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<List<User>> redAllUser(){
        List<User> allUsers = repository.findAll();
        if (allUsers.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(allUsers);
    }
    public ResponseEntity<String> deletionUser(Long idUser){
        Optional<User> userFind = repository.findById(idUser);
        if (repository.existsById(idUser)){
            repository.deleteById(idUser);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("User successfully deleted");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
    }

}
