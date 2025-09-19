package com.shanmuga.moviespotlight.controller;

import com.shanmuga.moviespotlight.model.dto.UserDto;
import com.shanmuga.moviespotlight.model.request.AuthRequest;
import com.shanmuga.moviespotlight.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    private final UserService userService;

    public AuthenticationController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDto userDto){
        return ResponseEntity.ok(userService.registerUser(userDto));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request){
        String token = userService.authenticate(request);
        return ResponseEntity.ok(Map.of("token", token));
    }
}
