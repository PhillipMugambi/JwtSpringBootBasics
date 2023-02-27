package jwttest.jwt.Filter;
import jwttest.jwt.UserDetailsService.CustomerDetailsService;
import jwttest.jwt.JwtTokenUtils.JwtTokenUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
public
class jwtTokenFilter extends OncePerRequestFilter  {
    @Autowired
    private CustomerDetailsService DetailsService;
    @Autowired
    private  JwtTokenUtil jwtTokenUtil;
    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if (request.getServletPath().equals("/api/auth/login")) {
            filterChain.doFilter(request, response);
        }
        else if (request.getServletPath().equals("/api/auth/register")) {
            filterChain.doFilter(request, response);

        }
        else {
           // UniversalResponse universalResponse;
            String authorizationHeader = request.getHeader("Authorization");
            String token = null;
            String username = null;
            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer")) {
                try {

                    token = authorizationHeader.substring(8);
                    username = jwtTokenUtil.extractUsername(token);
                    if(username != null && SecurityContextHolder.getContext().getAuthentication()==null){
                        UserDetails userDetails = DetailsService.loadUserByUsername(username);
                        if (jwtTokenUtil.validateToken(token, userDetails)) {
                            UsernamePasswordAuthenticationToken authenticationToken = new
                                    UsernamePasswordAuthenticationToken(username, null, userDetails.getAuthorities());
                           authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                            filterChain.doFilter(request, response);
                        }
                    }

                } catch (Exception e) {
                    log.info("exceptions " + e.getMessage());
                    //universalResponse = UniversalResponse.builder()
                           // .status(401)
                            //.message("Token has expired to perform this request")
                           // .build();
                   // response.setContentType(APPLICATION_JSON_VALUE);
                   // new ObjectMapper().writeValue(response.getOutputStream(), universalResponse);
                }
            } else {
                filterChain.doFilter(request, response);
            }

        }

    }
}