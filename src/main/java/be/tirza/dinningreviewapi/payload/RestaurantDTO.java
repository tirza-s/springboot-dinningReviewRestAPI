package be.tirza.dinningreviewapi.payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class RestaurantDTO {

    private Long id;

    @NotEmpty
    @Size(min = 2, message = "Restaurant name should have min 2 character and may not be empty")
    private String name;

    @NotEmpty(message = "City may not be null or empty")
    private String city;

    @NotEmpty(message = "Address may not be null or empty")
    private String address;

    @NotEmpty(message = "State may not be null or empty")
    private String state;

    @NotEmpty(message = "zipCode may not be null or empty")
    private String zipCode;

    @NotEmpty(message = "phoneNumber may not be null or empty")
    private String phoneNumber;

    @NotEmpty(message = "Website may not be null or empty")
    private String website;

    // to be able to see the reviews
    private Set<ReviewDTO> reviews;
}
