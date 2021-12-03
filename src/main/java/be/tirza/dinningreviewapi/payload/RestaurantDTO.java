package be.tirza.dinningreviewapi.payload;

import lombok.Data;

import java.util.Set;

@Data
public class RestaurantDTO {
    private Long id;
    private String name;
    private String city;
    private String address;
    private String state;
    private long zipCode;

    private String phoneNumber;
    private String website;

    private Integer overallScore;

    // to be able to see the reviews
    private Set<ReviewDTO> reviews;
}
