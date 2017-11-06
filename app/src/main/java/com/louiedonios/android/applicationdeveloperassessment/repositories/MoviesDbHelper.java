package com.louiedonios.android.applicationdeveloperassessment.repositories;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by Louie on 11/5/2017.
 */

public class MoviesDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Movies.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + MoviesDBContract.MovieEntry.TABLE_NAME + " (" +
                    MoviesDBContract.MovieEntry._ID + " INTEGER PRIMARY KEY," +
                    MoviesDBContract.MovieEntry.COLUMN_NAME_RATING + " REAL," +
                    MoviesDBContract.MovieEntry.COLUMN_NAME_GENRES + " TEXT," +
                    MoviesDBContract.MovieEntry.COLUMN_NAME_LANGUAGE + " TEXT," +
                    MoviesDBContract.MovieEntry.COLUMN_NAME_TITLE + " TEXT," +
                    MoviesDBContract.MovieEntry.COLUMN_NAME_URL + " TEXT," +
                    MoviesDBContract.MovieEntry.COLUMN_NAME_TITLE_LONG + " TEXT," +
                    MoviesDBContract.MovieEntry.COLUMN_NAME_IMDB_CODE + " TEXT," +
                    MoviesDBContract.MovieEntry.COLUMN_NAME_ID + " INTEGER," +
                    MoviesDBContract.MovieEntry.COLUMN_NAME_STATE + " TEXT," +
                    MoviesDBContract.MovieEntry.COLUMN_NAME_YEAR + " INTEGER," +
                    MoviesDBContract.MovieEntry.COLUMN_NAME_RUNTIME + " INTEGER," +
                    MoviesDBContract.MovieEntry.COLUMN_NAME_OVERVIEW + " TEXT," +
                    MoviesDBContract.MovieEntry.COLUMN_NAME_SLUG + " TEXT," +
                    MoviesDBContract.MovieEntry.COLUMN_NAME_MPA_RATING + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + MoviesDBContract.MovieEntry.TABLE_NAME;


    public MoviesDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
