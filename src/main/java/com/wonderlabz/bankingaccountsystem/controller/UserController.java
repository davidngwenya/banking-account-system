package com.wonderlabz.bankingaccountsystem.controller;

import com.wonderlabz.bankingaccountsystem.model.User;
import com.wonderlabz.bankingaccountsystem.service.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bank/users")
public class UserController {

    private SystemUserService systemUserService;

    @Autowired
    public UserController(SystemUserService systemUserService){
        this.systemUserService = systemUserService;
    }

    @GetMapping("/all")
    public List<User> getAllUsers(){
        return systemUserService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") Long id){
        return systemUserService.getUserById(id);
    }

    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User user){
        User newUser = systemUserService.saveUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User user){
        User updatedUser = systemUserService.updateUser(user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") Long id){
        systemUserService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
