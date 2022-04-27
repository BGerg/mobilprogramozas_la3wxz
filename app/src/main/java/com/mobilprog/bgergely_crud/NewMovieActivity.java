package com.mobilprog.bgergely_crud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewMovieActivity extends AppCompatActivity {
    
    private EditText movieNameEdt, movieDescEdt, movieRecommendationEdt;
    private Button movieBtn;

    public static final String EXTRA_ID =
            "com.mobilprog.bgergely_crud.EXTRA_ID";
    public static final String EXTRA_MOVIE_NAME =
            "com.mobilprog.bgergely_crud.EXTRA_MOVIE_NAME";
    public static final String EXTRA_DESCRIPTION =
            "com.mobilprog.bgergely_crud.EXTRA_MOVIE_DESCRIPTION";
    public static final String EXTRA_RECOMMENDATION =
            "com.mobilprog.bgergely_crud.EXTRA_MOVIE_RECOMMENDATION";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_movie);
        movieNameEdt = findViewById(R.id.idEdtMovieName);
        movieDescEdt = findViewById(R.id.idEdtMovieDescription);
        movieRecommendationEdt = findViewById(R.id.idEdtMovieRecommendation);
        movieBtn = findViewById(R.id.idBtnSaveMovie);
        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            movieNameEdt.setText(intent.getStringExtra(EXTRA_MOVIE_NAME));
            movieDescEdt.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
            movieRecommendationEdt.setText(intent.getStringExtra(EXTRA_RECOMMENDATION));
        }
        movieBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String movieName = movieNameEdt.getText().toString();
                String movieDesc = movieDescEdt.getText().toString();
                String movieRecommendation = movieRecommendationEdt.getText().toString();
                if (movieName.isEmpty() || movieDesc.isEmpty() || movieRecommendation.isEmpty()) {
                    Toast.makeText(NewMovieActivity.this, "Add meg a film adatait.", Toast.LENGTH_SHORT).show();
                    return;
                }
                saveMovie(movieName, movieDesc, movieRecommendation);
            }
        });

    }

    private void saveMovie(String movieName, String movieDescription, String movieRecommendation) {
        Intent data = new Intent();
        data.putExtra(EXTRA_MOVIE_NAME, movieName);
        data.putExtra(EXTRA_DESCRIPTION, movieDescription);
        data.putExtra(EXTRA_RECOMMENDATION, movieRecommendation);
        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            data.putExtra(EXTRA_ID, id);
        }
        setResult(RESULT_OK, data);
        Toast.makeText(this, "A film adatai mentésre kerültem a room adatbázisban", Toast.LENGTH_SHORT).show();
    }

}