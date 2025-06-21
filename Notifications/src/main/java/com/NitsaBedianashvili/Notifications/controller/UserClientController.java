package com.NitsaBedianashvili.Notifications.controller;

import com.NitsaBedianashvili.Notifications.model.NotificationPreference;
import com.NitsaBedianashvili.Notifications.model.User;
import com.NitsaBedianashvili.Notifications.service.NotificationService;
import com.NitsaBedianashvili.Notifications.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserClientController {

    @Autowired
    private UserService userService;

    @Autowired
    NotificationService notificationService;


    //page where everyone ends up, you can chose to register or log in
    @GetMapping("/")
    public void hello(){
        System.out.println( "Hello User, Register or Log In");
    }

    @PostMapping("/register")
    public void addUser(@RequestBody User user){
        System.out.println("hello "+user);
        userService.createUser(user);
    }

    @GetMapping("/client/{ID}")
    public NotificationPreference getNotificationInformation(@PathVariable long ID) {
        return userService.getUserPreferenceByID(ID);
    }

    @PutMapping("/client/{ID}")
    public NotificationPreference updateNotificationPreferance(@PathVariable long ID,
                                                            @RequestBody NotificationPreference notificationPreference){
        return notificationService.UpdateNotification( notificationPreference);
    }

    @DeleteMapping("/client/{ID}")
    public void deleteAccount (@PathVariable Long ID){
         userService.deleteAccount(ID);
    }





}
