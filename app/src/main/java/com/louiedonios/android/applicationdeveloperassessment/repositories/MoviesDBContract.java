package com.louiedonios.android.applicationdeveloperassessment.repositories;

import android.provider.BaseColumns;

/**
 * Created by Louie on 11/5/2017.
 */

public class MoviesDBContract {

    private MoviesDBContract(){}

    public static class MovieEntry implements BaseColumns {

        public static final String TABLE_NAME = "movie";
        public static final String COLUMN_NAME_RATING = "rating";
        public static final String COLUMN_NAME_GENRES = "genres";
        public static final String COLUMN_NAME_LANGUAGE = "language";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_URL = "url";
        public static final String COLUMN_NAME_TITLE_LONG = "title_long";
        public static final String COLUMN_NAME_IMDB_CODE = "imdb_code";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_STATE = "state";
        public static final String COLUMN_NAME_YEAR = "year";
        public static final String COLUMN_NAME_RUNTIME = "runtime";
        public static final String COLUMN_NAME_OVERVIEW = "overview";
        public static final String COLUMN_NAME_SLUG = "slug";
        public static final String COLUMN_NAME_MPA_RATING = "mpa_rating";
    }
}
