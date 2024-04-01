package com.rodrigo.feedbacksystem.controllers;

import com.rodrigo.feedbacksystem.service.UserService;
import com.rodrigo.feedbacksystem.utils.Converter;
import com.rodrigo.feedbacksystem.dto.AuthenticationDTO;
import com.rodrigo.feedbacksystem.dto.RegisterDTO;
import com.rodrigo.feedbacksystem.dto.TokenDTO;
import com.rodrigo.feedbacksystem.entity.User;
import com.rodrigo.feedbacksystem.repository.UserRepository;
import com.rodrigo.feedbacksystem.security.TokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/autenticar")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());
        TokenDTO tokenDTO = Converter.tokenDTO((User) auth.getPrincipal(), token);

        return ResponseEntity.ok(tokenDTO);

    }

    @PostMapping("/registrar")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO registerDTO){
        User newUser = Converter.user(registerDTO);
        this.userService.salvar(newUser);

        return ResponseEntity.ok().build();
    }
}