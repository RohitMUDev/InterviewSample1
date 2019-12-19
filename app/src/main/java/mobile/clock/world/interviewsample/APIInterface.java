package mobile.clock.world.interviewsample;

import java.util.ArrayList;
import java.util.List;

import mobile.clock.world.interviewsample.Model.Movies;
import mobile.clock.world.interviewsample.Model.Result;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {


    @GET("movie/top_rated")
    Call<Movies> getResults(@Query("api_key") String apikey);

    @GET("movie/top_rated")
    Call<Movies> getResultsWithPaging(@Query("api_key") String apikey, @Query("page") int page);
}