package com.example.gustavioandroidstudio.api;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RegisterApi {
    @POST("api/users/create")
    Call<RegisterUser> createUser(@Body RegisterRequest registerRequest);
}
