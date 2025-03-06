package com.example.gustavioandroidstudio;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gustavioandroidstudio.api.ApiClient;
import com.example.gustavioandroidstudio.api.ApiService;
import com.example.gustavioandroidstudio.api.Game;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.ArrayList;
import java.util.List;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BuscarJuegosActivity extends AppCompatActivity {
    private PopularGamesAdapter popularGamesAdapter;
    private List<Game> juegos;
    private ApiService apiService;
    private RecyclerView juegosRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_juegos);

        EditText edtBuscar = findViewById(R.id.edtBuscar);
        juegosRecyclerView = findViewById(R.id.recyclerBuscar); // ✅ ID corregido

        // Verificación de RecyclerView
        if (juegosRecyclerView == null) {
            Log.e("ERROR", "RecyclerView no encontrado en activity_buscar_juegos.xml");
            return; // Evita crash si no se encuentra el RecyclerView
        }

        // Usamos GridLayoutManager con 3 columnas
        juegosRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        juegos = new ArrayList<>();
        apiService = ApiClient.getClient().create(ApiService.class);

        // Petición API
        String query = "fields name,summary,cover.url; limit 500;";
        RequestBody requestBody = RequestBody.create(MediaType.parse("text/plain"), query);

        apiService.getVideojuegos(requestBody).enqueue(new Callback<List<Game>>() {
            @Override
            public void onResponse(Call<List<Game>> call, Response<List<Game>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    juegos.clear();
                    juegos.addAll(response.body());

                    // Si hay juegos disponibles
                    if (!juegos.isEmpty()) {
                        popularGamesAdapter = new PopularGamesAdapter(BuscarJuegosActivity.this, juegos, game -> {
                            Intent intent = new Intent(BuscarJuegosActivity.this, GamesActivity.class);
                            intent.putExtra("game", game);
                            startActivity(intent);
                        });
                        juegosRecyclerView.setAdapter(popularGamesAdapter);
                    } else {
                        Log.e("API_RESPONSE", "La API no devolvió juegos.");
                        // Mostrar un mensaje al usuario (podrías usar un Toast o algo similar)
                    }
                } else {
                    Log.e("API_ERROR", "Error en la respuesta: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<List<Game>> call, Throwable t) {
                Log.e("API_ERROR", "Error al obtener datos", t);
                // Mostrar un mensaje de error al usuario si la solicitud falla
            }
        });

        // Configuración del BottomNavigationView
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_home) {
                startActivity(new Intent(BuscarJuegosActivity.this, PaginaPrincipal.class));
                finish();
                return true;
            } else if (itemId == R.id.nav_search) {
                return true;
            } else if (itemId == R.id.nav_profile) {
                startActivity(new Intent(BuscarJuegosActivity.this, PaginaUsuario.class));
                finish();
                return true;
            }
            return false;
        });

        // Filtrado en tiempo real
        edtBuscar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (popularGamesAdapter != null) {
                    popularGamesAdapter.filtrar(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }
}
