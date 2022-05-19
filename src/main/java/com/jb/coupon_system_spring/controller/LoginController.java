package com.jb.coupon_system_spring.controller;

import com.jb.coupon_system_spring.beans.UserData;
import com.jb.coupon_system_spring.exceptions.LoginException;
import com.jb.coupon_system_spring.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
public class LoginController {
    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserData userData) throws LoginException {
        return new ResponseEntity<>(loginService.login(userData.getUserEmail(), userData.getUserPassword(), userData.getUserType()), HttpStatus.ACCEPTED);
    }

}
