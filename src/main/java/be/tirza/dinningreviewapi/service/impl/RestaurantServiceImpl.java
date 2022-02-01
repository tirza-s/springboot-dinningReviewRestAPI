package be.tirza.dinningreviewapi.service.impl;

import be.tirza.dinningreviewapi.entity.Restaurant;
import be.tirza.dinningreviewapi.exception.ResourceNotFoundException;
import be.tirza.dinningreviewapi.payload.RestaurantDTO;
import be.tirza.dinningreviewapi.payload.RestaurantResponse;
import be.tirza.dinningreviewapi.repository.RestaurantRepository;
import be.tirza.dinningreviewapi.service.RestaurantService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
class RestaurantServiceImpl implements RestaurantService {

    private RestaurantRepository restaurantRepository;

    /**
     *  Use Regex for zipCode search
     * "\\d{4}" is to specify the zipCode for this project has 4 characters
     * @see Pattern
     */
    private Pattern zipCodePattern = Pattern.compile("\\d{4}");

    private ModelMapper modelMapper;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository,
                                 ModelMapper modelMapper) {
        this.restaurantRepository = restaurantRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public RestaurantDTO createRestaurant(RestaurantDTO restaurantDTO) {

        //convert DTO into Entity
        Restaurant restaurant = mapToEntity(restaurantDTO);
        Restaurant newRestaurant = restaurantRepository.save(restaurant);

        //convert Entity into DTO
        RestaurantDTO restaurantResponse = mapToDTO(newRestaurant);
        return restaurantResponse;
    }

    @Override
    public RestaurantResponse getAllRestaurant(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        //create pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Restaurant> restaurants = restaurantRepository.findAll(pageable);

        //get content from page object
        List<Restaurant> listOfRestaurant = restaurants.getContent();

        List<RestaurantDTO> restaurantResp = listOfRestaurant.stream()
                .map(restaurant -> mapToDTO(restaurant))
                .collect(Collectors.toList());

        RestaurantResponse restaurantResponse = new RestaurantResponse();
        restaurantResponse.setRestaurantList(listOfRestaurant);
        restaurantResponse.setPageNo(restaurants.getNumber());
        restaurantResponse.setPageSize(restaurants.getSize());
        restaurantResponse.setTotalElement(restaurants.getTotalElements());
        restaurantResponse.setTotalPages(restaurants.getTotalPages());
        restaurantResponse.setLast(restaurants.isLast());

        return restaurantResponse;
    }

    @Override
    public RestaurantDTO getRestaurantById(long id) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant", "id", id));
        return mapToDTO(restaurant);
    }

    @Override
    public List<RestaurantDTO> getRestaurantByZipCode(String zipCode) {
        List<Restaurant> restaurants = restaurantRepository.findAllByZipCode(zipCode);
        if(!zipCode.isEmpty() && !restaurantRepository.existsByZipCode(zipCode)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Restaurant does not exist");
        }
        return restaurants.stream()
                .map(restaurant -> mapToDTO(restaurant))
                .collect(Collectors.toList());
    }

    @Override
    public RestaurantDTO updateRestaurant(RestaurantDTO restaurantDTO, long id) {
        // get post by id from db
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant", "id", id));

        restaurant.setName(restaurantDTO.getName());
        restaurant.setAddress(restaurantDTO.getAddress());
        restaurant.setCity(restaurantDTO.getCity());
        restaurant.setState(restaurantDTO.getState());
        restaurant.setZipCode(restaurantDTO.getZipCode());
        restaurant.setPhoneNumber(restaurantDTO.getPhoneNumber());
        restaurant.setWebsite(restaurantDTO.getWebsite());

        Restaurant updateRestaurant = restaurantRepository.save(restaurant);
        return mapToDTO(updateRestaurant);
    }

    @Override
    public void deleteRestaurantById(long id) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant", "id", id));

        restaurantRepository.delete(restaurant);
    }

    /**
     * Convert Entity into DTO
     * @param restaurant
     * @return restaurantDTO;
     */
    private RestaurantDTO mapToDTO(Restaurant restaurant) {

        RestaurantDTO restaurantDTO = modelMapper.map(restaurant, RestaurantDTO.class);
//        RestaurantDTO restaurantDTO = new RestaurantDTO();
//        restaurantDTO.setId(restaurant.getId());
//        restaurantDTO.setName(restaurant.getName());
//        restaurantDTO.setAddress(restaurant.getAddress());
//        restaurantDTO.setCity(restaurant.getCity());
//        restaurantDTO.setState(restaurant.getState());
//        restaurantDTO.setZipCode(restaurant.getZipCode());
//        restaurantDTO.setPhoneNumber(restaurant.getPhoneNumber());
//        restaurantDTO.setWebsite(restaurant.getWebsite());

        return restaurantDTO;
    }

    /**
     * convert DTO to Entity
     * @param restaurantDTO
     * @return restaurant
     */
    private Restaurant mapToEntity(RestaurantDTO restaurantDTO) {

        Restaurant restaurant = modelMapper.map(restaurantDTO, Restaurant.class);
//        Restaurant restaurant= new Restaurant();
//        restaurant.setName(restaurantDTO.getName());
//        restaurant.setAddress(restaurantDTO.getAddress());
//        restaurant.setCity(restaurantDTO.getCity());
//        restaurant.setState(restaurantDTO.getState());
//        restaurant.setZipCode(restaurantDTO.getZipCode());
//        restaurant.setPhoneNumber(restaurantDTO.getPhoneNumber());
//        restaurant.setWebsite(restaurantDTO.getWebsite());

        return restaurant;
    }
}
