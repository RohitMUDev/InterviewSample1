package mobile.clock.world.interviewsample.DataSource;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

public class DataSourceFactory extends DataSource.Factory {

    MovieDataSource movieDataSource;

    private MutableLiveData<MovieDataSource> mutableLiveData = new MutableLiveData<>();


    @Override
    public DataSource create() {

        movieDataSource = new MovieDataSource();
        mutableLiveData.postValue(movieDataSource);
        return movieDataSource;
    }

    public MutableLiveData<MovieDataSource> getMutableLiveData() {
        return mutableLiveData;
    }
}
