package com.NitsaBedianashvili.Notifications.controller.api;

import com.NitsaBedianashvili.Notifications.exception.InvalidNotificationException;
import com.NitsaBedianashvili.Notifications.exception.InvalidUserDataException;
import com.NitsaBedianashvili.Notifications.exception.UserNotFoundException;
import com.NitsaBedianashvili.Notifications.model.NotificationPreference;
import com.NitsaBedianashvili.Notifications.service.NotificationPreferenceService;
import com.NitsaBedianashvili.Notifications.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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

        try {
            //TODO: mayhapse create a DTO
            userService.getUserPreferenceByID(id);
            userService.getUserInformationByID(id);
        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found: " + e.getMessage());
        } catch (InvalidUserDataException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid user data: " + e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error retrieving client information: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("@userSecurity.isSelf(authentication, #id)")
    public ResponseEntity<?> updateNotificationPreferance
            (@PathVariable long id, @RequestBody NotificationPreference notificationPreference)
            throws InvalidNotificationException
    { try {
        if (notificationPreference == null || id == 0) {
            return ResponseEntity.badRequest()
                    .body("Error: Request body or user id is missing.");
        }
        NotificationPreference updatedPreference =
                notificationPreferenceService.UpdateNotification(notificationPreference);
        return ResponseEntity.ok(updatedPreference);
    } catch (InvalidNotificationException e) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid notification: " + e.getMessage());
    } catch (Exception e) {
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                "Error updating notification preference: " + e.getMessage());
    }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@userSecurity.isSelf(authentication, #id)")
    public ResponseEntity<?> deleteAccount (@PathVariable Long id)
            throws UserNotFoundException, InvalidUserDataException {

        try {
            if (id == 0) {
                return ResponseEntity.badRequest()
                        .body("Error: User ID is required.");
            }
            userService.deleteAccountByID(id);
            return ResponseEntity.ok("Account deleted successfully.");
        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found: " + e.getMessage());
        } catch (InvalidUserDataException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid user data: " + e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error deleting account: " + e.getMessage());
        }

    }





}
