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
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping("/{ID}/home")
    @PreAuthorize("@userSecurity.isSelf(authentication, #id)")
    public void getHomePage(@PathVariable long ID){
        System.out.println(ID);
    //TODO
    }

/// //////////GETTING INFORMATION ////////////////////////////////////////

    @GetMapping("/{ID}/clientInfo")
    @PreAuthorize("@userSecurity.isSelf(authentication, #id)")
    public ResponseEntity<?> getAllClientInfo(@PathVariable long ID){
        List<NotificationPreference> clients = adminService.showInformationOfAllClients();
        return ResponseEntity.ok(clients);
    }

//    @GetMapping("/{ID}/clientInfoSpecific")
//    @PreAuthorize("@userSecurity.isSelf(authentication, #id)")
//    public ResponseEntity<?> getSpecificClientInfo(@PathVariable long ID, Long userId)
//            throws UserNotFoundException {
//
//        User user=userService.getUserInformationByID(userId);
//        return ResponseEntity.ok(user);
//    }

////    /// //////////ADDING INFORMATION ////////////////////////////////////////

    //Admin can create a user
    @PostMapping("/{ID}/addUser")
    @PreAuthorize("@userSecurity.isSelf(authentication, #id)")
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

/////    /// //////////UPDATE INFORMATION ////////////////////////////////////////

    //Admin can update a user
    @PutMapping("/{ID}/updateUser")
    @PreAuthorize("@userSecurity.isSelf(authentication, #id)")
    public ResponseEntity<?> updateUserInfo(@RequestBody User user)
            throws UserNotFoundException, InvalidUserDataException {

        if (user == null || user.getID() == null) {
            return ResponseEntity.badRequest()
                    .body("Error: User ID is required for updates.");
        }
        userService.updateUser(user);
        return ResponseEntity.ok("User updated successfully.");
    }


    //I dont think admin should be able to change notification preferance but here anyway
    @PutMapping("/{ID}/updateUserNotifications")
    @PreAuthorize("@userSecurity.isSelf(authentication, #id)")
    public void updateUserNotification(@RequestBody NotificationPreference notificationPreference)
            throws InvalidNotificationException {

        //TODO : notification information updates
        notificationPreferenceService.UpdateNotification(notificationPreference);
        //TODO: error handling

    }

///////// ///////// DELETE INFORMATION ////////////////////////////////////////

    //Admin can delete a user
    @DeleteMapping("/{ID}/deleteUser")
    @PreAuthorize("@userSecurity.isSelf(authentication, #id)")
    public ResponseEntity<?> deleteUser(@RequestBody User user)
            throws UserNotFoundException, InvalidUserDataException {

        if (user.getID() == null) {
            return ResponseEntity.badRequest()
                    .body("Error: User ID is required for deletion.");
        }
        userService.deleteAccountByID(user.getID());
        return ResponseEntity.ok("User deleted successfully.");
    }





}
