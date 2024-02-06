package org.example.kunuz.controller;

import jakarta.validation.Valid;
import org.example.kunuz.dto.AuthDtO;
import org.example.kunuz.dto.ProfileDTO;
import org.example.kunuz.dto.RegistrationDTO;
import org.example.kunuz.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthConroller {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ProfileDTO> login(@Valid @RequestBody AuthDtO auth) {
        return ResponseEntity.ok(authService.auth(auth));

    }
    @PostMapping("/registration")
    public ResponseEntity<Boolean> registration(@Valid @RequestBody RegistrationDTO dto) {
        return ResponseEntity.ok(authService.registration(dto));
    }

    @GetMapping("/verification/email/{jwt}")
    public ResponseEntity<String> emailVerification(@PathVariable("jwt") String jwt) {
        return ResponseEntity.ok(authService.emailVerification(jwt));
    }


}
