package com.android.shopback.view;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;


import com.android.shopback.R;
import com.android.shopback.databinding.MovieListItemBinding;
import com.android.shopback.model.Movie;
import com.android.shopback.viewmodel.ItemMovieViewModel;


import java.util.Collections;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieAdapterViewHolder> {

  private List<Movie> movieList;

  public MovieAdapter() {
    this.movieList = Collections.emptyList();
  }

  @Override public MovieAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    MovieListItemBinding itemMovieBinding =
        DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.movie_list_item,
            parent, false);
    return new MovieAdapterViewHolder(itemMovieBinding);
  }

  @Override public void onBindViewHolder(MovieAdapterViewHolder holder, int position) {
    holder.bindMovie(movieList.get(position));
  }

  @Override public int getItemCount() {
    return movieList.size();
  }

  public void setMovieList(List<Movie> movieList) {
    this.movieList = movieList;
    notifyDataSetChanged();
  }

  public static class MovieAdapterViewHolder extends RecyclerView.ViewHolder {
    MovieListItemBinding mItemMovieBinding;

    public MovieAdapterViewHolder(MovieListItemBinding itemMovieBinding) {
      super(itemMovieBinding.itemMovie);
      this.mItemMovieBinding = itemMovieBinding;
    }

    void bindMovie(Movie movie) {
      if (mItemMovieBinding.getMovieViewModel() == null) {
        mItemMovieBinding.setMovieViewModel(
            new ItemMovieViewModel(movie, itemView.getContext()));
      } else {
        mItemMovieBinding.getMovieViewModel().setMovie(movie);
      }
    }
  }
}