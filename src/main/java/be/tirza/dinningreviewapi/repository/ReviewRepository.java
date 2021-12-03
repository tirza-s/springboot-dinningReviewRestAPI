package be.tirza.dinningreviewapi.repository;

import be.tirza.dinningreviewapi.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository <Review, Long> {
    List<Review> findPostById(long restaurantId);
}
