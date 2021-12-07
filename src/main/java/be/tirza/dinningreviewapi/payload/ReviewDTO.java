package be.tirza.dinningreviewapi.payload;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class ReviewDTO {

    private Long id;

    @NotEmpty(message = "submitBy may not be null or empty")
    private String submitBy;

    @NotEmpty(message = "Email may not be null or empty")
    @Email
    private String email;

    @NotEmpty
    @Size(min = 10, message = "Review must have minimum 10 character")
    private String comment;
}
