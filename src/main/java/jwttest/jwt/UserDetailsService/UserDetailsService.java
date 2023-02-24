package jwttest.jwt.UserDetailsService;

import jwttest.jwt.Repositories.CustomerRepository;
import jwttest.jwt.models.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    private final CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Customer customer = customerRepository. findCusomerByEmail(email);

        return customer; //(customer.getId(),customer.getUsername(),customer.getPassword(),customer.getEmail(),customer.getRole());
    }
}
