package com.example.gustavioandroidstudio;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("videojuegos")  // Debe coincidir con el endpoint de tu API en Spring Boot
    Call<List<Game>> getUsuarios();
}
