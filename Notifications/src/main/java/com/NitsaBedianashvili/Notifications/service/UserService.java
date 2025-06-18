package com.NitsaBedianashvili.Notifications.service;

import com.NitsaBedianashvili.Notifications.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;

}
