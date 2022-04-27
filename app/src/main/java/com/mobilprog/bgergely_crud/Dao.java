package com.mobilprog.bgergely_crud;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@androidx.room.Dao
public interface Dao {

    @Insert
    void insert(MovieModal model);
    
    @Update
    void update(MovieModal model);

    @Delete
    void delete(MovieModal model);
    
    @Query("DELETE FROM movie_table")
    void deleteAllMovies();
    
    @Query("SELECT * FROM movie_table ORDER BY movieName ASC")
    LiveData<List<MovieModal>> getAllMovies();

}
