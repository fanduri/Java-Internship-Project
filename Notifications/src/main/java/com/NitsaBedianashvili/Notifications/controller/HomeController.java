package com.NitsaBedianashvili.Notifications.controller;

import com.NitsaBedianashvili.Notifications.exception.DuplicateUserException;
import com.NitsaBedianashvili.Notifications.exception.InvalidUserDataException;
import com.NitsaBedianashvili.Notifications.model.User;
import com.NitsaBedianashvili.Notifications.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HomeController {

    @Autowired
    UserService userService;

    //page where everyone ends up, you can choose to register or log in
    @GetMapping("/")
    public void hello(){
        System.out.println( "Hello User, Register or Log In");
    }

    @PostMapping("/registerUser")
    public ResponseEntity<?> addUser(@RequestBody User user) throws InvalidUserDataException, DuplicateUserException {
        if (user == null || user.getUsername() == null || user.getPassword() == null) {
            return ResponseEntity.badRequest().body("Error: Username and password are required.");
        }
        User user1= userService.createUser(user);
        return ResponseEntity.ok(user1);
    }

    @PostMapping("/registerUsers")
    public void addUsers(@RequestBody User ... users) throws InvalidUserDataException, DuplicateUserException {
        userService.createUsers(users);
        //TODO: needs error handling
    }
}
