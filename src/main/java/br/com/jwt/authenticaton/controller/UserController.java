package br.com.jwt.authenticaton.controller;

import br.com.jwt.authenticaton.model.UserAuthentication;
import br.com.jwt.authenticaton.service.UserAuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserAuthenticationService userAuthenticationService;

    private final PasswordEncoder passwordEncoder;

    public UserController(UserAuthenticationService userAuthenticationService, PasswordEncoder passwordEncoder) {
        this.userAuthenticationService = userAuthenticationService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public ResponseEntity<List<UserAuthentication>> findAll(){
        return ResponseEntity.ok(userAuthenticationService.findAll());
    }

    @PostMapping
    public ResponseEntity<UserAuthentication> create(@RequestBody UserAuthentication user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return ResponseEntity.status(HttpStatus.CREATED).body(userAuthenticationService.create(user));
    }

    @GetMapping("/validatePassword")
    public ResponseEntity<?> validatePassword(@RequestParam String login, @RequestParam String password){
        HttpStatus status = userAuthenticationService.validatePassword(login, password);
        return ResponseEntity.status(status).body("");
    }
}
