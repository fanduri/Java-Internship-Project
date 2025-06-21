package com.NitsaBedianashvili.Notifications.controller;

import com.NitsaBedianashvili.Notifications.model.NotificationPreference;
import com.NitsaBedianashvili.Notifications.service.AdminService;
import com.NitsaBedianashvili.Notifications.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/admin")
@RestController
public class UserAdminController {
    @Autowired
    private NotificationService Notificationservice;

    @Autowired
    private AdminService adminService;


    //page where admins can see all clients information
    @GetMapping("/{ID}/home")
    public void getHomePage(@PathVariable long ID){
        System.out.println(ID);
    //TODO
    }
    @GetMapping("/{ID}/clientInfo")
    public List<NotificationPreference> getAllClientInfo(@PathVariable long ID){
        return adminService.showInformationOfAllClients();
    }


    //page where admins can see notification statuses (if they delived correctly or not etc.)
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
