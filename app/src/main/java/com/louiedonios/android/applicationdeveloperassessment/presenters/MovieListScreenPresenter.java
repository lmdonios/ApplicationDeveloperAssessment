package com.louiedonios.android.applicationdeveloperassessment.presenters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.louiedonios.android.applicationdeveloperassessment.R;
import com.louiedonios.android.applicationdeveloperassessment.models.Movie;
import com.louiedonios.android.applicationdeveloperassessment.repositories.DownloadOperation;
import com.louiedonios.android.applicationdeveloperassessment.repositories.OnDownloadOperationComplete;
import com.louiedonios.android.applicationdeveloperassessment.repositories.OnSaveOperationComplete;
import com.louiedonios.android.applicationdeveloperassessment.repositories.SaveOperation;
import com.louiedonios.android.applicationdeveloperassessment.repositories.SaveMoviesOfflineOperation;
import com.louiedonios.android.applicationdeveloperassessment.utils.AccessNetworkState;
import com.louiedonios.android.applicationdeveloperassessment.views.MovieListScreenView;

import java.util.List;

/**
 * Created by Louie on 11/5/2017.
 */

public class MovieListScreenPresenter {


    private final Context context;
    private MovieListScreenView view;
    private DownloadOperation downloadOperation;
    private SaveOperation saveOperation;

    public MovieListScreenPresenter(MovieListScreenView view, DownloadOperation downloadOperation, SaveOperation saveOperation, Context context) {
        this.view = view;
        this.downloadOperation = downloadOperation;
        this.saveOperation = saveOperation;
        this.context = context;

    }

    public void loadMovie() {

        if(context!=null && !AccessNetworkState.isNetworkAvailable(context)){

            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setCancelable(true);
            builder.setMessage(R.string.no_connection);
            builder.setTitle(R.string.no_connection_title);
            builder.setPositiveButton(R.string.settings_button_text, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which)
                {
                    context.startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
                }
            });

            builder.setNegativeButton(R.string.cancel_button_text, new DialogInterface.OnClickListener()
            {
                public void onClick(DialogInterface dialog, int which)
                {
                    return;
                }
            });

            builder.setOnCancelListener(new DialogInterface.OnCancelListener()
            {
                public void onCancel(DialogInterface dialog) {
                    return;
                }
            });

            builder.show();

        }else {

            this.downloadOperation.download(new OnDownloadOperationComplete() {
                @Override
                public void onDownloadComplete(List<?> result) {
                    List<Movie> movies = (List<Movie>) result;

                    if(movies!=null && !movies.isEmpty()){
                        if(saveOperation==null) {

                            view.displayMovies(movies);
                        }else {

                            saveMovieOffline(movies);
                        }
                    }
                }

                @Override
                public void onDownloadFailed() {
                    view.displayNoMovies();
                }
            });

        }

    }

    public void saveMovieOffline(final List<Movie> movies){

        this.saveOperation.setItems(movies);

        this.saveOperation.save(new OnSaveOperationComplete() {
            @Override
            public void onSaveComplete(List<?> savedMovies) {

                view.displayMovies((List<Movie>) savedMovies);
            }

            @Override
            public void onSaveFailed() {
                view.displayNoMovies();
            }
        });

    }


}
