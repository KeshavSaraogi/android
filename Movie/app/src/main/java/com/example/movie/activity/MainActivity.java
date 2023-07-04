package com.example.movie.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;
import android.widget.GridLayout;
import android.widget.Toast;

import com.bumptech.glide.RequestManager;
import com.example.movie.R;
import com.example.movie.adapter.MoviesAdapter;
import com.example.movie.adapter.MoviesLoadStateAdapter;
import com.example.movie.databinding.ActivityMainBinding;
import com.example.movie.util.GridSpace;
import com.example.movie.util.MovieComparator;
import com.example.movie.util.Utilities;
import com.example.movie.viewmodel.MovieViewModel;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    MovieViewModel mainActivityViewModel;
    ActivityMainBinding binding;
    MoviesAdapter moviesAdapter;

    @Inject
    RequestManager requestManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (Utilities.API_KEY == null || Utilities.API_KEY.isEmpty()){
            Toast.makeText(this,"ERROR IN API",Toast.LENGTH_SHORT).show();
        }

        moviesAdapter = new MoviesAdapter(new MovieComparator(), requestManager);
        mainActivityViewModel = new ViewModelProvider(this).get(MovieViewModel.class);
        initiateRecyclerviewAndAdapter();

        mainActivityViewModel.moviePagingDataFlowable.subscribe(moviePagingData -> {
            moviesAdapter.submitData(getLifecycle(), moviePagingData);
        });
    }

    private void initiateRecyclerviewAndAdapter() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        binding.recylcerView.setLayoutManager(gridLayoutManager);
        binding.recylcerView.addItemDecoration(new GridSpace(2,20, true));
        binding.recylcerView.setAdapter(
                moviesAdapter.withLoadStateFooter(
                        new MoviesLoadStateAdapter(view -> {
                            moviesAdapter.retry();
                        })
                )
        );

        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup(){
            @Override
            public int getSpanSize(int position) {
                return moviesAdapter.getItemViewType(position) == MoviesAdapter.LOADING_ITEM ? 1 : 2;
            }
        });
    }
}