package com.android.shopback.view;

/**
 * Created by dev on 16/04/17.
 */

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.android.shopback.R;
import com.android.shopback.databinding.MovieDetailBinding;
import com.android.shopback.model.Movie;
import com.android.shopback.viewmodel.MovieDetailViewModel;


public class MovieDetailActivity extends AppCompatActivity {

    private static final String EXTRA_MOVIE = "EXTRA_MOVIE";

    private MovieDetailBinding movieDetailActivityBinding;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        movieDetailActivityBinding =
                DataBindingUtil.setContentView(this, R.layout.movie_detail);
        setSupportActionBar(movieDetailActivityBinding.toolbar);
        getExtrasFromIntent();
    }

    public static Intent launchDetail(Context context, Movie movie) {
        Intent intent = new Intent(context, MovieDetailActivity.class);
        intent.putExtra(EXTRA_MOVIE, movie);
        return intent;
    }



    private void getExtrasFromIntent() {
        Movie movie = (Movie) getIntent().getSerializableExtra(EXTRA_MOVIE);
        MovieDetailViewModel movieDetailViewModel = new MovieDetailViewModel(this,movie);
        movieDetailActivityBinding.setMovieDetailViewModel(movieDetailViewModel);
        setTitle(movie.getTitle());
    }
}