
package com.android.shopback.data;

import retrofit2.Retrofit;

import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
public class MoviesFactory {

  private static final String API_KEY ="b57c604715a8e06d87277ff9bd889fde";
  private final static String BASE_URL = "http://api.themoviedb.org/3/";//"http://api.themoviedb.org/3/discover/movie?api_key="+API_KEY+"&primary_release_date.lte=2016-12-31&sort_by=release_%20date.desc&page=1/";


  public static MovieListService create() {
    Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .build();
    return retrofit.create(MovieListService.class);
  }

  public static MovieDetailService createDetailService() {
    Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build();
    return retrofit.create(MovieDetailService.class);
  }
}
