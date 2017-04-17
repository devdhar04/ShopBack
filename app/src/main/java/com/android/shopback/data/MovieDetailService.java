package com.android.shopback.data;

/**
 * Created by dev on 16/04/17.
 */
import com.android.shopback.model.Movie;

import retrofit2.http.GET;
import retrofit2.http.Url;
import rx.Observable;

public interface MovieDetailService {
    @GET Observable<Movie> fetchMovie(@Url String url);
}
