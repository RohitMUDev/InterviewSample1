package mobile.clock.world.interviewsample;

import android.util.Log;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Converters {
        @TypeConverter
        public List<Integer> gettingListFromString(String genreIds) {
            List<Integer> list = new ArrayList<>();

            String[] array = genreIds.split(",");
            Log.e("test","test");

            for (String s : array) {
                if (!s.isEmpty()) {
                    list.add(Integer.parseInt(s));
                }
            }
            return list;
        }

        @TypeConverter
        public String writingStringFromList(List<Integer> list) {
            String genreIds = "";
            for (int i : list) {
                genreIds += "," + i;
            }
            return genreIds;
        }
}