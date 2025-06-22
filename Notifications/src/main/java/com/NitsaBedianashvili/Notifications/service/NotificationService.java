package com.NitsaBedianashvili.Notifications.service;

import com.NitsaBedianashvili.Notifications.exception.InvalidNotificationException;
import com.NitsaBedianashvili.Notifications.exception.NotificationNotFoundException;
import com.NitsaBedianashvili.Notifications.model.NotificationPreference;
import com.NitsaBedianashvili.Notifications.repository.NotificationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class NotificationService {
    @Autowired
    NotificationRepo notificationRepo ;

    public void putInNotificationList( NotificationPreference notificationPreference)
            throws InvalidNotificationException {

        if (notificationPreference == null) {
            throw new InvalidNotificationException("Notification preference cannot be null");
        }
        notificationRepo.save(notificationPreference);
    }


    public void deleteNotificationPreferance( NotificationPreference notificationPreference)
            throws NotificationNotFoundException {

        if (!notificationRepo.existsById(notificationPreference.getID())) {
            throw new NotificationNotFoundException
                    ("Notification preference with ID " + notificationPreference.getID() + " not found");
        }
        notificationRepo.delete(notificationPreference);
    }

    public NotificationPreference UpdateNotification( NotificationPreference notificationPreference) throws InvalidNotificationException {


        if (notificationPreference == null || notificationPreference.getID() == null) {
            throw new InvalidNotificationException("Notification ID is required for update");
        }
        //TODO: DOes not work!!!!!!!!!

        NotificationPreference notificationPreference1=
                notificationRepo.getReferenceById(notificationPreference.getID());

        notificationPreference1.setTelNotif(notificationPreference.getTelNotif());
        notificationPreference1.setEmailNotif(notificationPreference.getEmailNotif());
        notificationPreference1.setPostalNotif(notificationPreference.getPostalNotif());

        return notificationRepo.save(notificationPreference1);

    }
}
