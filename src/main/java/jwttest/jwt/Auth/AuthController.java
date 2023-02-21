package jwttest.jwt.Auth;

import jwttest.jwt.Repositories.UserRepository;
import jwttest.jwt.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Streamable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")

public class AuthController {
    private final UserRepository userRepository;
    private final AuthenticationService authenticationService;
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest Request) {

        return ResponseEntity.ok(authenticationService.login(Request));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> Register(@RequestBody RegisterRequest Request) {
        return ResponseEntity.ok(authenticationService.register(Request));
    }
    @GetMapping("/getall")
    public List<User> getallUsers() {
List<User> Users= new ArrayList<>();
Streamable.of(userRepository.findAll()).forEach(Users::add);
        return Users;
    }}

