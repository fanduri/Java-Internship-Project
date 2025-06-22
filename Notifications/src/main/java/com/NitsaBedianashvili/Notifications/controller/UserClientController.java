package com.NitsaBedianashvili.Notifications.controller;

import com.NitsaBedianashvili.Notifications.exception.InvalidNotificationException;
import com.NitsaBedianashvili.Notifications.exception.InvalidUserDataException;
import com.NitsaBedianashvili.Notifications.exception.UserNotFoundException;
import com.NitsaBedianashvili.Notifications.model.NotificationPreference;
import com.NitsaBedianashvili.Notifications.service.NotificationService;
import com.NitsaBedianashvili.Notifications.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/client")
@RestController
public class UserClientController {

    @Autowired
    private UserService userService;

    @Autowired
    private NotificationService notificationService;



    @GetMapping("/{ID}")
    public void getInformationAboutClient(@PathVariable long ID)
            throws UserNotFoundException, InvalidUserDataException {
        //TODO: mayhapse create a DTO
         userService.getUserPreferenceByID(ID);
         userService.getUserInformationByID(ID);
    }

    @PutMapping("/{ID}")
    public ResponseEntity<?> updateNotificationPreferance(@PathVariable long ID,
                                                          @RequestBody NotificationPreference notificationPreference)
            throws InvalidNotificationException
    {

        if (notificationPreference == null || ID == 0) {
            return ResponseEntity.badRequest()
                    .body("Error: Request body or user ID is missing.");
        }
        NotificationPreference notificationPreference1
                = notificationService.UpdateNotification( notificationPreference);

        return ResponseEntity.ok(notificationPreference1);
    }

    @DeleteMapping("/{ID}")
    public ResponseEntity<?> deleteAccount (@PathVariable Long ID)
            throws UserNotFoundException, InvalidUserDataException {

        if (ID == 0) {
            return ResponseEntity.badRequest()
                    .body("Error: User ID is required.");
        }
        userService.deleteAccountByID(ID);
        return ResponseEntity.ok("Account deleted successfully.");

    }





}
