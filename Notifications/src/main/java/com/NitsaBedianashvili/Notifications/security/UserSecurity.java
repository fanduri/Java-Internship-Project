package com.NitsaBedianashvili.Notifications.security;

import com.NitsaBedianashvili.Notifications.model.User;
import com.NitsaBedianashvili.Notifications.model.UserPrincipal;
import com.NitsaBedianashvili.Notifications.service.UserService;
import org.springframework.stereotype.Component;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import org.springframework.security.core.userdetails.UserDetails;

//this is a class for helping to authenticate the users
@Component("userSecurity")   // will reference in @PreAuthorize
public class UserSecurity {

    private final UserService userService;

    // Constructor injection
    public UserSecurity(UserService userService) {
        this.userService = userService;
    }

    public boolean isSelf(Authentication authentication, Long userId) {
        if (authentication == null || userId == null) {
            return false;
        }

        //get the currently authenticated user's info
        UserPrincipal userDetails = (UserPrincipal) authentication.getPrincipal();

        //load the full user entity
        User currentUser = userService.findByUsername(userDetails.getUsername());

        //compare the IDs
        return currentUser.getID().equals(userId);
    }

    public boolean isAdmin(Authentication authentication) {
        if (authentication == null) {
            return false;
        }

        return authentication.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));
    }

}
