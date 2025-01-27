package com.society.controller;

import com.society.entity.User;
import com.society.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/{userid}")
    public ResponseEntity<User> getUser(@PathVariable("userid") String id){
        User user= userRepository.getReferenceById(id);
        return new ResponseEntity<>(user, HttpStatusCode.valueOf(200));
    }
}
