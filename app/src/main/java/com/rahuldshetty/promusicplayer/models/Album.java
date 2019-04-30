package com.rahuldshetty.promusicplayer.models;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.ArrayList;

public class Album implements Comparable<Album>, Serializable {

    private ArrayList<Song> songs;
    private String title,artist;
    private Bitmap cover;

    public Album(){
        songs=new ArrayList<>();
    }

    public Album(ArrayList<Song> songs, String title, String artist, Bitmap cover) {
        this.songs = songs;
        this.title = title;
        this.artist = artist;
        this.cover = cover;
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }

    public void setSongs(ArrayList<Song> songs) {
        this.songs = songs;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public Bitmap getCover() {
        return cover;
    }

    public void setCover(Bitmap cover) {
        this.cover = cover;
    }

    @Override
    public int compareTo(Album o) {
        return this.getTitle().compareTo(o.getTitle());
    }
}
