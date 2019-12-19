package mobile.clock.world.interviewsample;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import mobile.clock.world.interviewsample.Model.MoviesViewModel;
import mobile.clock.world.interviewsample.Model.Result;

public class MainActivity extends AppCompatActivity {

    MoviesViewModel moviesViewModel;
    RecyclerView rvRecyclerView;
    MovieAdapter movieAdapter;
    PagedList<Result> results;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvRecyclerView = findViewById(R.id.rvRecyclerView);

        moviesViewModel = ViewModelProviders.of(MainActivity.this).get(MoviesViewModel.class);

       /* moviesViewModel.getDataFromDB().observe((LifecycleOwner) MainActivity.this, new Observer<List<Result>>() {
            @Override
            public void onChanged(List<Result> results) {

                setAdapter(results);
            }
        });*/


        moviesViewModel.getMoviesPagedList().observe(MainActivity.this, new Observer<PagedList<Result>>() {
            @Override
            public void onChanged(PagedList<Result> results1) {
                results = results1;

                setAdapter();
            }
        });
    }

   /* private void setAdapter(List<Result> movies) {

        rvRecyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
        if(movies != null){

            movieAdapter = new MovieAdapter(MainActivity.this, movies);
            rvRecyclerView.setAdapter(movieAdapter);
        }

    }*/

    private void setAdapter() {

        rvRecyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));

            movieAdapter = new MovieAdapter(MainActivity.this);
            movieAdapter.submitList(results);
            rvRecyclerView.setAdapter(movieAdapter);
        }


}
