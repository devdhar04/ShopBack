package com.android.shopback;

import android.app.Application;
import android.content.Context;

import com.android.shopback.data.MovieDetailService;
import com.android.shopback.data.MovieListService;
import com.android.shopback.data.MoviesFactory;

import rx.Scheduler;
import rx.schedulers.Schedulers;

/**
 * Created by dev on 16/04/17.
 */

public class MovieApplication extends Application {

    private MovieListService movieListService;
    private MovieDetailService movieDetailService;
    private Scheduler scheduler;

    private static MovieApplication get(Context context) {
        return (MovieApplication) context.getApplicationContext();
    }

    public static MovieApplication create(Context context) {
        return MovieApplication.get(context);
    }

    public MovieListService getMovieListService() {
        if (movieListService == null) movieListService = MoviesFactory.create();

        return movieListService;
    }

    public MovieDetailService getMovieDetailService() {
        if (movieDetailService == null) movieDetailService = MoviesFactory.createDetailService();

        return movieDetailService;
    }
    public Scheduler subscribeScheduler() {
        if (scheduler == null) scheduler = Schedulers.io();

        return scheduler;
    }

    public void setMovieListService(MovieListService movieListService) {
        this.movieListService = movieListService;
    }

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }
}