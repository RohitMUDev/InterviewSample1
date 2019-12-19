package mobile.clock.world.interviewsample.Database;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.TypeConverters;

import mobile.clock.world.interviewsample.Converters;
import mobile.clock.world.interviewsample.Model.Result;

@Database(entities = {Result.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class MovieDatabase extends RoomDatabase {


    public abstract MoviesDAO getMoviesDAO();

    private static MovieDatabase instance ;

    public static synchronized  MovieDatabase getInstance(Application application){

        if(instance == null){


            instance = Room.databaseBuilder(application.getApplicationContext(),MovieDatabase.class,"MoviesDB").fallbackToDestructiveMigration().build();
        }

        return  instance;
    }
}
