package com.louiedonios.android.applicationdeveloperassessment;


import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.LruCache;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.louiedonios.android.applicationdeveloperassessment.models.Movie;
import com.louiedonios.android.applicationdeveloperassessment.presenters.MovieDetailsScreenPresenter;
import com.louiedonios.android.applicationdeveloperassessment.repositories.DownloadMovieOfflineOperation;
import com.louiedonios.android.applicationdeveloperassessment.repositories.DownloadOperation;
import com.louiedonios.android.applicationdeveloperassessment.views.MovieDetailsScreenView;

import static com.android.volley.toolbox.Volley.newRequestQueue;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsFragment extends Fragment implements MovieDetailsScreenView{

    private static final String ARG_MOVIE_ID = "movie_id";
    private String movieID;
    private TextView tvTitle;
    private TextView tvYear;
    private TextView tvRating;
    private TextView tvOverview;
    private MovieDetailsScreenPresenter presenter;
    private RequestQueue requestQueue;
    private ImageLoader imageLoader;
    private NetworkImageView networkIVbackDrop;
    private NetworkImageView networkIVcover;

    public static DetailsFragment newInstance(String movieID) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_MOVIE_ID, movieID);
        fragment.setArguments(args);
        return fragment;
    }


    public DetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if(((AppCompatActivity) getActivity()).getSupportActionBar()!=null){
            ((AppCompatActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(false); // disable the button
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false); // remove the left caret
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(false); // remove the icon
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            movieID = getArguments().getString(ARG_MOVIE_ID);
        }
        if(((AppCompatActivity) getActivity()).getSupportActionBar()!=null){
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);

        tvTitle = (TextView)view.findViewById(R.id.tvTitle);
        tvYear = (TextView)view.findViewById(R.id.tvYear);
        tvRating = (TextView)view.findViewById(R.id.tvRating);
        tvOverview = (TextView)view.findViewById(R.id.tvOverview);
        networkIVbackDrop = (NetworkImageView)view.findViewById(R.id.networkIVbackDrop);
        networkIVcover = (NetworkImageView)view.findViewById(R.id.networkIVcover);

        if(movieID != null){
            DownloadOperation downloadOperation = new DownloadMovieOfflineOperation(getActivity().getApplicationContext(), movieID);
            ;
            //when
            presenter = new MovieDetailsScreenPresenter(this, downloadOperation);
            presenter.loadMovie();
        }





        return view;
    }


    @Override
    public void displayMovieDetails(Movie movie) {

        tvTitle.setText(movie.getTitle());
        tvYear.setText(Integer.toString(movie.getYear()));
        tvRating.setText(Double.toString(movie.getRating()));
        tvOverview.setText(movie.getOverView());

        requestQueue = newRequestQueue(getActivity());
        imageLoader = new ImageLoader(requestQueue, new ImageLoader.ImageCache() {
            private final LruCache<String, Bitmap> mCache = new LruCache<String, Bitmap>(10);
            public void putBitmap(String url, Bitmap bitmap) {
                mCache.put(url, bitmap);
            }
            public Bitmap getBitmap(String url) {
                return mCache.get(url);
            }
        });
        String backdrop = "http://aacayaco.github.io/movielist/images/"+movie.getSlug()+"-backdrop.jpg";
        String cover = "http://aacayaco.github.io/movielist/images/"+movie.getSlug()+"-cover.jpg";
        networkIVbackDrop.setImageUrl(backdrop,imageLoader);
        networkIVcover.setImageUrl(cover,imageLoader);
    }

    @Override
    public void displayNoMovieDetails() {

    }


}
