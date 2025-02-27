package com.example.gustavioandroidstudio;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gustavioandroidstudio.adapters.GameAdapter;

import com.example.gustavioandroidstudio.api.ApiClient;
import com.example.gustavioandroidstudio.api.ApiService;
import com.example.gustavioandroidstudio.api.Game;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class BuscarJuegosActivity extends AppCompatActivity {
    private PopularGamesAdapter popularGamesAdapter;
    private List<Game> juegos; // Lista original de juegos
    private ApiService apiService;
    private GameAdapter gameAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_juegos);

        EditText edtBuscar = findViewById(R.id.edtBuscar);

        // Configurar el RecyclerView
        RecyclerView juegosRecyclerView = findViewById(R.id.popularGamesRecycler);
        juegosRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        // Inicializa la lista de juegos
        juegos = new ArrayList<>();

        // Crea el adaptador para RecyclerView
        popularGamesAdapter = new PopularGamesAdapter(this, juegos, game -> {
            // Acción al hacer clic en un juego


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
                    gameAdapter = new GameAdapter(BuscarJuegosActivity.this, juegosList, game -> {
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

        });

        // Inicializa el BottomNavigationView
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.nav_home) {
                    Intent intent = new Intent(BuscarJuegosActivity.this, PaginaPrincipal.class);
                    finish();
                    startActivity(intent);
                    return true;
                } else if (itemId == R.id.nav_search) {
                    Intent intent = new Intent(BuscarJuegosActivity.this, BuscarJuegosActivity.class);
                    startActivity(intent);
                    return true;
                } else if (itemId == R.id.nav_profile) {
                    Intent intent = new Intent(BuscarJuegosActivity.this, PaginaUsuario.class);
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });

        edtBuscar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                popularGamesAdapter.filtrar(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }
}