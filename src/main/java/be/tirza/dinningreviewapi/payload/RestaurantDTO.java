package be.tirza.dinningreviewapi.payload;

import lombok.Data;

@Data
public class RestaurantDTO {
    private Long id;
    private String name;
    private String city;
    private String address;
    private String state;
    private String zipCode;

    private String phoneNumber;
    private String website;

    private Integer overallScore;
    private Boolean peanutScore;
    private Boolean glutenScore;
    private Boolean dairyScore;
}
