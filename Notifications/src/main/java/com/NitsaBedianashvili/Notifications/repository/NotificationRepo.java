package com.NitsaBedianashvili.Notifications.repository;

import com.NitsaBedianashvili.Notifications.model.NotificationPreference;
import com.NitsaBedianashvili.Notifications.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepo extends JpaRepository<NotificationPreference, Long> {


}
