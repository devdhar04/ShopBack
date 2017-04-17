package com.android.shopback.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import com.android.shopback.Constants;
import com.android.shopback.view.MovieDetailActivity;
import com.android.shopback.model.Movie;
import com.bumptech.glide.Glide;

/**
 * Created by dev on 16/04/17.
 */

public class ItemMovieViewModel extends BaseObservable {

    private Movie movie;
    private Context context;

    public ItemMovieViewModel(Movie movie, Context context) {
        this.movie = movie;
        this.context = context;
    }

    public String getFullName() {

        return movie.title;
    }

    public String getReleaseDate() {
        return movie.releaseDate;
    }

    public String getPopularity() {
        return movie.getPopularity();
    }

    public String getPictureProfile() {
        return Constants.MOVIE_LIST_IMAGE_URL+movie.imagePath;
    }

    @BindingAdapter("imageUrl") public static void setImageUrl(ImageView imageView, String url) {
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }

    public void onItemClick(View view) {
        context.startActivity(MovieDetailActivity.launchDetail(view.getContext(), movie));
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
        notifyChange();
    }
}
