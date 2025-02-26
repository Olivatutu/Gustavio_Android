package com.example.gustavioandroidstudio.api;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class Game {
    @SerializedName("juegos")
    public List<Juegos> juegos = new ArrayList<>();

    public List<Juegos> getJuegos() {
        return juegos;
    }

    public static class Juegos {
        @SerializedName("id")
        private int id;

        @SerializedName("name")
        private String name;

        @SerializedName("description")
        private String description;

        @SerializedName("desarrollador")
        private String desarrollador;

        @SerializedName("genero")
        private String genero;

        @SerializedName("release_date") // Asegurar que coincide con la API
        private String releaseDate;

        @SerializedName("image_url") // Asegurar que coincide con la API
        private String imageUrl;

        // Constructor vacío necesario para Retrofit
        public Juegos() {}

        // Getters
        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        public String getDesarrollador() {
            return desarrollador;
        }

        public String getGenero() {
            return genero;
        }

        public String getReleaseDate() {
            return releaseDate;
        }

        public String getImageUrl() {
            return imageUrl;
        }
    }
}
