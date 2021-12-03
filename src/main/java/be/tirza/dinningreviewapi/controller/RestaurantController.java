package be.tirza.dinningreviewapi.controller;

import be.tirza.dinningreviewapi.entity.Restaurant;
import be.tirza.dinningreviewapi.payload.RestaurantDTO;
import be.tirza.dinningreviewapi.payload.RestaurantResponse;
import be.tirza.dinningreviewapi.service.RestaurantService;
import be.tirza.dinningreviewapi.util.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    private RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    //create a restaurant rest api
    @PostMapping
    public ResponseEntity<RestaurantDTO> createRestaurant(@RequestBody RestaurantDTO restaurantDTO){ // convert json to java object
        return new ResponseEntity<>(restaurantService.createRestaurant(restaurantDTO), HttpStatus.CREATED);
    }

    //get all restaurant rest api
    //http://localhost:8080/api/restaurants?pageNo=0&pageSize=5
    //http://localhost:8080/api/restaurants?pageNo=0&pageSize=10&sortBy=name&sortDir=asc
    @GetMapping
    public RestaurantResponse getAllRestaurant
            (@RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
             @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
             @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
             @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
            ){
        return restaurantService.getAllRestaurant(pageNo, pageSize, sortBy, sortDir);
    }

    //get restaurant by id
    @GetMapping("/{id}")
    public ResponseEntity getRestaurantById(@PathVariable(name = "id") long id) {
            return ResponseEntity.ok(restaurantService.getRestaurantById(id));
        }

    //update restaurant by id
    @PutMapping("/{id}")
    public ResponseEntity<RestaurantDTO> updateRestaurant(@RequestBody RestaurantDTO restaurantDTO, @PathVariable(name = "id") long id){

        RestaurantDTO restaurantResponse = restaurantService.updateRestaurant(restaurantDTO, id);
        return new ResponseEntity<>(restaurantResponse, HttpStatus.OK);
    }

    //delete post by id
    @DeleteMapping("/{id}")
    public ResponseEntity<String>deleteRestaurant(@PathVariable (name = "id") long id){
        restaurantService.deleteRestaurantById(id);

        return new ResponseEntity<>("Restaurant has been deleted successfully", HttpStatus.OK);
    }
}
