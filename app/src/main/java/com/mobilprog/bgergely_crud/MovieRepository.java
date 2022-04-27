package com.mobilprog.bgergely_crud;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class MovieRepository {
    private Dao dao;
    private LiveData<List<MovieModal>> allMovies;

    public MovieRepository(Application application) {
        MovieDatabase database = MovieDatabase.getInstance(application);
        dao = database.Dao();
        allMovies = dao.getAllMovies();
    }

    public void insert(MovieModal model) {
        new InsertMovieAsyncTask(dao).execute(model);
    }

    public void update(MovieModal model) {
        new UpdateMovieAsyncTask(dao).execute(model);
    }

    public void delete(MovieModal model) {
        new DeleteMovieAsyncTask(dao).execute(model);
    }

    public void deleteAllMovies() {
        new DeleteAllMoviesAsyncTask(dao).execute();
    }

    public LiveData<List<MovieModal>> getAllMovies() {
        return allMovies;
    }

    private static class InsertMovieAsyncTask extends AsyncTask<MovieModal, Void, Void> {
        private Dao dao;

        private InsertMovieAsyncTask(Dao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(MovieModal... model) {
            dao.insert(model[0]);
            return null;
        }
    }

    private static class UpdateMovieAsyncTask extends AsyncTask<MovieModal, Void, Void> {
        private Dao dao;

        private UpdateMovieAsyncTask(Dao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(MovieModal... models) {
            dao.update(models[0]);
            return null;
        }
    }

    private static class DeleteMovieAsyncTask extends AsyncTask<MovieModal, Void, Void> {
        private Dao dao;

        private DeleteMovieAsyncTask(Dao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(MovieModal... models) {
            dao.delete(models[0]);
            return null;
        }
    }

    private static class DeleteAllMoviesAsyncTask extends AsyncTask<Void, Void, Void> {
        private Dao dao;

        private DeleteAllMoviesAsyncTask(Dao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dao.deleteAllMovies();
            return null;
        }
    }
}
