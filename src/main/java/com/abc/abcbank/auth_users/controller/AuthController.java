package com.abc.abcbank.auth_users.controller;

import com.abc.abcbank.auth_users.dto.LoginRequest;
import com.abc.abcbank.auth_users.dto.LoginResponse;
import com.abc.abcbank.auth_users.dto.RegistrationRequest;
import com.abc.abcbank.auth_users.dto.ResetPasswordRequest;
import com.abc.abcbank.auth_users.service.AuthService;
import com.abc.abcbank.response.Response;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<Response<String>> register (@RequestBody @Valid RegistrationRequest registrationRequest) {
        return ResponseEntity.ok(authService.register(registrationRequest));
    }
}
