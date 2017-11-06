package com.louiedonios.android.applicationdeveloperassessment;

import android.net.ConnectivityManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.louiedonios.android.applicationdeveloperassessment.models.Movie;
import com.louiedonios.android.applicationdeveloperassessment.presenters.MovieListScreenPresenter;
import com.louiedonios.android.applicationdeveloperassessment.repositories.DownloadMoviesOnlineOperation;
import com.louiedonios.android.applicationdeveloperassessment.repositories.DownloadOperation;
import com.louiedonios.android.applicationdeveloperassessment.repositories.SaveOperation;
import com.louiedonios.android.applicationdeveloperassessment.repositories.SaveMoviesOfflineOperation;
import com.louiedonios.android.applicationdeveloperassessment.utils.AccessNetworkState;
import com.louiedonios.android.applicationdeveloperassessment.views.MovieListScreenView;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements MovieListScreenView, OnMovieClickListener{

    private RecyclerView rvMovies;
    private LinearLayoutManager layoutManager;
    private MovieListAdapter adapter;

    private List<Movie> movies;

    public MainActivityFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);

        rvMovies = view.findViewById(R.id.rvMovies);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        rvMovies.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(getActivity());
        rvMovies.setLayoutManager(layoutManager);

        movies = new ArrayList<Movie>();
        //movies = Arrays.asList(new Movie(), new Movie(), new Movie());

        adapter = new MovieListAdapter(movies, getActivity(), this);
        rvMovies.setAdapter(adapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        DownloadOperation downloadMoviesOnlineOperation = new DownloadMoviesOnlineOperation(getActivity());
        SaveOperation saveMoviesOfflineOperation = new SaveMoviesOfflineOperation(getActivity().getApplicationContext());
        MovieListScreenPresenter presenter = new MovieListScreenPresenter(this, downloadMoviesOnlineOperation, saveMoviesOfflineOperation, getActivity());

        presenter.loadMovie();
    }

    @Override
    public void displayMovies(List<Movie> movies) {

        this.movies = movies;
        adapter.setMovies(this.movies);

    }

    @Override
    public void displayNoMovies() {

    }

    @Override
    public void onMovieClick(Movie movie) {

        DetailsFragment displayFrag = (DetailsFragment) getFragmentManager()
                .findFragmentById(R.id.details_frag);

        if(displayFrag==null){
            displayFrag = DetailsFragment.newInstance(Long.toString(movie.getId()));

            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack so the user can navigate back
            transaction.replace(R.id.list_frag   , displayFrag);
            transaction.addToBackStack(null);

            // Commit the transaction
            transaction.commit();
        }else{
            displayFrag.displayMovieDetails(movie);
        }
    }
}
