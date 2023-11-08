package com.example.onlineshopping.User;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    ResponseEntity<String> createUser(@RequestBody User user) {
        userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("User Created!");
    }

    @GetMapping("/{username}")
    ResponseEntity<User> findUserById(@PathVariable String username) {
        return ResponseEntity.ok().body(userService.findUserByUsername(username));
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody UserLoginRequest userLoginRequest) {
        User user = userService.findUserByUsername(userLoginRequest.getUsername());
        if (user != null && user.getPassword().equals(userLoginRequest.getPassword())) {
            return ResponseEntity.status(HttpStatus.OK).body("User Validated!");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User Invalid!");
        }
    }
}
