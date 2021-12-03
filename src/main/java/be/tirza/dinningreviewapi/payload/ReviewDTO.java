package be.tirza.dinningreviewapi.payload;

import lombok.Data;

@Data
public class ReviewDTO {

    private Long id;
    private String submitBy;
    private String email;
    private String comment;

}
