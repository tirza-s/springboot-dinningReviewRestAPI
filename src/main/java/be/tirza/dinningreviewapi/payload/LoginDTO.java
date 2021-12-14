package be.tirza.dinningreviewapi.payload;

import lombok.Data;

@Data
public class LoginDTO {
    private String userNameOrEmail;
    private String password;
}
