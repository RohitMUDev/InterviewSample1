package mobile.clock.world.interviewsample.Model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import androidx.paging.PagedListAdapter;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import mobile.clock.world.interviewsample.DataSource.DataSourceFactory;
import mobile.clock.world.interviewsample.DataSource.MovieDataSource;
import mobile.clock.world.interviewsample.Repository.MovieRepository;

public class MoviesViewModel extends AndroidViewModel {

    MovieRepository repository;
    DataSourceFactory dataSourceFactory;
    LiveData<MovieDataSource> movieDataSourceLiveData;
    private LiveData<PagedList<Result>> moviesPagedList;
    private Executor executor;
    public MoviesViewModel(@NonNull Application application) {
        super(application);

        dataSourceFactory = new DataSourceFactory();
        movieDataSourceLiveData = dataSourceFactory.getMutableLiveData();
        repository = new MovieRepository(application);

        PagedList.Config config = (new PagedList.Config.Builder())
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(10)
                .setPageSize(4)
                .build();


        executor= Executors.newFixedThreadPool(5);


        moviesPagedList = (new LivePagedListBuilder<Integer, Result>(dataSourceFactory, config)).setFetchExecutor(executor)
        .build();

    }




    public LiveData<List<Result>> getDataFromDB(){

        return  repository.getDBData();
    }

    public LiveData<List<Result>> getResults(){

        return  repository.getResults();
    }

    public LiveData<PagedList<Result>> getMoviesPagedList() {
        return moviesPagedList;
    }
}
