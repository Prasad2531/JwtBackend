package com.jwt.example.JwtExample.controller;

import com.jwt.example.JwtExample.entities.User;
import com.jwt.example.JwtExample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private UserService userService;
    @GetMapping("/users")
    public List<User>  getUser(){
        System.out.println("Getting user!!");
        return userService.getUsers();
    }

    @GetMapping("/user")
    public String getloggedUsername(Principal principal){
        return principal.getName();
    }
}
