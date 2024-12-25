package com.example.mpprojectmtvtracker;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mpprojectmtvtracker.entity.Movie;

import java.util.List;


public class TMDBSearchActivityAdapter extends RecyclerView.Adapter<TMDBSearchActivityAdapter.ItemHolder>{

    private List<Movie> movies;
    private Context context;

    public TMDBSearchActivityAdapter(Context context, List<Movie> movies){
        this.context = context;
        this.movies = movies;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_in_list_holder, parent, false);
        return new ItemHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.textView.setText(movie.getMovieName());
        //holder.imageView.setImageResource(item.imageResource);

        // Set an OnClickListener for each item
//        holder.itemView.setOnClickListener(v -> {
//            String description = "";
//            // Create an Intent to navigate to DetailActivity
//            Intent intent = new Intent(context, ActivityDetail.class);
//            // Pass the clicked item's text to the DetailActivity
//            intent.putExtra("ITEM_TEXT", item.title);
//            intent.putExtra("ITEM_DESC", item.description);
//            intent.putExtra("ITEM_PHOTO_SOURCE", item.imageResource);
//            // Start the activity
//            context.startActivity(intent);
//        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
    //
    public static class ItemHolder extends RecyclerView.ViewHolder {
        TextView textView;

        ImageView imageView;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.movie_in_list_holder_title);
            imageView = itemView.findViewById(R.id.movie_in_list_holder_image);

        }
    }
}
