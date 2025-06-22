package com.NitsaBedianashvili.Notifications;

import com.NitsaBedianashvili.Notifications.exception.DuplicateUserException;
import com.NitsaBedianashvili.Notifications.exception.InvalidUserDataException;
import com.NitsaBedianashvili.Notifications.model.User;
import com.NitsaBedianashvili.Notifications.repository.UserRepo;
import com.NitsaBedianashvili.Notifications.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class NotificationsApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificationsApplication.class, args);
	}

}
