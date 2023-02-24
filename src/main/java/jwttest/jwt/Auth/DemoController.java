package jwttest.jwt.Auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/demo")
public class DemoController {
    @GetMapping
public ResponseEntity<String>sayhello(){
    return ResponseEntity.ok("hello there");
}
}
