package jwttest.jwt.Auth;

import jwttest.jwt.JwtTokenUtils.JwtTokenUtil;
import jwttest.jwt.Repositories.CustomerRepository;
import jwttest.jwt.models.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private  final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil ;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest request) {
 Customer customerObj = new Customer();
        var customer= customerObj.builder().
                email(request.getEmail())
                .D_O_B(request.getD_O_B()).
                gender(request.getGender()).
                deviceId(request.getDeviceId()).
                created_at(request.getCreated_at())
                .firstname(request.getFirstname())
                .secondname(request.getSecondname()).
                lastname(request.getLastname()).
                ocupation(request.getOcupation()).
                password(passwordEncoder.encode(request.getPassword())).
        created_at(request.getCreated_at()).

                password(passwordEncoder.encode(request.
                        getPassword())).ocupation(request.getOcupation())
        .build();
        customerRepository.save(customer);
        return new AuthenticationResponse("Customer saved");
    }
    public AuthenticationResponse  login(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(), request.getPassword()
        ));
        var user = customerRepository.findCusomerByEmail(request.getEmail());
        System.out.println("user is "+ user);
        var gentoken = "";
        if (user != null) {
            log.info("user found");
            gentoken = jwtTokenUtil.createToken(user);
        }
        return new AuthenticationResponse(gentoken);//.builder()
    }
    public List<Customer> getUsers() {
        return customerRepository.findAll();
    }
      }
