package com.louiedonios.android.applicationdeveloperassessment.repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.louiedonios.android.applicationdeveloperassessment.models.Movie;

import java.util.List;

/**
 * Created by Louie on 11/5/2017.
 */

public class SaveMoviesOfflineOperation implements SaveOperation {
    private final SQLiteDatabase db;
    private final MoviesDbHelper dbHelper;
    private List<Movie> moviesToSave;

    public  SaveMoviesOfflineOperation(Context context){

        dbHelper = new MoviesDbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    @Override
    public void save(OnSaveOperationComplete callback) {


        for(int i = 0;i<moviesToSave.size();i++){
            Movie movie = moviesToSave.get(i);
            boolean movieExist = movieExist(Long.toString(movie.getId()));
            // Gets the data repository in write mode

            if(!movieExist){

                // Create a new map of values, where column names are the keys
                ContentValues values = new ContentValues();

                values.put(MoviesDBContract.MovieEntry.COLUMN_NAME_RATING, movie.getRating());
                values.put(MoviesDBContract.MovieEntry.COLUMN_NAME_GENRES, movie.getGenres());
                values.put(MoviesDBContract.MovieEntry.COLUMN_NAME_LANGUAGE, movie.getLanguage());
                values.put(MoviesDBContract.MovieEntry.COLUMN_NAME_TITLE, movie.getTitle());
                values.put(MoviesDBContract.MovieEntry.COLUMN_NAME_URL, movie.getUrl());
                values.put(MoviesDBContract.MovieEntry.COLUMN_NAME_TITLE_LONG, movie.getTitleLong());
                values.put(MoviesDBContract.MovieEntry.COLUMN_NAME_IMDB_CODE, movie.getImdbCode());
                values.put(MoviesDBContract.MovieEntry.COLUMN_NAME_ID, movie.getId());
                values.put(MoviesDBContract.MovieEntry.COLUMN_NAME_STATE, movie.getState());
                values.put(MoviesDBContract.MovieEntry.COLUMN_NAME_YEAR, movie.getYear());
                values.put(MoviesDBContract.MovieEntry.COLUMN_NAME_RUNTIME, movie.getRunTime());
                values.put(MoviesDBContract.MovieEntry.COLUMN_NAME_OVERVIEW, movie.getOverView());
                values.put(MoviesDBContract.MovieEntry.COLUMN_NAME_SLUG, movie.getSlug());
                values.put(MoviesDBContract.MovieEntry.COLUMN_NAME_MPA_RATING, movie.getMpaRating());

                // Insert the new row, returning the primary key value of the new row
                db.insert(MoviesDBContract.MovieEntry.TABLE_NAME, null, values);
            }

        }

        callback.onSaveComplete(moviesToSave);
    }

    @Override
    public void setItems(List<?> movies) {
        this.moviesToSave = (List<Movie>) movies;
    }

    public void setMoviesToSave(List<Movie> moviesToSave){
        this.moviesToSave = moviesToSave;
    }

    private boolean movieExist(String searchItem) {

        String[] columns = { MoviesDBContract.MovieEntry.COLUMN_NAME_ID };
        String selection = MoviesDBContract.MovieEntry.COLUMN_NAME_ID + " =?";
        String[] selectionArgs = { searchItem };
        String limit = "1";

        Cursor cursor = db.query(MoviesDBContract.MovieEntry.TABLE_NAME, columns, selection, selectionArgs, null, null, null, limit);
        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        return exists;
    }
}
