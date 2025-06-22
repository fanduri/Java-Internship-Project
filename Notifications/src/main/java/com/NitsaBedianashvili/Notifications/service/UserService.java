package com.NitsaBedianashvili.Notifications.service;

import com.NitsaBedianashvili.Notifications.exception.DuplicateUserException;
import com.NitsaBedianashvili.Notifications.exception.InvalidUserDataException;
import com.NitsaBedianashvili.Notifications.exception.UserNotFoundException;
import com.NitsaBedianashvili.Notifications.model.NotificationPreference;
import com.NitsaBedianashvili.Notifications.model.User;
import com.NitsaBedianashvili.Notifications.repository.NotificationRepo;
import com.NitsaBedianashvili.Notifications.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;

    @Autowired
    NotificationRepo notificationRepo;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(13);

    /// ////////////////////CREATE OPERATIONS////////////////////////////////////////////////////////////
    @Transactional
    public User createUser(User user) throws InvalidUserDataException, DuplicateUserException {

        if (user == null) {
            throw new InvalidUserDataException("User data cannot be null.");
        }
        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            throw new InvalidUserDataException("Username is required.");
        }
        if (userRepo.existsByUsername(user.getUsername())) {
            throw new DuplicateUserException("Username '" + user.getUsername() + "' already exists.");
        }

        NotificationPreference preferences = createDefaultPreferences(user);

        user.setNotificationPreference(preferences);
        user.setPassword(encoder.encode(user.getPassword()));
        preferences.setUser(user);

        return userRepo.save(user);
    }


    @Transactional
    public void createUsers(User[] users) throws InvalidUserDataException, DuplicateUserException {
        //TODO: does not work properly !! It creates the users But still throws error 500
        if (users == null || users.length == 0) {
            throw new InvalidUserDataException("User list cannot be empty.");
        }
        for (User user : users) {
            createUser(user);
            System.out.println("user: "+user.toString()+" successfully added");
        }
    }


    /// ////////////////////READ OPERATIONS////////////////////////////////////////////////////////////

    //gets user information by ID
    public User getUserInformationByID(long id)
            throws UserNotFoundException {

        return userRepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found."));
    }


    //returns users preference by User
    public NotificationPreference getUserPreferenceByUser(User user)
            throws InvalidUserDataException, UserNotFoundException {

        if (user == null || user.getID() == 0) {
            throw new InvalidUserDataException("User must have a valid ID.");
        }

        return notificationRepo.findById(user.getID())
                .orElseThrow(() -> new UserNotFoundException("User's preference with ID " + user.getID() + " not found."));
    }


    //returns users preference by ID
    public NotificationPreference getUserPreferenceByID(Long id) throws InvalidUserDataException, UserNotFoundException {

        if (id == 0) {
            throw new InvalidUserDataException("ID cannot be null.");
        }
        return notificationRepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User's preference with ID " + id + " not found."));
    }


    ///    /////////////////////// UPDATE OPERATIONS////////////////////////////////////////////////////////////

    @Transactional
    public void updateUser(User user)
            throws InvalidUserDataException, UserNotFoundException {

        if (user == null || user.getID() == 0) {
            throw new InvalidUserDataException("User ID is required for update.");
        }
        if (!userRepo.existsById(user.getID())) {
            throw new UserNotFoundException("User with ID " + user.getID() + " not found.");
        }

        //I am making sure that if the user deletes the addresses
        //notification preferences will stay consistent
        NotificationPreference preference = getUserPreferenceByID(user.getID());
        if (user.getPhoneNum() == 0) {
            preference.setTelNotif(false);
        }
        if (user.getAddress() == null) {
            preference.setPostalNotif(false);
        }
        if (user.getEmail() == null) {
            preference.setEmailNotif(false);
        }

        userRepo.save(user);
        notificationRepo.save(preference);

    }


    /// ///    /////////////////////// DELETE OPERATIONS////////////////////////////////////////////////////////////
    @Transactional
    public void deleteAccountByID(Long id)
            throws UserNotFoundException, InvalidUserDataException {

        if (id == null) {
            throw new InvalidUserDataException("ID cannot be null.");
        }
        if (!userRepo.existsById(id)) {
            throw new UserNotFoundException("User with ID " + id + " not found.");
        }

        userRepo.deleteById(id);
    }


    @Transactional
    public void deleteAccountByAccount(User user)
            throws UserNotFoundException, InvalidUserDataException {
        if (user.getID() == 0) {
            throw new InvalidUserDataException("ID cannot be null.");
        }
        if (!userRepo.existsById(user.getID())) {
            throw new UserNotFoundException("User with ID " + user.getID() + " not found.");
        }

        userRepo.delete(user);
    }



/// //////////////////////HELPER METHOD////////////////////////////////////////////////////////////////////
    //Internal method for creating default preferances
    private NotificationPreference createDefaultPreferences(User user) {
        NotificationPreference preferences = new NotificationPreference();

        //by default if user has a notification addres the notifications are on
        preferences.setID(user.getID());
        preferences.setEmailNotif(user.getEmail() != null);
        preferences.setTelNotif(user.getPhoneNum() != 0);
        preferences.setPostalNotif(user.getAddress() != null);

        return preferences;

    }

}




