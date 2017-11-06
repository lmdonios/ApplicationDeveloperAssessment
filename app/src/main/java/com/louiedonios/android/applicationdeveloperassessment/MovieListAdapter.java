package com.louiedonios.android.applicationdeveloperassessment;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.louiedonios.android.applicationdeveloperassessment.models.Movie;

import java.util.List;

import static com.android.volley.toolbox.Volley.*;

/**
 * Created by Louie on 11/6/2017.
 */

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder>{

    private final OnMovieClickListener onClickListener;
    private final Context context;
    private List<Movie> movies;

    private  RequestQueue requestQueue;
    private  ImageLoader imageLoader;

    public MovieListAdapter(List<Movie> movies, Context context, OnMovieClickListener onClickListener){

        this.movies = movies;
        this.context = context;
        this.onClickListener = onClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_movie, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Movie movie = movies.get(position);
        holder.tvTitle.setText(movie.getTitle());
        holder.tvYear.setText(Integer.toString(movie.getYear()));

        requestQueue = newRequestQueue(context);
        imageLoader = new ImageLoader(requestQueue, new ImageLoader.ImageCache() {
            private final LruCache<String, Bitmap> mCache = new LruCache<String, Bitmap>(10);
            public void putBitmap(String url, Bitmap bitmap) {
                mCache.put(url, bitmap);
            }
            public Bitmap getBitmap(String url) {
                return mCache.get(url);
            }
        });
        String url = "http://aacayaco.github.io/movielist/images/"+movie.getSlug()+"-backdrop.jpg";
        holder.networkIVbackDrop.setImageUrl(url,imageLoader);

        holder.networkIVbackDrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickListener.onMovieClick(movie);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
        this.notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        NetworkImageView networkIVbackDrop;
        TextView tvYear;
        TextView tvTitle;

        public ViewHolder(View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvYear = itemView.findViewById(R.id.tvYear);
            networkIVbackDrop = itemView.findViewById(R.id.networkIVbackDrop);
        }
    }
}
