package com.louiedonios.android.applicationdeveloperassessment.views;

import com.louiedonios.android.applicationdeveloperassessment.models.Movie;

import java.util.List;

/**
 * Created by Louie on 11/5/2017.
 */

public interface MovieListScreenView {

    public void displayMovies(List<Movie> movies);
    public void displayNoMovies();
}
