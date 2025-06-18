package com.NitsaBedianashvili.Notifications.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@Entity
@Table(name = "notifications")
@NoArgsConstructor
public class NotificationPreference {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer ID;
    private boolean emailNotif;
    private boolean postalNotif;
    private boolean telNotif;

    public NotificationPreference(boolean emailNotif, boolean postalNotif, boolean telNotif) {
        this.emailNotif = emailNotif;
        this.postalNotif = postalNotif;
        this.telNotif = telNotif;
    }

    @Override
    public String toString() {
        return "NotificationPreference{" +
                "ID=" + ID +
                ", emailNotif=" + emailNotif +
                ", postalNotif=" + postalNotif +
                ", telNotif=" + telNotif +
                '}';
    }
}
