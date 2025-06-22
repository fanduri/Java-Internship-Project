package com.NitsaBedianashvili.Notifications.controller.api;

import com.NitsaBedianashvili.Notifications.exception.InvalidNotificationException;
import com.NitsaBedianashvili.Notifications.exception.InvalidUserDataException;
import com.NitsaBedianashvili.Notifications.exception.UserNotFoundException;
import com.NitsaBedianashvili.Notifications.model.NotificationPreference;
import com.NitsaBedianashvili.Notifications.service.NotificationPreferenceService;
import com.NitsaBedianashvili.Notifications.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/client")
@PreAuthorize("hasRole('CLIENT')")
@RestController
public class UserClientController {

    @Autowired
    private UserService userService;

    @Autowired
    private NotificationPreferenceService notificationPreferenceService;



    @GetMapping("/{id}")
    @PreAuthorize("@userSecurity.isSelf(authentication, #id)")
    public void getInformationAboutClient(@PathVariable long id)
            throws UserNotFoundException, InvalidUserDataException {
        //TODO: mayhapse create a DTO
         userService.getUserPreferenceByID(id);
         userService.getUserInformationByID(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("@userSecurity.isSelf(authentication, #id)")
    public ResponseEntity<?> updateNotificationPreferance
            (@PathVariable long id, @RequestBody NotificationPreference notificationPreference)
            throws InvalidNotificationException
    {
        if (notificationPreference == null || id == 0) {
            return ResponseEntity.badRequest()
                    .body("Error: Request body or user id is missing.");
        }
        NotificationPreference notificationPreference1
                = notificationPreferenceService.UpdateNotification( notificationPreference);

        return ResponseEntity.ok(notificationPreference1);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@userSecurity.isSelf(authentication, #id)")
    public ResponseEntity<?> deleteAccount (@PathVariable Long id)
            throws UserNotFoundException, InvalidUserDataException {

        if (id == 0) {
            return ResponseEntity.badRequest()
                    .body("Error: User ID is required.");
        }
        userService.deleteAccountByID(id);
        return ResponseEntity.ok("Account deleted successfully.");

    }





}
