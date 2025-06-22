package com.NitsaBedianashvili.Notifications.service;

import com.NitsaBedianashvili.Notifications.model.Notification;

import com.NitsaBedianashvili.Notifications.model.User;
import com.NitsaBedianashvili.Notifications.repository.NotificationRepo;
import com.NitsaBedianashvili.Notifications.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {
    @Autowired
    NotificationRepo notificationRepo;
    @Autowired
    UserRepo userRepo;

/// //////////SENDING MESSAGE //////////////////////////////////////////////
    public Notification sendMessageToUser(Long senderId,Long recipientId,String text){
        Notification notification =new Notification(senderId,recipientId,text);
        return notificationRepo.save(notification);
        //TODO:error handling
    }

    public Notification sendMessageToUser(Notification notification){
        return notificationRepo.save(notification);
        //TODO:error handling
    }

    public void sendMessageToAllUsers(Long ID, String text){
        List<User> users = userRepo.findAll();

        for(User user : users){
            Notification notification= new Notification(ID, user.getID(), text);
            notificationRepo.save(notification);
        }
        //TODO:error handling
    }

/// ///////////READING MESSAGE AS USER ///////////////////////////

    public List<Notification> getUserInbox(Long userId){
       return notificationRepo.findByRecipientID(userId);
        //TODO:error handling
    }

    public Notification getMessageByMessageID(Long messageId) {
        return notificationRepo.getReferenceById(messageId);
        //TODO:error handling
    }

////////READING  MESSAGE INFO AS ADMIN//////////////////////////////
    public List<Notification> getAllNotificationsInfo(){
        return notificationRepo.findAll();
        //TODO:error handling
    }
    public List<Notification> getAdminsSentNotificationsInfo(Long ID){
        return notificationRepo.findBySenderID(ID);
        //TODO:error handling
    }
///////////////// MARKING MESSAGE AS READ ////////////////////////////
    public void markMessageAsRead(Long messageId,Long userId){
        Notification notification =notificationRepo.getReferenceById(messageId);
        if(notification.getRecipientID()==userId){
            notification.setDeliveryStatus(Notification.DELIVERY_STATUS.DELIVERED);
            notificationRepo.save(notification);
            return;
        }
        System.out.println("NO ACCESS ");
        //TODO:error handling
    }

    public void markAllAsReadForUser(Long id) {
        List<Notification> notifications =notificationRepo.findByRecipientID(id);
        for (Notification notification : notifications){
            notification.setDeliveryStatus(Notification.DELIVERY_STATUS.DELIVERED);
            notificationRepo.save(notification);
        }
        //TODO:error handling

    }


/// ////////////////////////////////////////////////////////////////////////


}
