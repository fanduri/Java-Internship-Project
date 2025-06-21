package com.NitsaBedianashvili.Notifications.controller;

import com.NitsaBedianashvili.Notifications.model.NotificationPreference;
import com.NitsaBedianashvili.Notifications.model.User;
import com.NitsaBedianashvili.Notifications.service.AdminService;
import com.NitsaBedianashvili.Notifications.service.NotificationService;
import com.NitsaBedianashvili.Notifications.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/admin")
@RestController
public class UserAdminController {
    @Autowired
    private NotificationService notificationService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserService userService;


    //page where admins can see all clients information
    @GetMapping("/{ID}/home")
    public void getHomePage(@PathVariable long ID){
        System.out.println(ID);
    //TODO
    }


    @GetMapping("/{ID}/clientInfo")
    public List<NotificationPreference> getAllClientInfo(@PathVariable long ID){
        return adminService.showInformationOfAllClients();
        //TODO: need to check if ID matches an admin account
    }


    //Admin can create a user
    @PostMapping("/{ID}/addUser")
    public User addUser(@RequestBody User user){
       return userService.createUser(user);
    }

    //Admin can update a user
    @PutMapping("/{ID}/updateUser")
    public void updateUserInfo(@RequestBody User user){
         userService.updateUser(user);
    }

    //Admin can delete a user
    @DeleteMapping("/{ID}/deleteUser")
    public void deleteUser(@RequestBody User user){
        userService.deleteAccount(user.getID());
    }
    //I dont think admin should be able to change notification preferance but here anyway
    @PutMapping("/{ID}/updateUserNotifications")
    public void updateUserNotification(@RequestBody NotificationPreference notificationPreference){
        notificationService.UpdateNotification(notificationPreference);

    }
















    //page where admins can see notification statuses (if they delivered correctly or not etc.)
    @GetMapping("/{ID}/notificationStatus")
    public void getAllNotificationStatus(@PathVariable long ID){
        //TODO
    }


    //page where admins can send notifications to chosen clients
    @GetMapping("/{ID}/notificationSendingPage")
    public void getSendingPage(@PathVariable long ID){
        //TODO
    }

    //page where admins can send notifications to chosen clients
    @PostMapping("/{ID}/notificationSendingPage")
    public void sendNotification(@PathVariable long ID){
        //TODO
    }

}
