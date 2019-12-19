package mobile.clock.world.interviewsample.Database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import mobile.clock.world.interviewsample.Model.Movies;
import mobile.clock.world.interviewsample.Model.Result;

@Dao
public interface MoviesDAO {

    @Insert
    public void insertMovies(List<Result> results);


    @Query("Select * from Movies")
    public List<Result>  getMovies();
}
