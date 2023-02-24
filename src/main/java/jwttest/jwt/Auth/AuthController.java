package jwttest.jwt.Auth;

import jwttest.jwt.models.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")

public class AuthController {
    private final AuthenticationService authenticationService;
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest Request) {

        return ResponseEntity.ok(authenticationService.login(Request));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> Register(@RequestBody RegisterRequest Request) {
        return ResponseEntity.ok(authenticationService.register(Request));
    }
    @GetMapping("/getusers")
    public List<Customer> findusers(){
        return authenticationService.getUsers();
    }
}

