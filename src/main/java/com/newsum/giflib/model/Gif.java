package com.newsum.giflib.model;

import java.time.LocalDate;

public class Gif
{
    private String name;
    private LocalDate dateUploaded;
    private String username;
    private boolean favorite;

    public Gif(String name, LocalDate dateUploaded, String username, boolean favorite) {
        this.name = name;
        this.dateUploaded = dateUploaded;
        this.username = username;
        this.favorite = favorite;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDateUploaded(LocalDate dateUploaded) {
        this.dateUploaded = dateUploaded;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public String getUsername() {
        return username;
    }

    public LocalDate getDateUploaded() {
        return dateUploaded;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public String getName() {
        return name;
    }
}
