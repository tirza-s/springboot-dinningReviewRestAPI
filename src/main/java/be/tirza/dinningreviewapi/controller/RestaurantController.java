package be.tirza.dinningreviewapi.controller;

import be.tirza.dinningreviewapi.payload.RestaurantDTO;
import be.tirza.dinningreviewapi.payload.RestaurantResponse;
import be.tirza.dinningreviewapi.service.RestaurantService;
import be.tirza.dinningreviewapi.util.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping()
public class RestaurantController {

    private RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    /**
     * This api is to create a new restaurant -> check status code 201
     * http://localhost:8080/api/v1/restaurants
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/api/v1/restaurants")
    public ResponseEntity<RestaurantDTO> createRestaurant(@Valid @RequestBody RestaurantDTO restaurantDTO) { // convert json to java object
        return new ResponseEntity<>(restaurantService.createRestaurant(restaurantDTO), HttpStatus.CREATED);
    }

    /**
     * This api is to get all restaurant list
     * http://localhost:8080/api/v1/restaurants
     * http://localhost:8080/api/v1/restaurants?pageNo=0&pageSize=5
     */
    @GetMapping("/api/v1/restaurants")
    public RestaurantResponse getAllRestaurant
    (@RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
     @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize
    ) {
        return restaurantService.getAllRestaurant(pageNo, pageSize);
    }

    /**
     * This api is to get all restaurant by id
     * http://localhost:8080/api/v1/restaurants/7
     */
    @GetMapping("/api/v1/restaurants/{id}")
    public ResponseEntity<RestaurantDTO> getRestaurantById(@PathVariable(name = "id") long id) {
        return ResponseEntity.ok(restaurantService.getRestaurantById(id));
    }

    /**
     * This api is to get all restaurant by zipCode
     * http://localhost:8080/api/v1/restaurants//zipCode/1000
     */
    @GetMapping("/api/v1/restaurants/zipCode/{zipCode}")
    public ResponseEntity getRestaurantByZipCode(@PathVariable(name = "zipCode") String zipCode) {

        List<RestaurantDTO> restaurantDTO = restaurantService.getRestaurantByZipCode(zipCode);
        return new ResponseEntity<>(restaurantDTO, HttpStatus.OK);
    }

    /**
     * This api is to update restaurant by id
     * http://localhost:8080/api/v1/restaurants/7
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/api/v1/restaurants/{id}")
    public ResponseEntity<RestaurantDTO> updateRestaurant(@Valid @RequestBody RestaurantDTO restaurantDTO,
                                                          @PathVariable(name = "id") long id) {

        RestaurantDTO restaurantResponse = restaurantService.updateRestaurant(restaurantDTO, id);
        return new ResponseEntity<>(restaurantResponse, HttpStatus.OK);
    }

    /**
     * This api is to delete restaurant by id
     * http://localhost:8080/api/v1/restaurants/7
     */
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/api/v1/restaurants/{id}")
    public ResponseEntity<String> deleteRestaurant(@PathVariable(name = "id") long id) {
        restaurantService.deleteRestaurantById(id);

        return new ResponseEntity<>("Restaurant has been deleted successfully", HttpStatus.OK);
    }
}
