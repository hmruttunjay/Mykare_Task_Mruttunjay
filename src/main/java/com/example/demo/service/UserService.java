package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(User user) {
    	 if (user == null) {
    	        throw new IllegalArgumentException("User object cannot be null");
    	    }

    	    
    	    if (user.getName() == null || user.getEmail() == null || user.getGender() == null || user.getPassword() == null) {
    	        throw new IllegalArgumentException("User details are incomplete");
    	    }

    	   
    	    if (emailExists(user.getEmail())) {
    	        throw new IllegalArgumentException("Email already in use");
    	    }

    	    
    	    return userRepository.save(user);
    }

   

	public User authenticateUser(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (password.equals(user.getPassword())) {
                return user;
            }
        }

        throw new IllegalArgumentException("Invalid email or password");
    }

    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUserByEmail(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            userRepository.delete(user);
        } else {
            throw new IllegalArgumentException("User with email " + email + " not found");
        }
    }
    
    
    private boolean emailExists(String email) {
		
		return false;
	}

    
    public List<User> getAllUsers1() {
        return userRepository.findAll();
    }
    
}
