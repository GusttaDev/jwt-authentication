package br.com.jwt.authenticaton.jwtauthentication.controller;

import br.com.jwt.authenticaton.jwtauthentication.config.PasswordEncoderConfig;
import br.com.jwt.authenticaton.jwtauthentication.model.UserAuthentication;
import br.com.jwt.authenticaton.jwtauthentication.service.UserAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserAuthenticationService userAuthenticationService;

    @Autowired
    private PasswordEncoderConfig passwordEncoder;

    @GetMapping
    public ResponseEntity<List<UserAuthentication>> findAll(){
        return ResponseEntity.ok(userAuthenticationService.findAll());
    }

    @PostMapping
    public ResponseEntity<UserAuthentication> create(@RequestBody UserAuthentication user){
        user.setPassword(passwordEncoder.getPasswordEncoder().encode(user.getPassword()));
        return ResponseEntity.status(HttpStatus.CREATED).body(userAuthenticationService.create(user));
    }

    @GetMapping("/validatePassword")
    public ResponseEntity<?> validatePassword(@RequestParam String login, @RequestParam String password){
        HttpStatus status = userAuthenticationService.validatePassword(login, password);
        return ResponseEntity.status(status).body("");
    }
}
