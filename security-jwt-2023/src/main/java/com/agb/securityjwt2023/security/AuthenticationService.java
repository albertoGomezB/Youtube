package com.agb.securityjwt2023.security;

import com.agb.securityjwt2023.dto.AuthenticationRequestDTO;
import com.agb.securityjwt2023.dto.AuthenticationResponseDTO;
import com.agb.securityjwt2023.dto.RegisterRequestDTO;
import com.agb.securityjwt2023.model.Role;
import com.agb.securityjwt2023.model.User;
import com.agb.securityjwt2023.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public AuthenticationResponseDTO register (RegisterRequestDTO request) {

        try {

            var user = User.builder()
                    .firstname(request.getFirstname())
                    .lastname(request.getLastname())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(Role.ADMIN)
                    .build();

            userRepository.save(user);

            var jwtToken = jwtService.generateToken(user);

            return AuthenticationResponseDTO.builder()
                    .token(jwtToken)
                    .build();


        } catch (Exception ex) {
            throw ex;
        }
    }

    public AuthenticationResponseDTO authenticate (AuthenticationRequestDTO request) {

        try {

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );

            var user = userRepository.findByEmail(request.getEmail()).orElseThrow();


            var jwtToken = jwtService.generateToken(user);

            return AuthenticationResponseDTO.builder()
                    .token(jwtToken)
                    .build();

        } catch (Exception ex) {
            throw ex;
        }
    }


}
