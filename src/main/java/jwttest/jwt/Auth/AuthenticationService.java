package jwttest.jwt.Auth;

import jwttest.jwt.JwtTokenUtils.JwtTokenUtil;
import jwttest.jwt.Repositories.UserRepository;
import jwttest.jwt.models.Role;
import jwttest.jwt.models.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Streamable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private  final UserRepository userRepository ;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil ;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest request) {
 User userObj= new User();
        var user= userObj.builder().username(request.getUsername())
                .email(request.getEmail()).password(passwordEncoder.encode(request.getPassword())).role(Role.valueOf(String.valueOf(Role.USER)))
        .build();
        userRepository.save(user);
        //var gentoken=jwtTokenUtil.createToken(user);
        //return new AuthenticationResponse()//.builder().token(gentoken)
               //.build();
        return new AuthenticationResponse("user created");
    }

    public AuthenticationResponse  login(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(), request.getPassword()
        ));
        var user = userRepository.findUserByUsername(request.getUsername());
        System.out.println("user is "+ user);
        var gentoken = "";
        if (user != null) {
            log.info("user found");
            gentoken = jwtTokenUtil.createToken(user);


        }
        return new AuthenticationResponse(gentoken);//.builder()
                //.token(gentoken)
                //.build();
    }

          //public  List<User> getallUsers() {
//              List<User> Users=new ArrayList<>();
//              Users.addAll(userRepository.findAll());
//return Users;
        //  }
      }
