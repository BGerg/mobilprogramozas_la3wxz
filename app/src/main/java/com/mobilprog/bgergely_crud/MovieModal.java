package com.mobilprog.bgergely_crud;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "movie_table")
public class MovieModal {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String movieName;
    private String movieDescription;
    private String movieDuration;
    
    public MovieModal(String movieName, String movieDescription, String movieDuration) {
        this.movieName = movieName;
        this.movieDescription = movieDescription;
        this.movieDuration = movieDuration;
    }
    
    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieDescription() {
        return movieDescription;
    }

    public void setMovieDescription(String movieDescription) {
        this.movieDescription = movieDescription;
    }

    public String getMovieDuration() {
        return movieDuration;
    }

    public void setMovieDuration(String movieDuration) {
        this.movieDuration = movieDuration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
