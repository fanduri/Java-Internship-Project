package com.NitsaBedianashvili.Notifications.controller;

import com.NitsaBedianashvili.Notifications.model.User;
import com.NitsaBedianashvili.Notifications.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserClientController {

    @Autowired
    private UserService service;


    //page where everyone ends up, you can chose to register or log in
    @GetMapping("/")
    public void hello(){
        System.out.println( "Hello User, Register or Log In");
    }



    @PostMapping("/register")
    public void addUser(@RequestBody User user){
        System.out.println("hello "+user);
        service.createUser(user);
    }




}
