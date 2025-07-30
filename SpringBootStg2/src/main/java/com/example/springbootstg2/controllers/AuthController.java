package com.example.springbootstg2.controllers;

import com.example.springbootstg2.models.AgentMaritime;
import com.example.springbootstg2.payload.request.LoginRequest;
import com.example.springbootstg2.payload.response.JwtResponse;
// IMPORT THE NEW MESSAGE RESPONSE CLASS
import com.example.springbootstg2.payload.response.MessageResponse;
import com.example.springbootstg2.repository.AgentRepository;

import com.example.springbootstg2.security.JwtUtils;
import com.example.springbootstg2.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus; // Import HttpStatus

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    AgentRepository agentMaritimeRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(
                jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody AgentMaritime signUpRequest) {
        if (agentMaritimeRepository.existsByUsername(signUpRequest.getUsername())) {
            // Return JSON for error
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST) // Use appropriate HTTP status
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (agentMaritimeRepository.existsByEmail(signUpRequest.getEmail())) {
            // Return JSON for error
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST) // Use appropriate HTTP status
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new agent
        AgentMaritime agent = new AgentMaritime(
                signUpRequest.getName(),
                signUpRequest.getUsername(),
                passwordEncoder.encode(signUpRequest.getPassword()),
                signUpRequest.getEmail(),
                signUpRequest.getRole());

        agentMaritimeRepository.save(agent);


        return ResponseEntity.ok(new MessageResponse("Agent registered successfully!"));
    }
}