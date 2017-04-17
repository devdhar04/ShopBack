package com.android.shopback.view;

/**
 * Created by dev on 16/04/17.
 */

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.android.shopback.R;
import com.android.shopback.databinding.MoviesActivityBinding;
import com.android.shopback.view.MovieAdapter;
import com.android.shopback.viewmodel.MovieViewModel;

import java.util.Observable;
import java.util.Observer;

public class MoviesListActivity extends AppCompatActivity implements Observer {

    private MoviesActivityBinding movieActivityBinding;
    private MovieViewModel movieViewModel;
    private boolean loading = true;
    private int pastVisiblesItems, visibleItemCount, totalItemCount;
    private int pageCount = 2;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initDataBinding();

        setupListMovieView(movieActivityBinding.listMovie);
        setupObserver(movieViewModel);
    }

    private void initDataBinding() {
        movieActivityBinding = DataBindingUtil.setContentView(this, R.layout.movies_activity);
        movieViewModel = new MovieViewModel(this);
        movieActivityBinding.setMainViewModel(movieViewModel);
    }


    private void setupListMovieView(RecyclerView mRecyclerView) {
        MovieAdapter adapter = new MovieAdapter();
        mRecyclerView.setAdapter(adapter);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        setScrollListener(mRecyclerView,mLayoutManager);
    }

    private void setScrollListener(RecyclerView mRecyclerView, final LinearLayoutManager mLayoutManager)
    {
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener()
        {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy)
            {
                if(dy > 0)
                {
                    visibleItemCount = mLayoutManager.getChildCount();
                    totalItemCount = mLayoutManager.getItemCount();
                    pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition();

                    if (loading)
                    {
                        if ( (visibleItemCount + pastVisiblesItems) >= totalItemCount)
                        {
                            loading = false;

                            pageCount =  pageCount+1;
                            movieViewModel.fetchMovieList(pageCount);
                        }
                    }
                }
            }
        });
    }


    public void setupObserver(Observable observable) {
        observable.addObserver(this);
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        movieViewModel.reset();
    }



    @Override public void update(Observable observable, Object data) {
        if (observable instanceof MovieViewModel) {
            MovieAdapter movieAdapter = (MovieAdapter) movieActivityBinding.listMovie.getAdapter();
            MovieViewModel movieViewModel = (MovieViewModel) observable;
            movieAdapter.setMovieList(movieViewModel.getMovieList());
            loading =  true;
        }
    }
}
