package be.tirza.dinningreviewapi.repository;

import be.tirza.dinningreviewapi.entity.DinningReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository <DinningReview, Long> {
}
