package be.tirza.dinningreviewapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "restaurant")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private String city;
    private String state;
    private String zipCode;

    private String phoneNumber;
    private String website;

    private Integer overallScore;

    private Boolean peanutScore;
    private Boolean glutenScore;
    private Boolean dairyScore;
}
