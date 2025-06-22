package com.NitsaBedianashvili.Notifications.model;


import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "notifications")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            updatable = false
    )
    private Long messageID;

    public enum DELIVERY_STATUS {
        SENT, DELIVERED, FAILED;
    }

    @Column(
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String message;

    @Column(
            nullable = false
    )
    private Long recipientID;

    @Column(
            nullable = false
    )
    private Long senderID;

    @Column(
            nullable = false
    )
    private DELIVERY_STATUS deliveryStatus = DELIVERY_STATUS.SENT;


    public Notification(Long senderId, Long recipientId, String text) {
        this.message=text;
        this.senderID=senderId;
        this.recipientID=recipientId;
        this.deliveryStatus=DELIVERY_STATUS.SENT;
    }

}