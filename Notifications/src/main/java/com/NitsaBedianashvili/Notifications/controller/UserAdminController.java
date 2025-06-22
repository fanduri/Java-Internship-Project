package com.NitsaBedianashvili.Notifications.controller;

import com.NitsaBedianashvili.Notifications.exception.DuplicateUserException;
import com.NitsaBedianashvili.Notifications.exception.InvalidNotificationException;
import com.NitsaBedianashvili.Notifications.exception.InvalidUserDataException;
import com.NitsaBedianashvili.Notifications.exception.UserNotFoundException;
import com.NitsaBedianashvili.Notifications.model.NotificationPreference;
import com.NitsaBedianashvili.Notifications.model.User;
import com.NitsaBedianashvili.Notifications.service.AdminService;
import com.NitsaBedianashvili.Notifications.service.NotificationService;
import com.NitsaBedianashvili.Notifications.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/admin")
@RestController
public class UserAdminController {
    @Autowired
    private NotificationService notificationService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserService userService;


    //page where admins can see all clients information

    @GetMapping("/{ID}/home")
    public void getHomePage(@PathVariable long ID){
        System.out.println(ID);
    //TODO
    }



    @GetMapping("/{ID}/clientInfo")
    public ResponseEntity<?> getAllClientInfo(@PathVariable long ID){
        List<NotificationPreference> clients = adminService.showInformationOfAllClients();
        return ResponseEntity.ok(clients);
    }


    //Admin can create a user
    @PostMapping("/{ID}/addUser")
    public ResponseEntity<?> addUser(@RequestBody User user)
            throws InvalidUserDataException, DuplicateUserException

    {
        if (user == null || user.getUsername() == null || user.getPassword() == null) {
            return ResponseEntity.badRequest()
                    .body("Error: Username and password are required.");
        }

        User createdUser = userService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }


    //Admin can update a user
    @PutMapping("/{ID}/updateUser")
    public ResponseEntity<?> updateUserInfo(@RequestBody User user)
            throws UserNotFoundException, InvalidUserDataException {

        if (user == null || user.getID() == null) {
            return ResponseEntity.badRequest()
                    .body("Error: User ID is required for updates.");
        }
        userService.updateUser(user);
        return ResponseEntity.ok("User updated successfully.");
    }

    //Admin can delete a user
    @DeleteMapping("/{ID}/deleteUser")
    public ResponseEntity<?> deleteUser(@RequestBody User user)
            throws UserNotFoundException, InvalidUserDataException {

        if (user.getID() == null) {
            return ResponseEntity.badRequest()
                    .body("Error: User ID is required for deletion.");
        }
        userService.deleteAccountByID(user.getID());
        return ResponseEntity.ok("User deleted successfully.");
    }


    //I dont think admin should be able to change notification preferance but here anyway
    @PutMapping("/{ID}/updateUserNotifications")
    public void updateUserNotification(@RequestBody NotificationPreference notificationPreference)
            throws InvalidNotificationException {

        //TODO : notification information updates
        notificationService.UpdateNotification(notificationPreference);
        //TODO: error handling

    }



/// //////////////////////////////////////////////////////////////////////////////////
/// Notification sending and handling below

    //page where admins can see notification statuses (if they delivered correctly or not etc.)
    @GetMapping("/{ID}/notificationStatus")
    public void getAllNotificationStatus(@PathVariable long ID){
        //TODO
    }


    //page where admins can send notifications to chosen clients
    @GetMapping("/{ID}/notificationSendingPage")
    public void getSendingPage(@PathVariable long ID){
        //TODO
    }

    //page where admins can send notifications to chosen clients
    @PostMapping("/{ID}/notificationSendingPage")
    public void sendNotification(@PathVariable long ID){
        //TODO
    }

}
