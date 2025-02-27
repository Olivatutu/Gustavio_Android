package com.example.gustavioandroidstudio;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class PaginaPrincipal extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private List<Game> popularGames; // Asegúrate de inicializar esta lista

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagina_principal);

        // Inicializa el Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Obtener referencias a los elementos
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        ImageView menuIcon = findViewById(R.id.menuIcon);
        ImageView profileIcon = findViewById(R.id.profileIcon);

        // Configurar el clic del icono del menú
        menuIcon.setOnClickListener(v -> drawerLayout.openDrawer(navigationView));

      /*  // Configurar el clic del icono del perfil
        profileIcon.setOnClickListener(this::goToUserPage);
*/
        // Inicializa el BottomNavigationView
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.nav_home) {
                    // Inicia la actividad de la página principal
                    Intent intent = new Intent(PaginaPrincipal.this, PaginaPrincipal.class);
                    finish();
                    startActivity(intent);
                    return true;
                } else if (itemId == R.id.nav_search) {
                    Intent intent = new Intent(PaginaPrincipal.this, BuscarJuegosActivity.class);
                    startActivity(intent);
                    return true;
                } else if (itemId == R.id.nav_profile) {
                    Intent intent = new Intent(PaginaPrincipal.this, PaginaUsuario.class);
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });

        // Configura el RecyclerView
        RecyclerView popularGamesRecycler = findViewById(R.id.popularGamesRecycler);
        popularGamesRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        // Asegúrate de inicializar popularGames con una lista de juegos
        popularGames = new ArrayList<>(); // Inicializa la lista de juegos
    }
}