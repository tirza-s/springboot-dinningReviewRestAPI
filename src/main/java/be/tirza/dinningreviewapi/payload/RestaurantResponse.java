package be.tirza.dinningreviewapi.payload;

import be.tirza.dinningreviewapi.entity.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantResponse {
    private List<Restaurant> restaurantList;
    private int pageNo;
    private int pageSize;
    private long totalElement; // total records on the database
    private int totalPages;
    private boolean last;
}
