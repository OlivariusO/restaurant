package com.example.olivar_o.restaurant;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface RestaurantApi {
    @Headers({
            "Accept: application/json",
            "Content-Type: application/x-www-form-urlencoded"
    })
    @GET("api/restaurant")
    Call<List<Restaurant>> getRestaurant();

    @POST("/api/restaurant")
    Call<String> postRestaurant(@Body Restaurant restaurant);
}
