package com.NitsaBedianashvili.Notifications.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@Entity
@Table(name = "notifications")
@NoArgsConstructor
public class NotificationPreference {

    @OneToOne
    @MapsId
    @JoinColumn(name = "User_ID")
    private User user;

    @Id
    @Column(
            updatable = false
    )
    private Long ID;
    private Boolean emailNotif;
    private Boolean postalNotif;
    private Boolean telNotif;




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
