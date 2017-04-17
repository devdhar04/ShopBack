
package com.android.shopback.viewmodel;
/**
 * Created by dev on 16/04/17.
 */

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;
import android.view.View;

import com.android.shopback.MovieApplication;
import com.android.shopback.R;
import com.android.shopback.data.MovieListResponse;
import com.android.shopback.data.MovieListService;
import com.android.shopback.model.Movie;


import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.android.shopback.Constants.MOVIE_LIST_URL;

public class MovieViewModel extends Observable {

    public ObservableInt movieProgress;
    public ObservableInt movieRecycler;
    public ObservableInt movieLabel;
    public ObservableField<String> messageLabel;

    private List<Movie> movieList;
    private Context context;
    private Subscription subscription;

    public MovieViewModel(@NonNull Context context) {

        this.context = context;
        this.movieList = new ArrayList<>();
        movieProgress = new ObservableInt(View.GONE);
        movieRecycler = new ObservableInt(View.GONE);
        movieLabel = new ObservableInt(View.VISIBLE);

        initializeViews();
        fetchMovieList(2);
    }


    public void initializeViews() {
        movieLabel.set(View.GONE);
        movieRecycler.set(View.GONE);
        movieProgress.set(View.VISIBLE);

    }

    public void fetchMovieList(int pageCount) {

        final String URL = MOVIE_LIST_URL+pageCount;
        unSubscribeFromObservable();
        MovieApplication movieApplication = MovieApplication.create(context);
        MovieListService movieListService = movieApplication.getMovieListService();
        subscription = movieListService.fetchMovie(URL)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(movieApplication.subscribeScheduler())
                .subscribe(new Action1<MovieListResponse>() {
                    @Override
                    public void call(MovieListResponse movieResponse) {
                        movieProgress.set(View.GONE);
                        movieLabel.set(View.GONE);
                        movieRecycler.set(View.VISIBLE);
                        changeMovieDataSet(movieResponse.getMovieList());
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                        messageLabel.set(context.getString(R.string.error_loading_movies));
                        movieProgress.set(View.GONE);
                        movieLabel.set(View.VISIBLE);
                        movieRecycler.set(View.GONE);
                    }
                });
    }

    private void changeMovieDataSet(List<Movie> movies) {
        movieList.addAll(movies);
        setChanged();
        notifyObservers();
    }

    public List<Movie> getMovieList() {
        return movieList;
    }

    private void unSubscribeFromObservable() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    public void reset() {
        unSubscribeFromObservable();
        subscription = null;
        context = null;
    }
}
