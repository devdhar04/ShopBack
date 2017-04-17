package com.android.shopback;

/**
 * Created by dev on 17/04/17.
 */

public class Constants {

    public static final String API_KEY ="?api_key=b57c604715a8e06d87277ff9bd889fde";
    public static final String BASE_URL ="http://api.themoviedb.org/3";

    public static final String MOVIE_LIST_URL =BASE_URL+"/discover/movie"+API_KEY+"&primary_release_date.lte=2017-01-01&sort_by=release_%20date.desc&page=";
    public static final String MOVIE_DETAIL_URL =BASE_URL+"/movie/";
    public static final String MOVIE_DETAIL_IMAGE_URL= "https://image.tmdb.org/t/p/w300";

    public static final String MOVIE_LIST_IMAGE_URL= "https://image.tmdb.org/t/p/w154";

}
