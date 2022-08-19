package com.logisticcompany.team4.services;

import com.logisticcompany.team4.model.User;
import com.logisticcompany.team4.repository.UserRepository;
import com.logisticcompany.team4.security.UserPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class UserServices {
	
	 @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        List<User> users = userRepository.findAll();
        return users;
    }

    public void addUser(@ModelAttribute User user) {
        userRepository.save(user);
    }

    public User findUserById(@PathVariable("id") int id) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        return user;
    }

    public void updateUser(@ModelAttribute User user) throws Exception {
        User userInDB = userRepository.findById(user.getId()).orElse(null);
        if (userInDB != null) {
            userInDB.setUsername(user.getUsername());
            userInDB.setEmail(user.getEmail());
            userInDB.setPassword(user.getPassword());
            userInDB.setFirstName(user.getFirstName());
            userInDB.setLastName(user.getLastName());
            userInDB.setActive(user.isActive());
            userInDB.setRole(user.getRole());
            userRepository.save(userInDB);
        } else {
            throw new Exception("User not found");
        }
    }

    public void deleteUser(@PathVariable("id") int id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userRepository.delete(user);
    }

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userDetails = (UserPrincipal) authentication.getPrincipal();
        User currentUser = userRepository.findByUsername(userDetails.getUsername());

        return currentUser;
    }
}
