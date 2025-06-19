package com.NitsaBedianashvili.Notifications.service;

import com.NitsaBedianashvili.Notifications.model.NotificationPreference;
import com.NitsaBedianashvili.Notifications.model.User;
import com.NitsaBedianashvili.Notifications.repository.NotificationRepo;
import com.NitsaBedianashvili.Notifications.repository.UserRepo;
import org.hibernate.annotations.Array;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;

    @Autowired
    NotificationRepo notificationRepo ;

    public User createUser(User user) {

        NotificationPreference preferences = createDefaultPreferences(user);

        user.setNotificationPreference(preferences);
        preferences.setUser(user);

        return userRepo.save(user);
    }

    private NotificationPreference createDefaultPreferences(User user) {
        NotificationPreference preferences = new NotificationPreference();

        //by default if user has a notification addres the notifications are on
        preferences.setID(user.getID());
        preferences.setEmailNotif(user.getEmail() != null );
        preferences.setTelNotif(user.getPhoneNum()!= 0);
        preferences.setPostalNotif(user.getAddress() != null);

        return preferences;

    }
}
