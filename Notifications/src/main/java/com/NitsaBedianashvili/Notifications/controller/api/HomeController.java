package com.NitsaBedianashvili.Notifications.controller.api;

import com.NitsaBedianashvili.Notifications.exception.DuplicateUserException;
import com.NitsaBedianashvili.Notifications.exception.InvalidUserDataException;
import com.NitsaBedianashvili.Notifications.model.User;
import com.NitsaBedianashvili.Notifications.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


@RestController
public class HomeController {

    @Autowired
    UserService userService;

    //page where everyone ends up, you can choose to register or log in
    @GetMapping("/")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Welcome! Please register or log in.");
    }


    @PostMapping("/registerUser")
    public ResponseEntity<?> addUser(@RequestBody User user) throws InvalidUserDataException, DuplicateUserException {
        try {
            if (user == null || user.getUsername() == null || user.getPassword() == null) {
                return ResponseEntity.badRequest().body("Error: Username and password are required.");
            }
            User user1 = userService.createUser(user);
            return ResponseEntity.ok(user1);
        } catch (InvalidUserDataException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid user data: " + e.getMessage());
        } catch (DuplicateUserException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User already exists: " + e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error registering user: " + e.getMessage());
        }
    }

    @PostMapping("/registerUsers")
    public ResponseEntity<Object> addUsers(@RequestBody User ... users) throws InvalidUserDataException, DuplicateUserException {
        try {
            userService.createUsers(users);
            return ResponseEntity.ok().build();
        } catch (InvalidUserDataException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid user data: " + e.getMessage());
        } catch (DuplicateUserException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Duplicate user found: " + e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error registering multiple users: " + e.getMessage());
        }
    }
}
