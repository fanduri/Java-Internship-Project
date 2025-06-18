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

    public List<NotificationPreference> showAllPreferances(){
        return notificationRepo.findAll();
    }

    public NotificationPreference showPreferancesByID(int id) {
        return notificationRepo.findById(id).orElse(null);
    }

    public void putInNotifList( NotificationPreference notificationPreference){
        notificationRepo.save(notificationPreference);
    }


    public void deleteNotifPreferance( NotificationPreference notificationPreference) {
        notificationRepo.delete(notificationPreference);
    }
}
