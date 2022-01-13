function fetchRestaurant() {
    const restaurantId = document.getElementById('restaurant_zipCode');
    fetch(`http://localhost:8080/api/v1/restaurants/zipCode/${restaurantId.value}`)
        .then(response => response.json())
        .then(data => {
            arrayHandler(data, "responseField", htmlForRestaurant)
        })
}

function arrayHandler(arr, targetId, styler) {
    document.getElementById(targetId).innerHTML = '<progress></progress>';
    let html_string = '';
    for (let i = 0; i < arr.length; i++) {
        html_string += styler(arr[i]);
    }
    document.getElementById(targetId).innerHTML = html_string;
}

function htmlForRestaurant(restaurant) {
    let output = '<fieldset>' +
        '<legend> ' + restaurant.name + '</legend>' +
        '<p>' +
        '    City : ' + restaurant.city + '<br>' +
        '    Address : ' + restaurant.address + '<br>' +
        '    State : ' + restaurant.state + '<br>' +
        '    Website : ' + restaurant.zipCode + '<br>' +
        '    Phone number : ' + restaurant.phoneNumber + '<br>' +
        '    Website : ' + restaurant.website + '<br>' +
        '    Reviews : ' + '<br>';

    for (let i = 0; i < restaurant.reviews.length; i++) {
        let review = restaurant.reviews[i];

        output += review.submitBy + '<br>' +
            review.email + '<br>' +
            review.comment + '<br>'
    }

    return output + '</p>' + '</fieldset>';
}
