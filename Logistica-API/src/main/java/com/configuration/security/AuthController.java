////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 2020                                                          /
// developed by Abdelilah Dehaoui GitHub : Abdelilah00                         /
////////////////////////////////////////////////////////////////////////////////

package com.configuration.security;

import com.configuration.Exception.ApiResponse;
import com.configuration.security.domains.Role;
import com.configuration.security.domains.RoleName;
import com.configuration.security.domains.User;
import com.configuration.security.dto.JwtResponseDto;
import com.configuration.security.dto.SignInRequestDto;
import com.configuration.security.dto.SignUpRequestDto;
import com.configuration.security.jwt.JwtTokenProvider;
import com.configuration.security.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping(path = "signIn")
    public JwtResponseDto signIn(@Valid @RequestBody SignInRequestDto signInRequest) {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getUserName(), signInRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return new JwtResponseDto(jwtTokenProvider.createToken(userDetails));
    }

    @PostMapping(path = "signUp")
    public ResponseEntity<ApiResponse> signUp(@Valid @RequestBody SignUpRequestDto signUpRequest) {
        if (userRepository.existsByUserName(signUpRequest.getUserName())) {
            return new ResponseEntity<>(new ApiResponse(false, "Username is already taken!"), HttpStatus.BAD_REQUEST);
        }
        User result = userRepository.save(new User(signUpRequest.getUserName(), passwordEncoder.encode(signUpRequest.getPassword()), "y@y.com", true, Collections.singletonList(new Role(RoleName.ROLE_ADMIN, null))));
        URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/auth/signIn/{userName}").buildAndExpand(result.getUserName()).toUri();
        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
    }

}
