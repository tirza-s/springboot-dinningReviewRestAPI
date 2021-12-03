package be.tirza.dinningreviewapi.controller;

import be.tirza.dinningreviewapi.payload.ReviewDTO;
import be.tirza.dinningreviewapi.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class ReviewController {

    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    //create a review for restaurant
    @PostMapping("/restaurants/{restaurantId}/reviews")
    public ResponseEntity<ReviewDTO> createReview (@PathVariable(value ="restaurantId") long restaurantId,
                                                   @RequestBody ReviewDTO reviewDTO){
        return new ResponseEntity<>(reviewService.createReview(restaurantId, reviewDTO), HttpStatus.CREATED);
    }

    //http://localhost:8080/api/restaurants/12/reviews
    @GetMapping("/restaurants/{restaurantId}/reviews")
    public List<ReviewDTO> getAllReviewsByRestaurantId(@PathVariable(value = "restaurantId") Long restaurantId){
        return reviewService.getReviewByRestaurantId(restaurantId);
    }
}
