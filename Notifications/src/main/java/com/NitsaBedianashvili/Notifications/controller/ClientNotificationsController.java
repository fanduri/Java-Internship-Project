package com.NitsaBedianashvili.Notifications.controller;

import com.NitsaBedianashvili.Notifications.model.Notification;
import com.NitsaBedianashvili.Notifications.service.NotificationService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@PreAuthorize("hasRole('CLIENT')")

@RequestMapping("/notificationHub/client")
public class ClientNotificationsController {

    private NotificationService notificationService;

    @GetMapping("/{ID}")
    @PreAuthorize("@userSecurity.isSelf(authentication, #id)")
    public List<Notification> getMessageBox(@PathVariable Long ID) {
        return notificationService.getUserInbox(ID);
    }


}
