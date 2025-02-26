package com.example.gustavioandroidstudio;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gustavioandroidstudio.adapters.GameAdapter;
import com.example.gustavioandroidstudio.api.ApiClient;
import com.example.gustavioandroidstudio.api.ApiService;
import com.example.gustavioandroidstudio.api.Game;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PaginaPrincipal extends AppCompatActivity {
    private ApiService apiService;
    private GameAdapter gameAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagina_principal);

        // Configurar el RecyclerView
        RecyclerView juegosRecyclerView = findViewById(R.id.popularGamesRecycler);
        juegosRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        // Inicializar Retrofit
        Retrofit retrofit = ApiClient.getClient();
        apiService = retrofit.create(ApiService.class);

        // Llamada a la API
        apiService.getVideojuegos().enqueue(new Callback<Game>() {
            @Override
            public void onResponse(Call<Game> call, Response<Game> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Game.Juegos> juegosList = response.body().getJuegos(); // Obtener la lista de juegos

                    for (Game.Juegos juego : juegosList) {
                        Log.d("API_RESPONSE", "ID: " + juego.getId() +
                                ", Nombre: " + juego.getName() +
                                ", Descripción: " + juego.getDescription() +
                                ", Desarrollador: " + juego.getDesarrollador() +
                                ", Género: " + juego.getGenero() +
                                ", Fecha de lanzamiento: " + juego.getReleaseDate() +
                                ", Imagen: " + juego.getImageUrl());
                    }

                    // Configurar el adaptador con la lista de juegos
                    gameAdapter = new GameAdapter(PaginaPrincipal.this, juegosList, game -> {
                        // Acción al hacer clic en un juego (opcional)
                    });
                    juegosRecyclerView.setAdapter(gameAdapter);
                }
            }

            @Override
            public void onFailure(Call<Game> call, Throwable t) {
                Log.e("API_ERROR", "Error al obtener datos", t);
            }
        });

        // Inicializa el BottomNavigationView
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_home) {
                return true;
            } else if (itemId == R.id.nav_search) {
                startActivity(new Intent(PaginaPrincipal.this, BuscarJuegosActivity.class));
                return true;
            } else if (itemId == R.id.nav_profile) {
                startActivity(new Intent(PaginaPrincipal.this, PaginaUsuario.class));
                return true;
            }
            return false;
        });
    }
}
