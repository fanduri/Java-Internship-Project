package com.NitsaBedianashvili.Notifications.controller.api;

import com.NitsaBedianashvili.Notifications.model.Notification;
import com.NitsaBedianashvili.Notifications.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@RestController
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/notificationHub/admin")
public class AdminNotificationsController {

    @Autowired
    private NotificationService notificationService;


 ///////////////////////////SENDING NOTIFICATIONS ////////////////////////////////////////
    @PostMapping("/{id}/send")
    @PreAuthorize("@userSecurity.isSelf(authentication, #id)")
    public Notification sendNotification(@PathVariable Long id , @RequestBody Notification notification){
        try {
            if (!notification.getSenderID().equals(id)) {
                System.out.println("DENIED SENDING");
                return null;
            }
            return notificationService.sendMessageToUser(notification);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error sending notification: " + e.getMessage());
        }
    }
    @PostMapping("/{id}/sendAll")
    @PreAuthorize("@userSecurity.isSelf(authentication, #id)")
    public void sendNotificationToAllUsers
            (@PathVariable Long id , @RequestBody String text){

        try {
            notificationService.sendMessageToAllUsers(id, text);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error sending notifications to all users: " + e.getMessage());
        }
    }


 /// ////////////////////////READING INFORMATION OF NOTIFICATIONS ///////////////////

    @GetMapping("/{id}/allNotifications")
    @PreAuthorize("@userSecurity.isSelf(authentication, #id)")
    public List<Notification> getAllNotifications(){
        try {
            return notificationService.getAllNotificationsInfo();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error retrieving all notifications: " + e.getMessage());
        }
    }

    @GetMapping("/{id}/allNotifications/sentByMe")
    @PreAuthorize("@userSecurity.isSelf(authentication, #id)")
    public List<Notification> getAllNotifsSentByMe(@PathVariable Long id){
        try {
            return notificationService.getAdminsSentNotificationsInfo(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error retrieving sent notifications: " + e.getMessage());
        }
    }

}
