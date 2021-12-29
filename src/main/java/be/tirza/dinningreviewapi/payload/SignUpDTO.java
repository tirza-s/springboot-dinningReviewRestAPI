package be.tirza.dinningreviewapi.payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class SignUpDTO {

    @NotEmpty(message = "name may not be null or empty")
    private String name;

    @NotEmpty(message = "userName may not be null or empty")
    private String userName;

    @NotEmpty(message = "email may not be null or empty")
    private String email;

    @NotEmpty(message = "password may not be null or empty")
    private String password;
}
