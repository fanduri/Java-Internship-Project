package com.NitsaBedianashvili.Notifications.controller;

import com.NitsaBedianashvili.Notifications.model.NotificationPreference;
import com.NitsaBedianashvili.Notifications.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserAdminController {
    @Autowired
    private NotificationService service;

    //page where admins can see all clients information
    @GetMapping("/admin/{ID}/home")
    public void getHomePage(@PathVariable long ID){
        System.out.println(ID);
    //TODO
    }
    @GetMapping("/admin/{ID}/clientInfo")
    public List<NotificationPreference> getAllClientInfo(@PathVariable long ID){
        return service.showAllPreferances();
    }


    //page where admins can see notification statuses (if they delived correctly or not etc.)
    @GetMapping("/admin/{ID}/notificationStatus")
    public void getAllNotificationStatus(@PathVariable long ID){
        //TODO
    }


    //page where admins can send notifications to chosen clients
    @GetMapping("/admin/{ID}/notificationSendingPage")
    public void getSendingPage(@PathVariable long ID){
        //TODO
    }

    //page where admins can send notifications to chosen clients
    @PostMapping("/admin/{ID}/notificationSendingPage")
    public void sendNotification(@PathVariable long ID){
        //TODO
    }

}
