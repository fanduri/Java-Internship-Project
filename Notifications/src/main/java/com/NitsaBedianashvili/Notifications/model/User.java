package com.NitsaBedianashvili.Notifications.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.web.WebProperties;

@Data
@Entity
@Table(name = "Users")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer ID;
    private String name;
    private String surname;

    private String email;
    private String address;
    private int phone_num;

}
