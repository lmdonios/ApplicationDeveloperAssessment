package com.louiedonios.android.applicationdeveloperassessment;

import com.louiedonios.android.applicationdeveloperassessment.models.Movie;
import com.louiedonios.android.applicationdeveloperassessment.presenters.MovieDetailsScreenPresenter;
import com.louiedonios.android.applicationdeveloperassessment.repositories.DownloadOperation;
import com.louiedonios.android.applicationdeveloperassessment.repositories.OnDownloadOperationComplete;
import com.louiedonios.android.applicationdeveloperassessment.views.MovieDetailsScreenView;


import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by Louie on 11/6/2017.
 */
public class MovieDetailsScreenPresenterTest {
    private MovieDetailsScreenView view;
    @Before
    public void setUp() throws Exception {
        view = new MockMovieDetailsScreen();
    }

    @After
    public void tearDown() throws Exception {
        view = null;
    }

    @Test
    public void shouldDownloadMovieOfflineCompleted(){

        //given
        DownloadOperation dlOperation = new MockDownloadMovieOffline(true);

        //when
        MovieDetailsScreenPresenter presenter = new MovieDetailsScreenPresenter(view, dlOperation);
        presenter.loadMovie();
        //then
        Assert.assertEquals(true, ((MockMovieDetailsScreen)view).displayMovieDetails);
    }

    @Test
    public void shouldDownloadMovieOfflineFailed(){

        //given
        DownloadOperation dlOperation = new MockDownloadMovieOffline(false);

        //when
        MovieDetailsScreenPresenter presenter = new MovieDetailsScreenPresenter(view, dlOperation);
        presenter.loadMovie();
        //then
        Assert.assertEquals(true, ((MockMovieDetailsScreen)view).doNotDisplayMovieDetails);
    }


    private class MockDownloadMovieOffline implements DownloadOperation{

        private boolean success;

        public MockDownloadMovieOffline(boolean success) {
            this.success = success;
        }

        @Override
        public void download(OnDownloadOperationComplete callback) {
            if(success)
                callback.onDownloadComplete(Arrays.asList(new Movie()));
            else
                callback.onDownloadFailed();
        }
    }

    private class MockMovieDetailsScreen implements MovieDetailsScreenView{

        boolean displayMovieDetails;
        boolean doNotDisplayMovieDetails;

        @Override
        public void displayMovieDetails(Movie movie) {
            if(movie!=null)
                displayMovieDetails =true;
        }

        @Override
        public void displayNoMovieDetails() {
            doNotDisplayMovieDetails =true;

        }
    }
}