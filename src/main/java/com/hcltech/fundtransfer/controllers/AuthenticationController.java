package com.hcltech.fundtransfer.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hcltech.fundtransfer.dtos.LoginUserDto;
import com.hcltech.fundtransfer.dtos.RegisterUserDto;
import com.hcltech.fundtransfer.entities.Customer;
import com.hcltech.fundtransfer.responses.LoginResponse;
import com.hcltech.fundtransfer.services.AuthenticationService;
import com.hcltech.fundtransfer.services.JwtService;

import jakarta.validation.Valid;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterUserDto registerUserDto) {
        Customer registeredUser = authenticationService.signup(registerUserDto);
        return ResponseEntity.status(HttpStatus.CREATED)
				.body(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody @Valid LoginUserDto loginUserDto) {
        Customer authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse().setToken(jwtToken).setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }
}