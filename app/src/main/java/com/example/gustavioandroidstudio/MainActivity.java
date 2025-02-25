package com.example.gustavioandroidstudio;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Llamada a la API antes de redirigir (opcional)
        obtenerUsuarios();

        // Redirigir al login al inicio
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

        // Finaliza MainActivity para que no se pueda volver atrás
        finish();
    }

    private void obtenerUsuarios() {
        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        apiService.getUsuarios().enqueue(new Callback<List<Game>>() {
            @Override
            public void onResponse(Call<List<Game>> call, Response<List<Game>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Game> usuarios = response.body();
                    for (Game usuario : usuarios) {
                        Log.d("API_RESPONSE", "ID: " + usuario.getId() +
                                ", Nombre: " + usuario.getName() +
                                ", Descripcion: " + usuario.getDescription() +
                                ", Desarrollador: " + usuario.getDesarrollador() +
                                ", Genero: " + usuario.getGenero() +
                                ", Fecha de lanzamieneto: " + usuario.getReleaseDate() +
                                        ", " + usuario.getImageUrl());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Game>> call, Throwable t) {
                Log.e("API_ERROR", "Error al obtener datos", t);
            }
        });
    }
}
