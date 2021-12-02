package be.tirza.dinningreviewapi.repository;

import be.tirza.dinningreviewapi.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

//    Optional<Restaurant> findRestaurantByZipCode(String zipCode);
}
