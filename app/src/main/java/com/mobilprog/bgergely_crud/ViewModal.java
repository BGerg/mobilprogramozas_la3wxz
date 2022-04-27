package com.mobilprog.bgergely_crud;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ViewModal extends AndroidViewModel {
    private MovieRepository repository;
    private LiveData<List<MovieModal>> allMovies;

    public ViewModal(@NonNull Application application) {
        super(application);
        repository = new MovieRepository(application);
        allMovies = repository.getAllMovies();
    }

    public void insert(MovieModal model) {
        repository.insert(model);
    }

    public void update(MovieModal model) {
        repository.update(model);
    }


    public void delete(MovieModal model) {
        repository.delete(model);
    }

    public void deleteAllMovies() {
        repository.deleteAllMovies();
    }

    public LiveData<List<MovieModal>> getAllMovies() {
        return allMovies;
    }
}
