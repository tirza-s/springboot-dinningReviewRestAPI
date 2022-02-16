package be.tirza.dinningreviewapi.controller;

import be.tirza.dinningreviewapi.payload.ReviewDTO;
import be.tirza.dinningreviewapi.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/v1")
public class ReviewController {

    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    /**
     * This api is responsible to create a new post to the restaurant base on the restaurant id
     * http://localhost:8080/api/v1/restaurants/2/reviews
     */
    @PostMapping("/restaurants/{restaurantId}/reviews")
    public ResponseEntity<ReviewDTO> createReview(@PathVariable(value = "restaurantId") long restaurantId,
                                                   @Valid @RequestBody ReviewDTO reviewDTO) {

        return new ResponseEntity<>(reviewService.createReview(restaurantId, reviewDTO), HttpStatus.CREATED);
    }

    /**
     * This api is responsible to get the review based on restaurant id
     * http://localhost:8080/api/v1/restaurants/2/reviews
     */
    @GetMapping("/restaurants/{restaurantId}/reviews")
    public List<ReviewDTO> getAllReviewsByRestaurantId(@PathVariable(value = "restaurantId") Long restaurantId) {
        return reviewService.getReviewByRestaurantId(restaurantId);
    }

    /**
     * This api is responsible to get restaurant and review by id
     * http://localhost:8080/api/v1/restaurants/2/reviews/6
     */
    @GetMapping("/restaurants/{restaurantId}/reviews/{id}")
    public ResponseEntity<ReviewDTO> getReviewById(@PathVariable(value = "restaurantId") Long restaurantId,
                                                   @PathVariable(value = "id") Long reviewId) {

        ReviewDTO reviewDTO = reviewService.getReviewsById(restaurantId, reviewId);
        return new ResponseEntity<>(reviewDTO, HttpStatus.OK);
    }

    /**
     * This api is to update the review based on restaurant id
     * http://localhost:8080/api/v1/restaurants/2/reviews/6
     */
    @PutMapping("/restaurants/{restaurantId}/reviews/{id}")
    public ResponseEntity<ReviewDTO> updateComment(@PathVariable(value="restaurantId") Long restaurantId,
                                                   @PathVariable(value="id")Long reviewId,
                                                   @Valid @RequestBody ReviewDTO reviewDTO) {

        ReviewDTO updatedReview = reviewService.updateReview(restaurantId, reviewId, reviewDTO);
        return new ResponseEntity<>(updatedReview, HttpStatus.OK);
    }

    /**
     *  This api is to delete the restaurant and review by id
     *  http://localhost:8080/api/v1/restaurants/2/reviews/6
     */
    @DeleteMapping("/restaurants/{restaurantId}/reviews/{id}")
    public ResponseEntity<String> deleteReView(@PathVariable(value="restaurantId") Long restaurantId,
                                               @PathVariable(value="id")Long reviewId) {

        reviewService.deleteReview(restaurantId, reviewId);
        return new ResponseEntity<>("Comment has been deleted successfully", HttpStatus.OK);
    }
}
