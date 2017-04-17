
package com.android.shopback.data;

import com.android.shopback.model.Movie;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieListResponse {

  @SerializedName("results") private List<Movie> movieList;

  public List<Movie> getMovieList() {
    return movieList;
  }



  public void setMovieList(List<Movie> mMovieList) {
    this.movieList = mMovieList;
  }
}
