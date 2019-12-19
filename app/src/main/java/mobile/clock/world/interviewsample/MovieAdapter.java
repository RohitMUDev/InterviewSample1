package mobile.clock.world.interviewsample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import mobile.clock.world.interviewsample.Model.Result;


public class MovieAdapter extends PagedListAdapter<Result,MovieAdapter.ViewHolder> {

    Context context;
    List<Result> movies = new ArrayList<>();
    public MovieAdapter(Context context){
        super(Result.CALLBACK);

        this.context = context;

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate( R.layout.movie_list_item, viewGroup, false);
        return  new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Result result = getItem(position);
        holder.tvTitle.setText(result.getOriginalTitle());
        holder.tvRating.setText(Double.toString(result.getVoteAverage()));

        String imagePath="https://image.tmdb.org/t/p/w500"+result.getPosterPath();

        Glide.with(context)
                .load(imagePath)
                .placeholder(R.drawable.loading)
                .into(holder.ivMovie);


    }



    public  class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivMovie;
        TextView tvTitle;
        TextView tvRating;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            ivMovie = itemView.findViewById(R.id.ivMovie);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvRating = itemView.findViewById(R.id.tvRating);
        }
    }
}
