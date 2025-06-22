package com.NitsaBedianashvili.Notifications.controller.api;

import com.NitsaBedianashvili.Notifications.model.Notification;
import com.NitsaBedianashvili.Notifications.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@RestController
@PreAuthorize("hasRole('CLIENT')")

@RequestMapping("/notificationHub/client")
public class ClientNotificationsController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/{id}")
    @PreAuthorize("@userSecurity.isSelf(authentication, #id)")
    public List<Notification> getMessageBox(@PathVariable Long id) {
        try {
            return notificationService.getUserInbox(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error retrieving user inbox: " + e.getMessage());
        }
    }

    @GetMapping("/{ID}/getMessageByID/{messageId}")
    @PreAuthorize("@userSecurity.isSelf(authentication, #ID)")
    public Notification getMessageByID(@PathVariable Long id, @PathVariable Long messageId) {
        try {
            return notificationService.getMessageByMessageID(messageId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error retrieving message: " + e.getMessage());
        }
    }


 ///////////////////MARK AS READ //////////////////  /// ////////////

    @PostMapping("/{id}/markAsRead")
    @PreAuthorize("@userSecurity.isSelf(authentication, #id)")
    public void markAsRead(@PathVariable Long id ,@RequestBody Long messageId) {
        try {
            notificationService.markMessageAsRead(messageId, id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error marking message as read: " + e.getMessage());
        }
    }

    @PostMapping("/{id}/markAllAsRead")
    @PreAuthorize("@userSecurity.isSelf(authentication, #id)")
    public void markAllAsRead(@PathVariable Long id ) {
        try {
            notificationService.markAllAsReadForUser(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error marking all messages as read: " + e.getMessage());
        }
    }


}
