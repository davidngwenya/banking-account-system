package com.wonderlabz.bankingaccountsystem.service;

import com.wonderlabz.bankingaccountsystem.model.User;
import com.wonderlabz.bankingaccountsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class SystemUserService {

    private UserRepository userRepository;

    @Autowired
    public SystemUserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User saveUser(User user){}

    public User updateUser(User user){}

    public User getUser(User user){}

    public User deleteUser(User user){}

    public User getAllUsers(User user){}

}
