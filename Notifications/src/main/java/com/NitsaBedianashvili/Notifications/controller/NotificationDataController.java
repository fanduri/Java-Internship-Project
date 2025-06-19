package com.NitsaBedianashvili.Notifications.controller;

import com.NitsaBedianashvili.Notifications.model.NotificationPreference;
import com.NitsaBedianashvili.Notifications.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
public class NotificationDataController {

//    @Autowired
//    private NotificationService service;

//    @GetMapping("/notifications")
//    public List<NotificationPreference> getAllNotificationTable(){
//        return service.showAllPreferances();
//    }
//
//    @GetMapping("/notifications/{ID}")
//    public NotificationPreference getByID(@PathVariable int ID){
//        return service.showPreferancesByID(ID);
//    }
//
//    @PutMapping("/notifications")
//    public void addNotifPreferance(@RequestBody NotificationPreference notificationPreference){
//        System.out.println("hello "+notificationPreference);
//        service.putInNotifList(notificationPreference);
//    }
//
//    @DeleteMapping("/notifications")
//    public void deleteNotiFPreferance(@RequestBody NotificationPreference notificationPreference){
//        service.deleteNotifPreferance(notificationPreference);
//    }
//

//    @GetMapping("/home/{ID}/notificationMenu")
//    public NotificationPreference getNotificationInfo(@PathVariable long ID){
//        return service.showPreferancesByID(ID);
//    }


}
