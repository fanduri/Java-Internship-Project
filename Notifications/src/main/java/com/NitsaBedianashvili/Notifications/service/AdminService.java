package com.NitsaBedianashvili.Notifications.service;

import com.NitsaBedianashvili.Notifications.model.NotificationPreference;
import com.NitsaBedianashvili.Notifications.repository.NotificationRepo;
import com.NitsaBedianashvili.Notifications.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    NotificationRepo notificationRepo ;

    @Autowired
    UserRepo userRepo;

    public List<NotificationPreference> showInformationOfAllClients(){
        //TODO: need to add the names of customers too
        return null;
    }



    public NotificationPreference showUserById(long id) {
        //TODO: need to add the names of customers too
        return notificationRepo.findById(id).orElse(null);
    }




}
