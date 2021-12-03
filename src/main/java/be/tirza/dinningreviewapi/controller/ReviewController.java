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
    public ResponseEntity<ReviewDTO> createReview(@PathVariable(value = "restaurantId") long restaurantId,
                                                  @RequestBody ReviewDTO reviewDTO) {

        return new ResponseEntity<>(reviewService.createReview(restaurantId, reviewDTO), HttpStatus.CREATED);
    }

    //Get all reviews by restaurant id
    //http://localhost:8080/api/restaurants/12/reviews
    @GetMapping("/restaurants/{restaurantId}/reviews")
    public List<ReviewDTO> getAllReviewsByRestaurantId(@PathVariable(value = "restaurantId") Long restaurantId) {
        return reviewService.getReviewByRestaurantId(restaurantId);
    }

    //Get review by id
    //http://localhost:8080/api/restaurants/12/reviews/3
    @GetMapping("/restaurants/{restaurantId}/reviews/{id}")
    public ResponseEntity<ReviewDTO> getReviewById(@PathVariable(value = "restaurantId") Long restaurantId,
                                                   @PathVariable(value = "id") Long reviewId) {

        ReviewDTO reviewDTO = reviewService.getReviewsById(restaurantId, reviewId);
        return new ResponseEntity<>(reviewDTO, HttpStatus.OK);
    }

    //Update review from db
    @PutMapping("/restaurants/{restaurantId}/reviews/{id}")
    public ResponseEntity<ReviewDTO> updateComment(@PathVariable(value="restaurantId") Long restaurantId,
                                                   @PathVariable(value="id")Long reviewId,
                                                   @RequestBody ReviewDTO reviewDTO) {

        ReviewDTO updatedReview = reviewService.updateReview(restaurantId, reviewId, reviewDTO);
        return new ResponseEntity<>(updatedReview, HttpStatus.OK);
    }


    //Deleted reviw from db
    @DeleteMapping("/restaurants/{restaurantId}/reviews/{id}")
    public ResponseEntity<String> deleteReView(@PathVariable(value="restaurantId") Long restaurantId,
                                               @PathVariable(value="id")Long reviewId) {

        reviewService.deleteReview(restaurantId, reviewId);
        return new ResponseEntity<>("Comment has been deleted successfully", HttpStatus.OK);
    }
}
