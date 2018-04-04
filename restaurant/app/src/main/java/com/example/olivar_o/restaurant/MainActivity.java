package com.example.olivar_o.restaurant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.GsonBuilder;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private static RestaurantApi restaurantapi;
    private final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.configureRetrofit();
        this.getRestaurantApi();
    }

    private void configureRetrofit()
    {


        retrofit = new Retrofit.Builder()
                .baseUrl("http://172.16.29.226:8000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        restaurantapi = retrofit.create(RestaurantApi.class);


    }

    private void getRestaurantApi()
    {
        restaurantapi.getRestaurant().enqueue(new Callback<List<Restaurant>>() {
            @Override
            public void onResponse(Call<List<Restaurant>> call, Response<List<Restaurant>> response) {
                List<Restaurant> restaurantList = response.body();
                if (restaurantList != null) {
                    for (Restaurant restaurant: restaurantList) {
                        Log.d(TAG, "restaurant reçu: " + restaurant.getId() + " " + restaurant.getName());
                    }
                } else {
                    Log.d(TAG, "OnResponse: " + response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<List<Restaurant>> call, Throwable t) {
                Log.d("XXXXXX", "onFailure" + t.getMessage());

            }
        });
    }





}
