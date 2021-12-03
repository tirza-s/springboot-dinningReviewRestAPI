package be.tirza.dinningreviewapi.service.impl;

import be.tirza.dinningreviewapi.entity.Restaurant;
import be.tirza.dinningreviewapi.entity.Review;
import be.tirza.dinningreviewapi.exception.ResourceNotFoundException;
import be.tirza.dinningreviewapi.payload.ReviewDTO;
import be.tirza.dinningreviewapi.repository.RestaurantRepository;
import be.tirza.dinningreviewapi.repository.ReviewRepository;
import be.tirza.dinningreviewapi.service.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImp implements ReviewService {

    private ReviewRepository reviewRepository;
    private RestaurantRepository restaurantRepository;

    public ReviewServiceImp(ReviewRepository reviewRepository, RestaurantRepository restaurantRepository) {
        this.reviewRepository = reviewRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public ReviewDTO createReview(long restaurantId, ReviewDTO reviewDTO) {

        //Convert DTO into Entity
        Review review = mapToEntity(reviewDTO);

        //Retrieve post entity by id
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", restaurantId));

        //set restaurant to review entity
        review.setRestaurant(restaurant);

        //save review entity to db
        Review newReview = reviewRepository.save(review);

        return  mapToDTO(newReview);
    }

    @Override
    public List<ReviewDTO> getReviewByRestaurantId(long restaurantId) {
        //retrieve comment by restaurantId
        List<Review> reviews = reviewRepository.findByRestaurantId(restaurantId);

        return reviews.stream().map(review -> (mapToDTO(review))).collect(Collectors.toList());
    }

    // convert Entity into DTO
    private ReviewDTO mapToDTO(Review review) {
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setId(review.getId());
        reviewDTO.setSubmitBy(review.getSubmitBy());
        reviewDTO.setEmail(review.getEmail());
        reviewDTO.setComment(review.getComment());

        return reviewDTO;
    }

    // convert DTO into Entity
    private Review mapToEntity(ReviewDTO reviewDTO){
        Review review = new Review();
        review.setId(reviewDTO.getId());
        review.setSubmitBy(reviewDTO.getSubmitBy());
        review.setEmail(reviewDTO.getEmail());
        review.setComment(reviewDTO.getComment());

        return review;
    }

}
