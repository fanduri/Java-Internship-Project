package com.NitsaBedianashvili.Notifications.service;

import com.NitsaBedianashvili.Notifications.exception.InvalidNotificationException;
import com.NitsaBedianashvili.Notifications.exception.NotificationAccessException;
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
    public Notification sendMessageToUser(Long senderId,Long recipientId,String text)
            throws InvalidNotificationException {
        try {
            Notification notification = new Notification(senderId, recipientId, text);
            return notificationRepo.save(notification);
        } catch (IllegalArgumentException e) {
            throw new InvalidNotificationException("Invalid notification data: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Failed to send message: " + e.getMessage(), e);
        }
    }

    public Notification sendMessageToUser(Notification notification) throws InvalidNotificationException {
        try {
            return notificationRepo.save(notification);
        } catch (IllegalArgumentException e) {
            throw new InvalidNotificationException("Invalid notification data: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Failed to send message: " + e.getMessage(), e);
        }
    }

    public void sendMessageToAllUsers(Long ID, String text){

        try {
            List<User> users = userRepo.findAll();
            for (User user : users) {
                Notification notification = new Notification(ID, user.getID(), text);
                notificationRepo.save(notification);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to send messages to all users: " + e.getMessage(), e);
        }
    }

/// ///////////READING MESSAGE AS USER ///////////////////////////

    public List<Notification> getUserInbox(Long userId){
        try {
            return notificationRepo.findByRecipientID(userId);
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve user inbox: " + e.getMessage(), e);
        }
    }


    public Notification getMessageByMessageID(Long messageId) throws InvalidNotificationException {
        try {
            return notificationRepo.findById(messageId)
                    .orElseThrow(() -> new InvalidNotificationException("Message not found with ID: " + messageId));
        } catch (InvalidNotificationException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve message: " + e.getMessage(), e);
        }
    }

////////READING  MESSAGE INFO AS ADMIN//////////////////////////////
    public List<Notification> getAllNotificationsInfo(){
        try {
            return notificationRepo.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve all notifications: " + e.getMessage(), e);
        }
    }
    public List<Notification> getAdminsSentNotificationsInfo(Long ID){
        try {
            return notificationRepo.findBySenderID(ID);
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve admin's sent notifications: " + e.getMessage(), e);
        }
    }
///////////////// MARKING MESSAGE AS READ ////////////////////////////
    public void markMessageAsRead(Long messageId,Long userId) throws NotificationAccessException, InvalidNotificationException {
        try {
            Notification notification = notificationRepo.findById(messageId)
                    .orElseThrow(() -> new InvalidNotificationException("Message not found with ID: " + messageId));

            if (notification.getRecipientID().equals(userId)) {
                notification.setDeliveryStatus(Notification.DELIVERY_STATUS.DELIVERED);
                notificationRepo.save(notification);
            } else {
                System.out.println("NO ACCESS");
                throw new NotificationAccessException("User " + userId + " cannot mark this message as read");
            }
        } catch (InvalidNotificationException | NotificationAccessException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Failed to mark message as read: " + e.getMessage(), e);
        }
    }

    public void markAllAsReadForUser(Long ID) {
        try {
            List<Notification> notifications = notificationRepo.findByRecipientID(ID);
            for (Notification notification : notifications) {
                notification.setDeliveryStatus(Notification.DELIVERY_STATUS.DELIVERED);
                notificationRepo.save(notification);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to mark all messages as read: " + e.getMessage(), e);
        }
    }


/// ////////////////////////////////////////////////////////////////////////


}
