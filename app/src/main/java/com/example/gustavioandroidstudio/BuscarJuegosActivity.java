package com.example.gustavioandroidstudio;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class BuscarJuegosActivity extends AppCompatActivity {
    private PopularGamesAdapter popularGamesAdapter;
    private List<Game> juegos; // Lista original de juegos

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_juegos);

        EditText edtBuscar = findViewById(R.id.edtBuscar);
        RecyclerView recyclerView = findViewById(R.id.recyclerBuscar);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        /*// Cargar lista de juegos
        juegos = new ArrayList<>();
        juegos.add(new Game("GTA V", R.drawable.gta_v));
        juegos.add(new Game("Cyberpunk 2077", R.drawable.cyberpunk));
        juegos.add(new Game("Red Dead Redemption 2", R.drawable.red_dead));
        juegos.add(new Game("The Witcher 3", R.drawable.witcher_3));

        popularGamesAdapter = new PopularGamesAdapter(this, juegos, game -> {
            // Acción al hacer clic en un juego
        });
        recyclerView.setAdapter(popularGamesAdapter);
*/
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
