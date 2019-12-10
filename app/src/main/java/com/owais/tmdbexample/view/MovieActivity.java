package com.owais.tmdbexample.view;

import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.owais.tmdbexample.R;
import com.owais.tmdbexample.databinding.ActivityMovieBinding;
import com.owais.tmdbexample.model.Movie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MovieActivity extends AppCompatActivity {

    Movie movie;
    ImageView imageView;
    String image;
    private TextView movieTitle,movieSynopsis,movieRating,movieReleaseDate;
    private ActivityMovieBinding activityMovieBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        activityMovieBinding= DataBindingUtil.setContentView(this,R.layout.activity_movie);
         imageView=findViewById(R.id.ivMovieLarge);

        movieTitle=findViewById(R.id.tvMovieTitle);

        movieSynopsis=findViewById(R.id.tvPlotsynopsis);


        movieRating=findViewById(R.id.tvMovieRating);

        movieReleaseDate=findViewById(R.id.tvReleaseDate);


        Intent intent=getIntent();
        if(intent.hasExtra("movie")){
            movie=getIntent().getParcelableExtra("movie");
            activityMovieBinding.setMovie(movie);

            //Toast.makeText(this, movie.getTitle(), Toast.LENGTH_SHORT).show();
//            image=movie.getPosterPath();
//            String path="https://image.tmdb.org/t/p/w500"+image;
//            Glide.with(this)
//                    .load(path)
//                    .placeholder(R.drawable.loading)
//                    .into(imageView);
           // getSupportActionBar().setTitle(movie.getTitle());
//            movieTitle.setText(movie.getTitle());
//            movieSynopsis.setText(movie.getOverview());
//            movieRating.setText(movie.getVoteAverage().toString());
//            movieReleaseDate.setText(movie.getReleaseDate());

        }
    }

}
