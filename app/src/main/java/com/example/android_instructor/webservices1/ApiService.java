package com.example.android_instructor.webservices1;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * Created by Android-Instructor on 20/11/2017.
 */

public interface ApiService {
    @Headers({"Content-Type: application/json"})
    @GET("/posts")
    Call<List<PostObj>> getPosts();
}
