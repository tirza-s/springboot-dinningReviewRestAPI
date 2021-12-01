package be.tirza.dinningreviewapi.repository;

import be.tirza.dinningreviewapi.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
