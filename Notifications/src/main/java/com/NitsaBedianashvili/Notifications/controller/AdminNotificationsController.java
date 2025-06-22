package com.NitsaBedianashvili.Notifications.controller;

import com.NitsaBedianashvili.Notifications.model.Notification;
import com.NitsaBedianashvili.Notifications.service.NotificationService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/notificationHub/admin")
public class AdminNotificationsController {

    private NotificationService notificationService;


 ///////////////////////////SENDING NOTIFICATIONS ////////////////////////////////////////
    @PostMapping("/{ID}/send")
    @PreAuthorize("@userSecurity.isSelf(authentication, #id)")
    public Notification sendNotification(@PathVariable Long ID , @RequestBody Notification notification){
        if (Objects.equals(notification.getSenderID(), ID)){
           return notificationService.sendMessageToUser(notification);
        }
        System.out.println("DENIED SENDING ");
        return null;

        //TODO: ERROR HANDLING
    }
    @PostMapping("/{ID}/sendAll")
    @PreAuthorize("@userSecurity.isSelf(authentication, #id)")
    public void sendNotificationToAllUsers
            (@PathVariable Long ID , @RequestBody String text){
        notificationService.sendMessageToAllUsers(ID,text);
        //TODO: ERROR HANDLING
    }


 /// ////////////////////////READING INFORMATION OF NOTIFICATIONS ///////////////////

    @GetMapping("/{ID}/allNotifications")
    @PreAuthorize("@userSecurity.isSelf(authentication, #id)")
    public List<Notification> getAllNotifications(){
        return notificationService.getAllNotificationsInfo();
        //TODO: ERROR HANDLING
    }

    @GetMapping("/{ID}/allNotifications/sentByMe")
    @PreAuthorize("@userSecurity.isSelf(authentication, #id)")
    public List<Notification> getAllNotifsSentByMe(@PathVariable Long ID){
        return notificationService.getAdminsSentNotificationsInfo(ID);
        //TODO: ERROR HANDLING
    }



}
