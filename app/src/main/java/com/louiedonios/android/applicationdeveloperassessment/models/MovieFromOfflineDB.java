package com.louiedonios.android.applicationdeveloperassessment.models;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.louiedonios.android.applicationdeveloperassessment.repositories.MoviesDBContract;

/**
 * Created by Louie on 11/6/2017.
 */

public class MovieFromOfflineDB extends Movie {

    private final String movieID;
    private final SQLiteDatabase db;

    public MovieFromOfflineDB(String movieID, SQLiteDatabase db){
        this.movieID = movieID;
        this.db = db;
    }

    public void downloadFromDB(){

        String[] projection = {
                MoviesDBContract.MovieEntry.COLUMN_NAME_RATING,
                MoviesDBContract.MovieEntry.COLUMN_NAME_GENRES,
                MoviesDBContract.MovieEntry.COLUMN_NAME_LANGUAGE,
                MoviesDBContract.MovieEntry.COLUMN_NAME_TITLE,
                MoviesDBContract.MovieEntry.COLUMN_NAME_URL,
                MoviesDBContract.MovieEntry.COLUMN_NAME_TITLE_LONG,
                MoviesDBContract.MovieEntry.COLUMN_NAME_IMDB_CODE,
                MoviesDBContract.MovieEntry.COLUMN_NAME_ID,
                MoviesDBContract.MovieEntry.COLUMN_NAME_STATE,
                MoviesDBContract.MovieEntry.COLUMN_NAME_YEAR,
                MoviesDBContract.MovieEntry.COLUMN_NAME_RUNTIME,
                MoviesDBContract.MovieEntry.COLUMN_NAME_OVERVIEW,
                MoviesDBContract.MovieEntry.COLUMN_NAME_SLUG,
                MoviesDBContract.MovieEntry.COLUMN_NAME_MPA_RATING

        };


        String selection = MoviesDBContract.MovieEntry.COLUMN_NAME_ID + " =?";
        String[] selectionArgs = { movieID };
        String limit = "1";

        Cursor cursor = db.query(MoviesDBContract.MovieEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, null, limit);
        if(cursor.moveToLast()){

            rating = cursor.getDouble(cursor.getColumnIndexOrThrow(MoviesDBContract.MovieEntry.COLUMN_NAME_RATING));
            genres = cursor.getString(cursor.getColumnIndexOrThrow(MoviesDBContract.MovieEntry.COLUMN_NAME_GENRES));;
            language= cursor.getString(cursor.getColumnIndexOrThrow(MoviesDBContract.MovieEntry.COLUMN_NAME_LANGUAGE));;
            title= cursor.getString(cursor.getColumnIndexOrThrow(MoviesDBContract.MovieEntry.COLUMN_NAME_TITLE));;
            url= cursor.getString(cursor.getColumnIndexOrThrow(MoviesDBContract.MovieEntry.COLUMN_NAME_URL));;
            titleLong= cursor.getString(cursor.getColumnIndexOrThrow(MoviesDBContract.MovieEntry.COLUMN_NAME_TITLE_LONG));;
            imdbCode= cursor.getString(cursor.getColumnIndexOrThrow(MoviesDBContract.MovieEntry.COLUMN_NAME_IMDB_CODE));;
            id= cursor.getLong(cursor.getColumnIndexOrThrow(MoviesDBContract.MovieEntry.COLUMN_NAME_ID));;
            state= cursor.getString(cursor.getColumnIndexOrThrow(MoviesDBContract.MovieEntry.COLUMN_NAME_STATE));;
            year= cursor.getInt(cursor.getColumnIndexOrThrow(MoviesDBContract.MovieEntry.COLUMN_NAME_YEAR));;
            runTime= cursor.getInt(cursor.getColumnIndexOrThrow(MoviesDBContract.MovieEntry.COLUMN_NAME_RUNTIME));;
            overView= cursor.getString(cursor.getColumnIndexOrThrow(MoviesDBContract.MovieEntry.COLUMN_NAME_OVERVIEW));;
            slug= cursor.getString(cursor.getColumnIndexOrThrow(MoviesDBContract.MovieEntry.COLUMN_NAME_SLUG));;
            mpaRating= cursor.getString(cursor.getColumnIndexOrThrow(MoviesDBContract.MovieEntry.COLUMN_NAME_MPA_RATING));;
        }

        cursor.close();

    }
}
