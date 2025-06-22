package com.NitsaBedianashvili.Notifications.service;

import com.NitsaBedianashvili.Notifications.exception.InvalidNotificationException;
import com.NitsaBedianashvili.Notifications.exception.NotificationNotFoundException;
import com.NitsaBedianashvili.Notifications.model.NotificationPreference;
import com.NitsaBedianashvili.Notifications.repository.NotificationPreferenceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationPreferenceService {
    @Autowired
    NotificationPreferenceRepo notificationPreferenceRepo;

    public void putInNotificationList( NotificationPreference notificationPreference)
            throws InvalidNotificationException {

        if (notificationPreference == null) {
            throw new InvalidNotificationException("Notification preference cannot be null");
        }
        notificationPreferenceRepo.save(notificationPreference);
    }


    public void deleteNotificationPreferance( NotificationPreference notificationPreference)
            throws NotificationNotFoundException {

        if (!notificationPreferenceRepo.existsById(notificationPreference.getID())) {
            throw new NotificationNotFoundException
                    ("Notification preference with ID " + notificationPreference.getID() + " not found");
        }
        notificationPreferenceRepo.delete(notificationPreference);
    }

    public NotificationPreference UpdateNotification( NotificationPreference notificationPreference) throws InvalidNotificationException {


        if (notificationPreference == null || notificationPreference.getID() == null) {
            throw new InvalidNotificationException("Notification ID is required for update");
        }
        //TODO: DOes not work!!!!!!!!!

        NotificationPreference notificationPreference1=
                notificationPreferenceRepo.getReferenceById(notificationPreference.getID());

        notificationPreference1.setTelNotif(notificationPreference.getTelNotif());
        notificationPreference1.setEmailNotif(notificationPreference.getEmailNotif());
        notificationPreference1.setPostalNotif(notificationPreference.getPostalNotif());

        return notificationPreferenceRepo.save(notificationPreference1);

    }
}
