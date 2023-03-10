package jwttest.jwt.UserDetailsService;

import jwttest.jwt.Repositories.CustomerRepository;
import jwttest.jwt.models.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CustomerDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    private final CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Customer customer = customerRepository. findCustomerByEmail(email);

        return customer;
    }
}
