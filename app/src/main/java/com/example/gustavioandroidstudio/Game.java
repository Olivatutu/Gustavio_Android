package com.example.gustavioandroidstudio;

public class Game {
    private int id;
    private String name;
    private String description;
    private String desarrollador;
    private String genero;
    private String releaseDate;
    private String imageUrl;

    // Constructor
    public Game(int id, String name, String description, String releaseDate, String imageUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.desarrollador = desarrollador;
        this.genero = genero;
        this.releaseDate = releaseDate;
        this.imageUrl = imageUrl;
    }

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
        return description;
    }

    public String getGenero() {
        return description;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
}
}
