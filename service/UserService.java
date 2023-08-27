package com.example.space.service;

import com.example.space.model.User;
import com.example.space.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public List<User> listAllUser(){
        return userRepository.findAll();
    }
    public void saveUser(User user){
        userRepository.save(user);
    }
    public User getUser(Integer id){
        return userRepository.findById(id).get();
    }
    public void deleteUser(Integer id){
        userRepository.deleteById(id);
    }
}

