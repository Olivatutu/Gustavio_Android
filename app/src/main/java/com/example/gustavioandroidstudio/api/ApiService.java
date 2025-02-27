package com.example.gustavioandroidstudio.api;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("videojuegos.php")  // Debe coincidir con el endpoint de tu API en Spring Boot
    Call<Game> getVideojuegos();  // Cambié el nombre del método
}