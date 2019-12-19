package mobile.clock.world.interviewsample.Repository;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import mobile.clock.world.interviewsample.APIInterface;
import mobile.clock.world.interviewsample.Database.MovieDatabase;
import mobile.clock.world.interviewsample.Model.Movies;
import mobile.clock.world.interviewsample.Model.Result;
import mobile.clock.world.interviewsample.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MovieRepository {


    Application application;

    MutableLiveData<List<Result>> mutableResults = new MutableLiveData<>();

    MovieDatabase movieDatabase;
    public  MovieRepository(Application application){

        this.application = application;

        movieDatabase = MovieDatabase.getInstance(application);


    }

    public MutableLiveData<List<Result>> getDBData(){

        new GetResultsDB().execute();
        return mutableResults;
    }

    public MutableLiveData<List<Result>>  getResults(){


        Retrofit retrofit = RetrofitInstance.getInstance();

        APIInterface apiInterface = retrofit.create(APIInterface.class);

        final Call<Movies> results = apiInterface.getResults("a387ee846c4e0511eaca389cf362f050");


        results.enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(Call<Movies> call, Response<Movies> response) {


                if(response.isSuccessful()){

                    Movies results1 = response.body();
                    List<Result> results2 = results1.getResults();
                    new insertMovies().execute(results2);
                    mutableResults.postValue(results2);
                }

            }

            @Override
            public void onFailure(Call<Movies> call, Throwable t) {

                Log.e("Failed", t.getMessage());
            }
        });

        return  mutableResults;
    }


    private class insertMovies extends AsyncTask<List<Result>, Void, Void>{


        @Override
        protected Void doInBackground(List<Result>... lists) {

            movieDatabase.getMoviesDAO().insertMovies(lists[0]);
            return null;
        }
    }

    private class GetResultsDB extends AsyncTask<Void, Void, List<Result>>{



        @Override
        protected List<Result> doInBackground(Void... voids) {



            return  movieDatabase.getMoviesDAO().getMovies();
        }

        @Override
        protected void onPostExecute(List<Result> results) {
            super.onPostExecute(results);

            mutableResults.postValue(results);
        }
    }



}
