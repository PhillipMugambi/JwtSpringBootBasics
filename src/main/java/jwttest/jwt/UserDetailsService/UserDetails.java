//package jwttest.jwt.UserDetailsService;
//import jwttest.jwt.models.Role;
//import jwttest.jwt.models.Customer;
//import lombok.Builder;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//@Builder
//@Component
//@RequiredArgsConstructor
//public class UserDetails {
//   Customer user = new Customer();
//   private Role role;
//    public UserDetails(Customer user1) {
//        this.user=user1;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//       return List.of(new SimpleGrantedAuthority(role.name()));
//    }
//
//    @Override
//    public String getPassword() {
//        return user.getPassword();
//    }
//
//    @Override
//    public String getUsername() {
//        return user.getUsername();
//    }
//    //public String getemail(){return user.getEmail();}
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//}
