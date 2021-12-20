package be.tirza.dinningreviewapi.repository;

import be.tirza.dinningreviewapi.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    Optional<Restaurant> findAByZipCode (String zipCode);

    Boolean existsByZipCode(String zipCode);
}
