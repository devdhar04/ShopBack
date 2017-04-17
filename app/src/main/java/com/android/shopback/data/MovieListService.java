
package com.android.shopback.data;

/**
 * Created by dev on 16/04/17.
 */

import retrofit2.http.GET;
import retrofit2.http.Url;
import rx.Observable;

public interface MovieListService {
  @GET Observable<MovieListResponse> fetchMovie(@Url String url);
}
