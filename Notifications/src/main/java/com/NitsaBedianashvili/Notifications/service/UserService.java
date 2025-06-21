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

        //TODO: need to check if the user exists already first
        NotificationPreference preferences = createDefaultPreferences(user);

        user.setNotificationPreference(preferences);
        preferences.setUser(user);

        return userRepo.save(user);
    }

    public NotificationPreference createDefaultPreferences(User user) {
        NotificationPreference preferences = new NotificationPreference();

        //by default if user has a notification addres the notifications are on
        preferences.setID(user.getID());
        preferences.setEmailNotif(user.getEmail() != null );
        preferences.setTelNotif(user.getPhoneNum()!= 0);
        preferences.setPostalNotif(user.getAddress() != null);

        return preferences;

    }


    //returns users preference by User
    public NotificationPreference getUserPreferenceByUser (User user){
        return notificationRepo.findById(user.getID()).orElse(null);
    }
    //returns users preference by ID
    public NotificationPreference getUserPreferenceByID(Long id){
        return notificationRepo.findById(id).orElse(null);
    }

    public void deleteAccount(Long id) {
        if ( userRepo.existsById(id)){
            User user= userRepo.getReferenceById(id);
            userRepo.delete(user);

        }

    }
}
