package org.example.kunuz.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.example.kunuz.dto.AuthDtO;
import org.example.kunuz.dto.ProfileDTO;
import org.example.kunuz.dto.RegistrationDTO;
import org.example.kunuz.service.AuthService;
import org.example.kunuz.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Tag(name = "Ro'yhatdan o'tish", description = "Api list for Authorization")
@RestController
@RequestMapping("/auth")
public class AuthConroller {
    @Autowired
    private AuthService authService;
//    private final Logger log = LoggerFactory.getLogger(AuthConroller.class);
    @PostMapping("/login")
    @Operation( summary = "Api for login", description = "this api used for authorization")
    public ResponseEntity<ProfileDTO> login(@Valid @RequestBody AuthDtO auth) {
        log.info("Login {} ", auth.getEmail());
        return ResponseEntity.ok(authService.auth(auth));
    }
    @PostMapping("/registration")
    public ResponseEntity<Boolean> registration(@Valid @RequestBody RegistrationDTO dto) {
        log.info("registration {} ", dto.getEmail());
        return ResponseEntity.ok(authService.registration(dto));
    }
    @GetMapping("/verification/email/{jwt}")
    public ResponseEntity<String> emailVerification(@PathVariable("jwt") String jwt) {
        log.info("emailVerification {} ", JWTUtil.decode(jwt).getId());
        return ResponseEntity.ok(authService.emailVerification(jwt));
    }
}