package be.tirza.dinningreviewapi.controller;

import be.tirza.dinningreviewapi.payload.RestaurantDTO;
import be.tirza.dinningreviewapi.payload.RestaurantResponse;
import be.tirza.dinningreviewapi.service.RestaurantService;
import be.tirza.dinningreviewapi.util.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping()
public class RestaurantController {

    private RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    //create restaurant rest api
    @PostMapping("/api/v1/restaurants")
    public ResponseEntity<RestaurantDTO> createRestaurant(@Valid @RequestBody RestaurantDTO restaurantDTO) { // convert json to java object
        return new ResponseEntity<>(restaurantService.createRestaurant(restaurantDTO), HttpStatus.CREATED);
    }

    //get all restaurant rest api
    //http://localhost:8080/api/restaurants?pageNo=0&pageSize=5
    //http://localhost:8080/api/restaurants?pageNo=0&pageSize=10&sortBy=name&sortDir=asc
    @GetMapping("/api/v1/restaurants")
    public RestaurantResponse getAllRestaurant
    (@RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
     @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
     @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
     @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ) {
        return restaurantService.getAllRestaurant(pageNo, pageSize, sortBy, sortDir);
    }

    //get restaurant by id
    @GetMapping("/api/v1/restaurants/{id}")
    public ResponseEntity<RestaurantDTO> getRestaurantById(@PathVariable(name = "id") long id) {
        return ResponseEntity.ok(restaurantService.getRestaurantById(id));
    }

    //get restaurant by zipCode
    @GetMapping("/api/v1/restaurants/zipCode/{zipCode}")
    public ResponseEntity getRestaurantByZipCode(@PathVariable(name = "zipCode") String zipCode) {

        List<RestaurantDTO> restaurantDTO = restaurantService.getRestaurantByZipCode(zipCode);
        return new ResponseEntity<>(restaurantDTO, HttpStatus.OK);
    }

    //update restaurant by id rest api
    @PutMapping("/api/v1/restaurants/{id}")
    public ResponseEntity<RestaurantDTO> updateRestaurant(@Valid @RequestBody RestaurantDTO restaurantDTO,
                                                          @PathVariable(name = "id") long id) {

        RestaurantDTO restaurantResponse = restaurantService.updateRestaurant(restaurantDTO, id);
        return new ResponseEntity<>(restaurantResponse, HttpStatus.OK);
    }

    //delete post by id
    @DeleteMapping("/api/v1/restaurants/{id}")
    public ResponseEntity<String> deleteRestaurant(@PathVariable(name = "id") long id) {
        restaurantService.deleteRestaurantById(id);

        return new ResponseEntity<>("Restaurant has been deleted successfully", HttpStatus.OK);
    }
}
