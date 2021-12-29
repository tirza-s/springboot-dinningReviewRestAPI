package be.tirza.dinningreviewapi.repository;

import be.tirza.dinningreviewapi.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    List<Restaurant> findAllByZipCode (String zipCode);

    Boolean existsByZipCode(String zipCode);
}
