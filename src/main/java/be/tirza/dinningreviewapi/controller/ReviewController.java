package be.tirza.dinningreviewapi.controller;

import be.tirza.dinningreviewapi.payload.ReviewDTO;
import be.tirza.dinningreviewapi.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1")
public class ReviewController {

    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/restaurants/{restaurantId}/reviews")
    public ResponseEntity<ReviewDTO> createReview(@PathVariable(value = "restaurantId") long restaurantId,
                                                   @Valid @RequestBody ReviewDTO reviewDTO) {

        return new ResponseEntity<>(reviewService.createReview(restaurantId, reviewDTO), HttpStatus.CREATED);
    }

    //http://localhost:8080/api/v1/restaurants/12/reviews
    @GetMapping("/restaurants/{restaurantId}/reviews")
    public List<ReviewDTO> getAllReviewsByRestaurantId(@PathVariable(value = "restaurantId") Long restaurantId) {
        return reviewService.getReviewByRestaurantId(restaurantId);
    }

    //http://localhost:8080/api/v1/restaurants/12/reviews/3
    @GetMapping("/restaurants/{restaurantId}/reviews/{id}")
    public ResponseEntity<ReviewDTO> getReviewById(@PathVariable(value = "restaurantId") Long restaurantId,
                                                   @PathVariable(value = "id") Long reviewId) {

        ReviewDTO reviewDTO = reviewService.getReviewsById(restaurantId, reviewId);
        return new ResponseEntity<>(reviewDTO, HttpStatus.OK);
    }

    // http://localhost:8080/api/v1/restaurants/13/reviews/4
    @PutMapping("/restaurants/{restaurantId}/reviews/{id}")
    public ResponseEntity<ReviewDTO> updateComment(@PathVariable(value="restaurantId") Long restaurantId,
                                                   @PathVariable(value="id")Long reviewId,
                                                   @Valid @RequestBody ReviewDTO reviewDTO) {

        ReviewDTO updatedReview = reviewService.updateReview(restaurantId, reviewId, reviewDTO);
        return new ResponseEntity<>(updatedReview, HttpStatus.OK);
    }

    @DeleteMapping("/restaurants/{restaurantId}/reviews/{id}")
    public ResponseEntity<String> deleteReView(@PathVariable(value="restaurantId") Long restaurantId,
                                               @PathVariable(value="id")Long reviewId) {

        reviewService.deleteReview(restaurantId, reviewId);
        return new ResponseEntity<>("Comment has been deleted successfully", HttpStatus.OK);
    }
}
