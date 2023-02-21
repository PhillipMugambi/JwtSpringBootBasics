package jwttest.jwt.UserDetailsService;

import jwttest.jwt.Repositories.UserRepository;
import jwttest.jwt.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);

        return new User(user.getId(),user.getUsername(),user.getPassword(),user.getEmail(),user.getRole());
    }
}
