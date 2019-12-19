package mobile.clock.world.interviewsample.DataSource;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import java.util.List;

import mobile.clock.world.interviewsample.APIInterface;
import mobile.clock.world.interviewsample.Model.Movies;
import mobile.clock.world.interviewsample.Model.Result;
import mobile.clock.world.interviewsample.Repository.MovieRepository;
import mobile.clock.world.interviewsample.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MovieDataSource extends PageKeyedDataSource<Integer, Result> {


    @Override
    public void loadInitial(@NonNull final LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, Result> callback) {
        Retrofit retrofit = RetrofitInstance.getInstance();

        APIInterface apiInterface = retrofit.create(APIInterface.class);

        final Call<Movies> results = apiInterface.getResults("a387ee846c4e0511eaca389cf362f050");


        results.enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(Call<Movies> call, Response<Movies> response) {


                if(response.isSuccessful()){

                    Movies results1 = response.body();
                    List<Result> results2 = results1.getResults();
                    callback.onResult(results2,null, 2);
                }

            }

            @Override
            public void onFailure(Call<Movies> call, Throwable t) {

                Log.e("Failed", t.getMessage());
            }
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Result> callback) {

    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Result> callback) {

        Retrofit retrofit = RetrofitInstance.getInstance();

        APIInterface apiInterface = retrofit.create(APIInterface.class);

        final Call<Movies> results = apiInterface.getResultsWithPaging("a387ee846c4e0511eaca389cf362f050", params.key + 1);


        results.enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(Call<Movies> call, Response<Movies> response) {


                if(response.isSuccessful()){

                    Movies results1 = response.body();
                    List<Result> results2 = results1.getResults();
                    callback.onResult(results2, params.key + 1);
                }

            }

            @Override
            public void onFailure(Call<Movies> call, Throwable t) {

                Log.e("Failed", t.getMessage());
            }
        });


    }
}
