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

        // Redirigir al login al inicio
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

        // Finaliza MainActivity para que no se pueda volver atrás
        finish();
    }
}