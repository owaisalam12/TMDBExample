package com.owais.tmdbexample.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.owais.tmdbexample.databinding.MovieListItemBinding;
import com.owais.tmdbexample.view.MovieActivity;
import com.owais.tmdbexample.R;
import com.owais.tmdbexample.model.Movie;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyHolder> {

    private Context context;
    private ArrayList<Movie> moviesList;

    public MovieAdapter(Context context, ArrayList<Movie> moviesList) {
        this.context = context;
        this.moviesList = moviesList;
    }

    @NonNull
    @Override
    public MovieAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       MovieListItemBinding movieListItemBinding= DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.movie_list_item,parent,false);
        return new MovieAdapter.MyHolder(movieListItemBinding);

        // View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item, parent, false);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.MyHolder holder, int position) {
        Movie movie=moviesList.get(position);
//        String imagePath="https://image.tmdb.org/t/p/w500"+movie.getPosterPath();
//
//        movie.setPosterPath(imagePath);
        holder.movieListItemBinding.setMovie(movie);
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        private MovieListItemBinding movieListItemBinding;
        public MyHolder(@NonNull MovieListItemBinding movieListItemBinding) {
            super(movieListItemBinding.getRoot());
            this.movieListItemBinding=movieListItemBinding;

            movieListItemBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position=getAdapterPosition();
                    if(position!=RecyclerView.NO_POSITION){
                        Movie selectedMovie=moviesList.get(position);
                        Intent intent=new Intent(context, MovieActivity.class);
                        intent.putExtra("movie",selectedMovie);
                        context.startActivity(intent);
                    }
                }
            });
        }
    }
}
