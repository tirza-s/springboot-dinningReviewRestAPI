package be.tirza.dinningreviewapi.service.impl;

import be.tirza.dinningreviewapi.entity.Restaurant;
import be.tirza.dinningreviewapi.entity.Review;
import be.tirza.dinningreviewapi.exception.ResourceNotFoundException;
import be.tirza.dinningreviewapi.exception.RestaurantApiException;
import be.tirza.dinningreviewapi.payload.ReviewDTO;
import be.tirza.dinningreviewapi.repository.RestaurantRepository;
import be.tirza.dinningreviewapi.repository.ReviewRepository;
import be.tirza.dinningreviewapi.service.ReviewService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImp implements ReviewService {

    private ReviewRepository reviewRepository;
    private RestaurantRepository restaurantRepository;
    private ModelMapper modelMapper;

    public ReviewServiceImp(ReviewRepository reviewRepository,
                            RestaurantRepository restaurantRepository,
                            ModelMapper modelMapper) {
        this.reviewRepository = reviewRepository;
        this.restaurantRepository = restaurantRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ReviewDTO createReview(long restaurantId, ReviewDTO reviewDTO) {

        //Convert DTO into Entity
        Review review = mapToEntity(reviewDTO);

        //Retrieve restaurant entity by id
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", restaurantId));

        //set restaurant to review entity
        review.setRestaurant(restaurant);

        //save review entity to db
        Review newReview = reviewRepository.save(review);

        return mapToDTO(newReview);
    }

    @Override
    public List<ReviewDTO> getReviewByRestaurantId(long restaurantId) {
        //retrieve comment by restaurantId
        List<Review> reviews = reviewRepository.findByRestaurantId(restaurantId);

        return reviews.stream().map(review -> (mapToDTO(review))).collect(Collectors.toList());
    }

    @Override
    public ReviewDTO getReviewsById(Long restaurantId, Long reviewId) {

        //Retrieve restaurant entity by id
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", restaurantId));

        //Retrieve review entity by id
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ResourceNotFoundException("Review", "id", reviewId));

        //if comment doesn't belong to a restaurant then it will throw RestaurantApiException
        if (!review.getRestaurant().getId().equals(restaurant.getId())) {
            throw new RestaurantApiException(HttpStatus.BAD_REQUEST, "Review does not belong to the Restaurant. Please enter the right restaurant id");
        }

        return mapToDTO(review);
    }

    @Override
    public ReviewDTO updateReview(Long restaurantId, long reviewId, ReviewDTO reviewRequest) {

        //Retrieve restaurant entity by id
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", restaurantId));

        //Retrieve review entity by id
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ResourceNotFoundException("Review", "id", reviewId));

        if (!review.getRestaurant().getId().equals(restaurant.getId())) {
            throw new RestaurantApiException(HttpStatus.BAD_REQUEST, "Review does not belong to the Restaurant. Please enter the right restaurant id");
        }

        review.setSubmitBy(reviewRequest.getSubmitBy());
        review.setEmail(reviewRequest.getEmail());
        review.setComment(reviewRequest.getComment());

        Review updatedReview = reviewRepository.save(review);
        return mapToDTO(updatedReview);
    }

    @Override
    public void deleteReview(Long restaurantId, Long reviewId) {

        //Retrieve restaurant entity by id
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", restaurantId));

        //Retrieve review entity by id
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ResourceNotFoundException("Review", "id", reviewId));

        if (!review.getRestaurant().getId().equals(restaurant.getId())) {
            throw new RestaurantApiException(HttpStatus.BAD_REQUEST, "Review does not belong to the Restaurant. Please enter the right restaurant id");
        }

        reviewRepository.delete(review);
    }

    // convert Entity into DTO
    private ReviewDTO mapToDTO(Review review) {

        ReviewDTO reviewDTO = modelMapper.map(review, ReviewDTO.class);

//        ReviewDTO reviewDTO = new ReviewDTO();
//        reviewDTO.setId(review.getId());
//        reviewDTO.setSubmitBy(review.getSubmitBy());
//        reviewDTO.setEmail(review.getEmail());
//        reviewDTO.setComment(review.getComment());

        return reviewDTO;
    }

    // convert DTO into Entity
    private Review mapToEntity(ReviewDTO reviewDTO) {

        Review review = modelMapper.map(reviewDTO, Review.class);
//        Review review = new Review();
//        review.setId(reviewDTO.getId());
//        review.setSubmitBy(reviewDTO.getSubmitBy());
//        review.setEmail(reviewDTO.getEmail());
//        review.setComment(reviewDTO.getComment());

        return review;
    }
}
