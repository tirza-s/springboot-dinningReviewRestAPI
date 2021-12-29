package be.tirza.dinningreviewapi.payload;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@ApiModel(description= "Review model information")
@Data
public class ReviewDTO {

    @ApiModelProperty(value="Review id")
    private Long id;

    @ApiModelProperty(value="Review submitBy")
    @NotEmpty(message = "submitBy may not be null or empty")
    private String submitBy;

    @ApiModelProperty(value="Review email")
    @NotEmpty(message = "Email may not be null or empty")
    @Email
    private String email;

    @ApiModelProperty(value="Review body")
    @NotEmpty
    @Size(min = 10, message = "Review must have minimum 10 character")
    private String comment;
}
