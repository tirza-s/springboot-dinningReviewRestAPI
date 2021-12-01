package be.tirza.dinningreviewapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "dinning_review")
public class DinningReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String submitBy;
    private Long restaurantId;
    private String comment;

    private Integer peanutScore;
    private Integer glutenScore;
    private Integer dairyScore;

    private ReviewStatus reviewStatus;

}
