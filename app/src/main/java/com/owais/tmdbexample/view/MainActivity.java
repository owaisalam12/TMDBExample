package com.owais.tmdbexample.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.res.Configuration;
import android.os.Bundle;

import com.owais.tmdbexample.R;
import com.owais.tmdbexample.adapter.MovieAdapter;
import com.owais.tmdbexample.databinding.ActivityMainBinding;
import com.owais.tmdbexample.model.Movie;
import com.owais.tmdbexample.model.MovieDBResponse;
import com.owais.tmdbexample.service.RetrofitInstance;
import com.owais.tmdbexample.viewmodel.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Movie> moviesList;
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    MainActivityViewModel mainActivityViewModel;
    ActivityMainBinding activityMainBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activityMainBinding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        mainActivityViewModel= ViewModelProviders.of(this).get(MainActivityViewModel.class);

        getPopularMovies();

       // swipeRefreshLayout=findViewById(R.id.swiperefresh);
        swipeRefreshLayout=activityMainBinding.swiperefresh;
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                moviesList.clear();
                movieAdapter.notifyDataSetChanged();
                getPopularMovies();

            }
        });




    }
    private void getPopularMovies(){
        mainActivityViewModel.getAllMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> moviesFromLiveData) {
                moviesList=(ArrayList<Movie>) moviesFromLiveData;
                showOnRecyclerView();
            }
        });
    }

    private void showOnRecyclerView(){
        //recyclerView=findViewById(R.id.rvMovies);
        recyclerView=activityMainBinding.rvMovies;
        movieAdapter=new MovieAdapter(this,moviesList);
        if(this.getResources().getConfiguration().orientation== Configuration.ORIENTATION_PORTRAIT){
            recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        }else{
            recyclerView.setLayoutManager(new GridLayoutManager(this,4));

        }
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(movieAdapter);
        movieAdapter.notifyDataSetChanged();
    }
}
