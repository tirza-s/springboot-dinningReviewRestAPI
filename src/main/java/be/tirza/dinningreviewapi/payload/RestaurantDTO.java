package be.tirza.dinningreviewapi.payload;

import lombok.Data;

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
}
