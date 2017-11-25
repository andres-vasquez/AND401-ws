package com.example.android_instructor.webservices1;

import com.example.android_instructor.webservices1.model.Clima;
import com.example.android_instructor.webservices1.model.Pronostico;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Android-Instructor on 20/11/2017.
 */

public interface ApiService {
    @Headers({"Content-Type: application/json"})
    @GET("/data/2.5/weather")
    Call<Clima> obtenerClimaPorLugar(@Query("lat") double lat,
                                     @Query("lon") double lon,
                                     @Query("appid") String apiKey,
                                     @Query("units") String units);

    @Headers({"Content-Type: application/json"})
    @GET("/data/2.5/forecast")
    Call<Pronostico> obtenerPronosticoPorLugar(@Query("lat") double lat,
                                          @Query("lon") double lon,
                                          @Query("appid") String apiKey);
}
