package com.NitsaBedianashvili.Notifications.service;

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

    public void putInNotificationList( NotificationPreference notificationPreference){
        notificationRepo.save(notificationPreference);
    }


    public void deleteNotificationPreferance( NotificationPreference notificationPreference) {
        notificationRepo.delete(notificationPreference);
    }

    public NotificationPreference UpdateNotification( NotificationPreference notificationPreference) {

        //TODO: DOes not work!!!!!!!!!
        if (notificationRepo.existsById(notificationPreference.getID())){
//            notificationRepo.save()
            NotificationPreference notificationPreference1= notificationRepo.getReferenceById(notificationPreference.getID());
            notificationPreference1.setTelNotif(notificationPreference.getTelNotif());
            notificationPreference1.setEmailNotif(notificationPreference.getEmailNotif());
            notificationPreference1.setPostalNotif(notificationPreference.getPostalNotif());
//            return notificationRepo.save(notificationPreference);
            return notificationPreference1;

        }
        return null;
    }
}
