package com.NitsaBedianashvili.Notifications.repository;

import com.NitsaBedianashvili.Notifications.model.Notification;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepo extends JpaRepository<Notification, Long> {
    List<Notification> findByRecipientID(Long recipientID);

    List<Notification> findBySenderID(Long recipientID);
}
