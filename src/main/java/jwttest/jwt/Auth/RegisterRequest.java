package jwttest.jwt.Auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {
    private Timestamp created_at;
    private String firstname;
    private String secondname;
    private String lastname;
    private String D_O_B;
    private String gender;
    private String ocupation;
    private String email;
    private String password;
    private String deviceId;
}