package com.android.shopback.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;

import com.android.shopback.Constants;
import com.android.shopback.MovieApplication;
import com.android.shopback.data.MovieDetailService;
import com.android.shopback.model.Movie;
import com.android.shopback.view.WebViewActivity;
import com.bumptech.glide.Glide;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by dev on 16/04/17.
 */

public class MovieDetailViewModel extends BaseObservable {


    private Movie movie;
    private Context context;
    private Subscription subscription;

    public ObservableInt movieProgress;

    public MovieDetailViewModel(@NonNull Context context, Movie movie) {
        this.context = context;
        this.movie = movie;

        movieProgress = new ObservableInt(View.VISIBLE);
        fetchMovieList();

    }

    public String getTitle() {

        return movie != null ? movie.title : "";
    }

    public String getOverView() {
        return movie != null ? movie.overview : "";
    }



    public String getGeners() {
        StringBuffer sb = new StringBuffer();
        if (movie.genreList != null) {
            for (int i = 0; i < movie.genreList.size(); i++) {
                sb.append(movie.genreList.get(i).getGenresName());
                if (i < movie.genreList.size() - 1)
                    sb.append(" ,");
            }
        }
        return sb.toString();
    }



    public String getLanguages() {
        StringBuffer sb = new StringBuffer();
        if (movie.languageList != null) {
            for (int i = 0; i < movie.languageList.size(); i++) {
                sb.append(movie.languageList.get(i).getLanguageName());
                if (i < movie.languageList.size() - 1)
                    sb.append(" ,");
            }
        }
        return sb.toString();
    }

    public String getPictureProfile() {
        String imagePath = Constants.MOVIE_DETAIL_IMAGE_URL;
        if (movie != null) {
            imagePath = imagePath + movie.imagePath;
        }
        return imagePath;
    }

    public String getDuration() {
        return movie != null ? movie.duration + " Mins" : "";
    }


    public void onItemClick(View view) {
        Intent intent = new Intent(context, WebViewActivity.class);
        context.startActivity(intent);
    }


    private void fetchMovieList() {
        String id = "";
        if (movie != null && movie.movieId != null) {
            id = movie.movieId;
        }
        String URL = Constants.MOVIE_DETAIL_URL + id + Constants.API_KEY;
        unSubscribeFromObservable();
        MovieApplication movieApplication = MovieApplication.create(context);
        MovieDetailService movieDetailService = movieApplication.getMovieDetailService();
        subscription = movieDetailService.fetchMovie(URL)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(movieApplication.subscribeScheduler())
                .subscribe(new Action1<Movie>() {
                    @Override
                    public void call(Movie movieResponse) {
                        movieProgress.set(View.GONE);

                        changeMovieDataSet(movieResponse);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();

                        movieProgress.set(View.GONE);

                    }
                });
    }

    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext()).load(imageUrl).into(view);
    }

    private void changeMovieDataSet(Movie movieResponse) {

        this.movie = movieResponse;
        notifyChange();

    }

    private void unSubscribeFromObservable() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }


}
