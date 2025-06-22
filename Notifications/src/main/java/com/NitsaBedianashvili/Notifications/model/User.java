package com.NitsaBedianashvili.Notifications.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Table(name = "Users")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private NotificationPreference notificationPreference;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(
            updatable = false
    )
    private Long ID;

    @Column(
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String name;

    @Column(
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String surname;


    private String email;
    private String address;
    private Integer phoneNum;





    //security
    @Column(
            nullable = false,
            columnDefinition = "TEXT",
            unique = true
    )
    private String username;

    @Column(

            columnDefinition = "TEXT",
            length =199


    )
    private String password;

    @Column(
            nullable = false
    )
    private String role;

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//
//        SimpleGrantedAuthority authority =
//                new SimpleGrantedAuthority(usertype.name());
//        return Collections.singletonList(authority);
//    }
//
//    @Override
//    public String getPassword() {
//        return password;
//    }
//
//    @Override
//    public String getUsername() {
//        return userName;
//    }

//    @Override
//    public boolean isAccountNonExpired() {
//        return UserDetails.super.isAccountNonExpired();
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return UserDetails.super.isAccountNonLocked();
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return UserDetails.super.isCredentialsNonExpired();
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return UserDetails.super.isEnabled();
//    }
}
