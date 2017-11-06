package com.louiedonios.android.applicationdeveloperassessment;

import com.louiedonios.android.applicationdeveloperassessment.models.Movie;
import com.louiedonios.android.applicationdeveloperassessment.presenters.MovieListScreenPresenter;
import com.louiedonios.android.applicationdeveloperassessment.repositories.DownloadOperation;
import com.louiedonios.android.applicationdeveloperassessment.repositories.OnDownloadOperationComplete;
import com.louiedonios.android.applicationdeveloperassessment.repositories.OnSaveOperationComplete;
import com.louiedonios.android.applicationdeveloperassessment.repositories.SaveOperation;
import com.louiedonios.android.applicationdeveloperassessment.views.MovieListScreenView;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Louie on 11/5/2017.
 */
public class MovieListScreenPresenterTest {

    private MovieListScreenView view;

    @Before
    public void setUp() throws Exception {
        //given
        view = new MockMovieListScreen();

    }

    @After
    public void tearDown() throws Exception {
        view = null;
    }

    @Test
    public void shouldDownloadMoviesOnlineCompleted(){

        //given
        DownloadOperation dlOperation = new MockDownloadMoviesOnlineOperation(true);

        //when
        MovieListScreenPresenter presenter = new MovieListScreenPresenter(view, dlOperation, null, null);
        presenter.loadMovie();

        //then
        Assert.assertEquals(true, ((MockMovieListScreen)view).diplayMovies);

    }

    @Test
    public void shouldDownloadMoviesOnlineFailed(){

        //given
        DownloadOperation dlOperation = new MockDownloadMoviesOnlineOperation(false);

        //when
        MovieListScreenPresenter presenter = new MovieListScreenPresenter(view, dlOperation, null, null);
        presenter.loadMovie();

        //then
        Assert.assertEquals(true, ((MockMovieListScreen)view).doNotDisplayMovies);

    }

    @Test
    public void shouldSaveMoviesOfflineCompleted(){

        //given
        SaveOperation saveOperation = new MockSaveMoviesOfflineOperation(true);

        //when
        MovieListScreenPresenter presenter = new MovieListScreenPresenter(view, null, saveOperation, null);
        presenter.saveMovieOffline(Arrays.asList(new Movie(), new Movie(), new Movie()));

        //then
        Assert.assertEquals(true, ((MockMovieListScreen)view).diplayMovies);
    }

    @Test
    public void shouldSaveMoviesOfflineFailed(){

        //given
        SaveOperation saveOperation = new MockSaveMoviesOfflineOperation(false);

        //when
        MovieListScreenPresenter presenter = new MovieListScreenPresenter(view, null, saveOperation, null);
        presenter.saveMovieOffline(Arrays.asList(new Movie(), new Movie(), new Movie()));

        //then
        Assert.assertEquals(true, ((MockMovieListScreen)view).doNotDisplayMovies);
    }

    private class  MockSaveMoviesOfflineOperation implements SaveOperation {

        private boolean success;
        private List<Movie> movies;

        public  MockSaveMoviesOfflineOperation(boolean success){
            this.success = success;
        }

        @Override
        public void save(OnSaveOperationComplete callback) {
           if(success)
               callback.onSaveComplete(movies);
           else
               callback.onSaveFailed();
        }

        @Override
        public void setItems(List<?> items) {
            movies = (List<Movie>)movies;
        }

    }

    private class MockMovieListScreen implements MovieListScreenView{

        boolean diplayMovies;
        boolean doNotDisplayMovies;

        @Override
        public void displayMovies(List<Movie> movies) {
            diplayMovies = true;
        }

        @Override
        public void displayNoMovies() {
            doNotDisplayMovies = true;
        }
    }

    private class  MockDownloadMoviesOnlineOperation implements DownloadOperation {
        private boolean success;

        public MockDownloadMoviesOnlineOperation(boolean success){
            this.success = success;

        }


        @Override
        public void download(OnDownloadOperationComplete callback) {

            if(success)
                callback.onDownloadComplete(Arrays.asList(new Movie(), new Movie(), new Movie()));
            else
                callback.onDownloadFailed();


        }
    }

}