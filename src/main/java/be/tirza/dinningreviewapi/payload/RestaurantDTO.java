package be.tirza.dinningreviewapi.payload;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@ApiModel(description = "Restaurant model information")
@Data
public class RestaurantDTO {

    @ApiModelProperty(value = "Restaurant id")
    private Long id;

    @ApiModelProperty(value = "Restaurant name")
    @NotEmpty
    @Size(min = 2, message = "Restaurant name should have min 2 character and may not be empty")
    private String name;

    @ApiModelProperty(value = "Restaurant city")
    @NotEmpty(message = "City may not be null or empty")
    private String city;

    @ApiModelProperty(value = "Restaurant address")
    @NotEmpty(message = "Address may not be null or empty")
    private String address;

    @ApiModelProperty(value = "Restaurant state")
    @NotEmpty(message = "State may not be null or empty")
    private String state;

    @ApiModelProperty(value = "Restaurant zipCode")
    @NotEmpty(message = "zipCode may not be null or empty")
    private String zipCode;

    @ApiModelProperty(value = "Restaurant phoneNumber")
    @NotEmpty(message = "phoneNumber may not be null or empty")
    private String phoneNumber;

    @ApiModelProperty(value = "Restaurant website")
    @NotEmpty(message = "Website may not be null or empty")
    private String website;

    // to be able to see the reviews
    @ApiModelProperty(value = "Restaurant review")
    private Set<ReviewDTO> reviews;
}
