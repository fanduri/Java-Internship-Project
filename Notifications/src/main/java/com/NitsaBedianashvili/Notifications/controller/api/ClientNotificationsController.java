package com.NitsaBedianashvili.Notifications.controller.api;

import com.NitsaBedianashvili.Notifications.model.Notification;
import com.NitsaBedianashvili.Notifications.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@PreAuthorize("hasRole('CLIENT')")

@RequestMapping("/notificationHub/client")
public class ClientNotificationsController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/{id}")
    @PreAuthorize("@userSecurity.isSelf(authentication, #id)")
    public List<Notification> getMessageBox(@PathVariable Long ID) {
        return notificationService.getUserInbox(ID);
    }


    @GetMapping("/{ID}/getMessageByID/{messageId}")
    @PreAuthorize("@userSecurity.isSelf(authentication, #ID)")
    public Notification getMessageByID(@PathVariable Long ID, @PathVariable Long messageId) {
        return notificationService.getMessageByMessageID(messageId);
    }


 ///////////////////MARK AS READ //////////////////  /// ////////////

    @PostMapping("/{id}/markAsRead")
    @PreAuthorize("@userSecurity.isSelf(authentication, #id)")
    public void markAsRead(@PathVariable Long ID ,@RequestBody Long messageId) {
        notificationService.markMessageAsRead(messageId, ID);
    }

    @PostMapping("/{id}/markAllAsRead")
    @PreAuthorize("@userSecurity.isSelf(authentication, #id)")
    public void markAllAsRead(@PathVariable Long ID ) {
        notificationService.markAllAsReadForUser(ID);
    }


}
