package be.tirza.dinningreviewapi.repository;

import be.tirza.dinningreviewapi.entity.Restaurant;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@DataJpaTest
@Sql({"/data.sql"})
public class RestaurantRespositoryTests {

   @Autowired
    private RestaurantRepository restaurantRepository;


   @Test
    public void given_when_then() {
       // given - precondition or setup
       List<Restaurant> restaurantList = restaurantRepository.findAllByZipCode("1000");

       // when - action or the behavior to test

       // then - verify the output
       assertThat(restaurantList).isNotNull();
       assertThat(restaurantList).isEqualTo(3);
   }



}
