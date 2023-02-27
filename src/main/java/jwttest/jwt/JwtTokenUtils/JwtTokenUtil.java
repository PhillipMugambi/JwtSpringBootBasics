package jwttest.jwt.JwtTokenUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jwttest.jwt.Repositories.CustomerRepository;
import jwttest.jwt.models.Customer;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static org.apache.coyote.http11.Constants.a;

@Service
public class JwtTokenUtil {
   // UserServiceImpl userServiceImpl;
    //private String secret = "9MFJ6Rt9pqzBynSV";
    private final String secret = "SECRETKESECRETKEYYSECRETKESECRETKEYSECRETKEY";
    //SECRETKESECRETKEYYSECRETKESECRETKEYSECRETKEY
    //private final String jwtSecret = "yBKrn1G0b7cY";
    private final String jwtIssuer = "deliverance.com";  //https://passwordsgenerator.net/

private CustomerRepository customerRepository;
    private Key getsignInKey(){
byte[] keyBytes= Decoders.BASE64.decode(secret);
return Keys.hmacShaKeyFor(keyBytes);
    }
    public String extractUsername(String token){
        return extractClaim(token,Claims::getSubject);
    }
    public String extractOccupation(String token){
        return extractClaim(token,Claims::getAudience);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getsignInKey())
                .build().parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
    public String createToken(Customer customer) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder().setClaims(claims).setSubject(customer.getUsername()).
                setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1800000)) //30 minutes
                .setIssuedAt(new Date())
                .signWith(getsignInKey(),SignatureAlgorithm.HS256).compact();
    }
    public Boolean validateToken(String token, UserDetails  userDetails) {

        final String username = extractUsername(token);
       // throw a 401 error to show token has expired
        if(isTokenExpired(token)) throw new RuntimeException("Token has expired");//throw a 401 error to show token has expired
    return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}


