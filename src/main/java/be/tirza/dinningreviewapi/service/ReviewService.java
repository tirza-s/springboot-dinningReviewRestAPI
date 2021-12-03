package be.tirza.dinningreviewapi.service;

import be.tirza.dinningreviewapi.payload.ReviewDTO;

import java.util.List;

public interface ReviewService {

    ReviewDTO createReview(long restaurantId, ReviewDTO reviewDTO);

    List<ReviewDTO> getReviewByPostId(long restaurantId);
}
