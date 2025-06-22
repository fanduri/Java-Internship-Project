package com.NitsaBedianashvili.Notifications.controller.api;

import com.NitsaBedianashvili.Notifications.exception.DuplicateUserException;
import com.NitsaBedianashvili.Notifications.exception.InvalidNotificationException;
import com.NitsaBedianashvili.Notifications.exception.InvalidUserDataException;
import com.NitsaBedianashvili.Notifications.exception.UserNotFoundException;
import com.NitsaBedianashvili.Notifications.model.NotificationPreference;
import com.NitsaBedianashvili.Notifications.model.User;
import com.NitsaBedianashvili.Notifications.service.AdminService;
import com.NitsaBedianashvili.Notifications.service.NotificationPreferenceService;
import com.NitsaBedianashvili.Notifications.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/admin")
@RestController
public class UserAdminController {
    @Autowired
    private NotificationPreferenceService notificationPreferenceService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserService userService;


    //page where admins can see all clients information


    @GetMapping("/{id}/home")
    @PreAuthorize("@userSecurity.isSelf(authentication, #id)")
    public void getHomePage(@PathVariable long id) {
        try {
            System.out.println(id);
            //TODO: actual logic
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error accessing admin home: " + e.getMessage());
        }
    }

/// //////////GETTING INFORMATION ////////////////////////////////////////

    @GetMapping("/{id}/clientInfo")
    @PreAuthorize("@userSecurity.isSelf(authentication, #id)")
    public ResponseEntity<?> getAllClientInfo(@PathVariable long ID){
        try {
            List<NotificationPreference> clients = adminService.showInformationOfAllClients();
            return ResponseEntity.ok(clients);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error retrieving client info: " + e.getMessage());
        }
    }

//    @GetMapping("/{id}/clientInfoSpecific")
//    @PreAuthorize("@userSecurity.isSelf(authentication, #id)")
//    public ResponseEntity<?> getSpecificClientInfo(@PathVariable long ID, Long userId)
//            throws UserNotFoundException {
//
//        User user=userService.getUserInformationByID(userId);
//        return ResponseEntity.ok(user);
//    }

////    /// //////////ADDING INFORMATION ////////////////////////////////////////

    //Admin can create a user
    @PostMapping("/{id}/addUser")
    @PreAuthorize("@userSecurity.isSelf(authentication, #id)")
    public ResponseEntity<?> addUser(@RequestBody User user)
            throws InvalidUserDataException, DuplicateUserException

    {
        try {
            if (user == null || user.getUsername() == null || user.getPassword() == null) {
                return ResponseEntity.badRequest()
                        .body("Error: Username and password are required.");
            }
            User createdUser = userService.createUser(user);
            return ResponseEntity.ok(createdUser);
        } catch (InvalidUserDataException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid user data: " + e.getMessage());
        } catch (DuplicateUserException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Duplicate user: " + e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error adding user: " + e.getMessage());
        }
    }

/////    /// //////////UPDATE INFORMATION ////////////////////////////////////////

    //Admin can update a user
    @PutMapping("/{id}/updateUser")
    @PreAuthorize("@userSecurity.isSelf(authentication, #id)")
    public ResponseEntity<?> updateUserInfo(@RequestBody User user)
            throws UserNotFoundException, InvalidUserDataException {

        try {
            if (user == null || user.getID() == null) {
                return ResponseEntity.badRequest()
                        .body("Error: User ID is required for updates.");
            }
            userService.updateUser(user);
            return ResponseEntity.ok("User updated successfully.");
        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found: " + e.getMessage());
        } catch (InvalidUserDataException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid user data: " + e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error updating user: " + e.getMessage());
        }
    }


    //I dont think admin should be able to change notification preferance but here anyway
    @PutMapping("/{id}/updateUserNotifications")
    @PreAuthorize("@userSecurity.isSelf(authentication, #id)")
    public ResponseEntity<Object> updateUserNotification(@RequestBody NotificationPreference notificationPreference)
            throws InvalidNotificationException {
        try {
            //TODO : notification information updates
            notificationPreferenceService.UpdateNotification(notificationPreference);
            return ResponseEntity.ok().build();
        } catch (InvalidNotificationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid notification: " + e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error updating notification preferences: " + e.getMessage());
        }

    }

///////// ///////// DELETE INFORMATION ////////////////////////////////////////

    //Admin can delete a user
    @DeleteMapping("/{id}/deleteUser")
    @PreAuthorize("@userSecurity.isSelf(authentication, #id)")
    public ResponseEntity<?> deleteUser(@RequestBody User user)
            throws UserNotFoundException, InvalidUserDataException {
        try {
            if (user.getID() == null) {
                return ResponseEntity.badRequest()
                        .body("Error: User ID is required for deletion.");
            }
            userService.deleteAccountByID(user.getID());
            return ResponseEntity.ok("User deleted successfully.");
        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found: " + e.getMessage());
        } catch (InvalidUserDataException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid user data: " + e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error deleting user: " + e.getMessage());
        }
    }





}
