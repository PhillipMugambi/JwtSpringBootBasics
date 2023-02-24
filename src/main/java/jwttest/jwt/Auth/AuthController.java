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
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {

        return ResponseEntity.ok(authenticationService.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> Register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authenticationService.register(request));
    }
    @GetMapping("/getusers")
    public List<Customer> findusers(){
        return authenticationService.getUsers();
    }
    @PostMapping("DeleteUser")
        public ResponseEntity deleteUser(@RequestBody DeleteCusomerRequest request){
        return ResponseEntity.ok(authenticationService.deleteUser(request));
        }
    @PostMapping("DeleteUser")
    public ResponseEntity deleteAllUsers(){
        return ResponseEntity.ok(authenticationService.deleteAllUsers());
    }
}

