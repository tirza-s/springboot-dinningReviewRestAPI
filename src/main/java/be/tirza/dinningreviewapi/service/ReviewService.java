package be.tirza.dinningreviewapi.service;

import be.tirza.dinningreviewapi.payload.ReviewDTO;

import java.util.List;

public interface ReviewService {

    ReviewDTO createReview(long restaurantId, ReviewDTO reviewDTO);

    List<ReviewDTO> getReviewByRestaurantId(long restaurantId);

    ReviewDTO getReviewsById(Long restaurantId, Long reviewId);

    ReviewDTO updateReview(Long restaurantId, long reviewId, ReviewDTO reviewRequest);

    void deleteReview(Long restaurantId, Long reviewId);
}
