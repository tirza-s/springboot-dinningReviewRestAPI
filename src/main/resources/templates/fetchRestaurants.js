function fetchRestaurant() {
    const restaurantId = document.getElementById('restaurant_zipCode');

    fetch(`http://localhost:8080/api/v1/restaurants/zipCode/${restaurantId.value}`)
        .then(response => {
            if (!response.ok) {
                throw Error("Not Found");
            }
            return response.json()
        }).then(data => {
        console.log(data);
        const htmlRestaurants = data.map(restaurant => {
            return `
                <div class="restaurant-list">
                    <fieldset>
                        <legend> ${restaurant.name}</legend>
                        <p>City: ${restaurant.city}</p>
                        <p>Address: ${restaurant.address}</p>
                        <p>State: ${restaurant.state}</p>
                        <p>ZipCode: ${restaurant.zipCode}</p>
                        <p>Phone number: ${restaurant.phoneNumber}</p>
                        <p>Website: ${restaurant.website}</p>
                        <p>Reviews: ${restaurant.reviews.submitBy}</p>              
                </fieldset>          
                </div>   
               `
        }).join(" ");
        document.querySelector('#responseField').insertAdjacentHTML('afterbegin', htmlRestaurants)
    }).catch(err => {
        console.log(err)
    });
}

fetchRestaurant();

