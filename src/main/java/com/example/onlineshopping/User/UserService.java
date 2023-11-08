package com.example.onlineshopping.User;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    void createUser(User user) {
        userRepository.save(user);
    }

    User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }


}
