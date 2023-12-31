package com.example.space.controller;

import com.example.space.model.User;
import com.example.space.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(path="/getAll",produces="application/json")
    public List<User> list(){return userService.listAllUser();}

    @GetMapping(path="/{id}",produces="application/json")
    public ResponseEntity<User> get(@PathVariable Integer id){
        try{
            User user=userService.getUser(id);
            return new ResponseEntity<User>(user, HttpStatus.OK);
        }
        catch(NoSuchElementException e){
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/add")
    public String add(@RequestBody User user){
        userService.saveUser(user);
        return "New User added";
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody User user,@PathVariable Integer id){
        try{
            User existUser =userService.getUser(id);
            user.setId(id);
            userService.saveUser(user);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        userService.deleteUser(id);
    }
}

