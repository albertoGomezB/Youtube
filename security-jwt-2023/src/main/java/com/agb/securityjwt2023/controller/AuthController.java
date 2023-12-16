package com.agb.securityjwt2023.controller;

import com.agb.securityjwt2023.dto.AuthenticationRequestDTO;
import com.agb.securityjwt2023.dto.AuthenticationResponseDTO;
import com.agb.securityjwt2023.dto.RegisterRequestDTO;
import com.agb.securityjwt2023.security.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/youtube/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AuthenticationResponseDTO> register (@RequestBody @Valid RegisterRequestDTO registerRequest) {

        return ResponseEntity.ok(authenticationService.register(registerRequest));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponseDTO> authenticate (@RequestBody @Valid AuthenticationRequestDTO authenticationRequest) {

        return ResponseEntity.ok(authenticationService.authenticate(authenticationRequest));
    }
}
