package com.louiedonios.android.applicationdeveloperassessment.presenters;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.louiedonios.android.applicationdeveloperassessment.models.Movie;
import com.louiedonios.android.applicationdeveloperassessment.repositories.DownloadOperation;
import com.louiedonios.android.applicationdeveloperassessment.repositories.OnDownloadOperationComplete;
import com.louiedonios.android.applicationdeveloperassessment.views.MovieDetailsScreenView;

import java.util.List;

import static com.android.volley.toolbox.Volley.newRequestQueue;

/**
 * Created by Louie on 11/6/2017.
 */

public class MovieDetailsScreenPresenter {


    private final MovieDetailsScreenView view;
    private final DownloadOperation downloadOperation;


    public MovieDetailsScreenPresenter(MovieDetailsScreenView view, DownloadOperation downloadOperation) {
        this.view = view;
        this.downloadOperation = downloadOperation;
    }

    public void loadMovie(){
        downloadOperation.download(new OnDownloadOperationComplete() {
            @Override
            public void onDownloadComplete(List<?> result) {
                if(!((List<Movie>)result).isEmpty())
                    view.displayMovieDetails(((List<Movie>)result).get(0));
            }

            @Override
            public void onDownloadFailed() {
                view.displayNoMovieDetails();
            }
        });
    }


}
