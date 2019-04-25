package com.rahuldshetty.promusicplayer.models;

import android.graphics.Bitmap;

public class Song {

    private String title,path,artist,album,genre;
    private Bitmap cover;

    public Song(){

    }

    public Song(String title, String path, String artist, String album, String genre, Bitmap cover) {
        this.title = title;
        this.path = path;
        this.artist = artist;
        this.album = album;
        this.genre = genre;
        this.cover = cover;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Bitmap getCover() {
        return cover;
    }

    public void setCover(Bitmap cover) {
        this.cover = cover;
    }
}
