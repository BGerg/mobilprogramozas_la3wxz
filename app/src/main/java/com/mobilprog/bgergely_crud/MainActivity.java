package com.mobilprog.bgergely_crud;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView moviesRV;
    private static final int ADD_MOVIE_REQUEST = 1;
    private static final int EDIT_MOVIE_REQUEST = 2;
    private ViewModal viewmodal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        moviesRV = findViewById(R.id.idRVMovies);
        FloatingActionButton fab = findViewById(R.id.idFABAdd);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewMovieActivity.class);
                startActivityForResult(intent, ADD_MOVIE_REQUEST);
            }
        });

        moviesRV.setLayoutManager(new LinearLayoutManager(this));
        moviesRV.setHasFixedSize(true);
        final MovieRVAdapter adapter = new MovieRVAdapter();
        moviesRV.setAdapter(adapter);
        viewmodal = ViewModelProviders.of(this).get(ViewModal.class);
        viewmodal.getAllMovies().observe(this, new Observer<List<MovieModal>>() {
            @Override
            public void onChanged(List<MovieModal> models) {
                adapter.submitList(models);
            }
        });
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                viewmodal.delete(adapter.getMovieAt(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this, "Movie deleted", Toast.LENGTH_SHORT).show();
            }
        }).
                        attachToRecyclerView(moviesRV);
        adapter.setOnItemClickListener(new MovieRVAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(MovieModal model) {
                Intent intent = new Intent(MainActivity.this, NewMovieActivity.class);
                intent.putExtra(NewMovieActivity.EXTRA_ID, model.getId());
                intent.putExtra(NewMovieActivity.EXTRA_MOVIE_NAME, model.getMovieName());
                intent.putExtra(NewMovieActivity.EXTRA_DESCRIPTION, model.getMovieDescription());
                intent.putExtra(NewMovieActivity.EXTRA_RECOMMENDATION, model.getMovieDuration());
                startActivityForResult(intent, EDIT_MOVIE_REQUEST);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_MOVIE_REQUEST && resultCode == RESULT_OK) {
            String movieName = data.getStringExtra(NewMovieActivity.EXTRA_MOVIE_NAME);
            String movieDescription = data.getStringExtra(NewMovieActivity.EXTRA_DESCRIPTION);
            String movieRecommendation = data.getStringExtra(NewMovieActivity.EXTRA_RECOMMENDATION);
            MovieModal model = new MovieModal(movieName, movieDescription, movieRecommendation);
            viewmodal.insert(model);
            Toast.makeText(this, "Film mentve", Toast.LENGTH_SHORT).show();
        } else if (requestCode == EDIT_MOVIE_REQUEST && resultCode == RESULT_OK) {
            int id = data.getIntExtra(NewMovieActivity.EXTRA_ID, -1);
            if (id == -1) {
                Toast.makeText(this, "A film adatai nem frissithetőek", Toast.LENGTH_SHORT).show();
                return;
            }
            String movieName = data.getStringExtra(NewMovieActivity.EXTRA_MOVIE_NAME);
            String movieDesc = data.getStringExtra(NewMovieActivity.EXTRA_DESCRIPTION);
            String movieRecommendation = data.getStringExtra(NewMovieActivity.EXTRA_RECOMMENDATION);
            MovieModal model = new MovieModal(movieName, movieDesc, movieRecommendation);
            model.setId(id);
            viewmodal.update(model);
            Toast.makeText(this, "Film adatai frissítve", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "A film nem került mentésre", Toast.LENGTH_SHORT).show();
        }

    }
}