package com.team02.best_properta.controller;



import com.team02.best_properta.payload.request.authentication.LoginRequest;


import com.team02.best_properta.payload.response.authentication.JwtResponse;
import com.team02.best_properta.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> authenticateUser(@RequestBody @Valid LoginRequest loginRequest) {
        JwtResponse jwtResponse = authService.authenticateUser(loginRequest);
        return ResponseEntity.ok(jwtResponse);
    }
}
