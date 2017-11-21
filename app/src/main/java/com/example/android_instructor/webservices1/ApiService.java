package com.example.android_instructor.webservices1;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Android-Instructor on 20/11/2017.
 */

public interface ApiService {
    @Headers({"Content-Type: application/json"})
    @GET("/posts")
    Call<List<PostObj>> getPosts();

    @Headers({"Content-Type: application/json"})
    @GET("/posts/{postId}")
    Call<PostObj> getPost(@Path("postId") int id);

    @Headers({"Content-Type: application/json"})
    @POST("/posts")
    Call<PostObj> enviarPost(@Body PostObj obj);
}
