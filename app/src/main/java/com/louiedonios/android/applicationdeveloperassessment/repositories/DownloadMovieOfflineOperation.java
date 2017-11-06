package com.louiedonios.android.applicationdeveloperassessment.repositories;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.louiedonios.android.applicationdeveloperassessment.models.Movie;
import com.louiedonios.android.applicationdeveloperassessment.models.MovieFromOfflineDB;

import java.util.Arrays;

/**
 * Created by Louie on 11/6/2017.
 */

public class DownloadMovieOfflineOperation implements DownloadOperation{


    private final String movieID;
    private final MoviesDbHelper dbHelper;
    private final SQLiteDatabase db;

    public DownloadMovieOfflineOperation(Context context, String movieID){

        this.movieID = movieID;

        dbHelper = new MoviesDbHelper(context);
        db = dbHelper.getReadableDatabase();
    }
    @Override
    public void download(OnDownloadOperationComplete callback) {

        MovieFromOfflineDB movie = new MovieFromOfflineDB(movieID, db);
        movie.downloadFromDB();

        if(movie.getTitle()!=null){
            callback.onDownloadComplete(Arrays.asList((Movie)movie));
        }else{
            callback.onDownloadFailed();
        }


    }
}
