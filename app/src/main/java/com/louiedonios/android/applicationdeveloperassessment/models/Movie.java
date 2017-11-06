package com.louiedonios.android.applicationdeveloperassessment.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Louie on 11/5/2017.
 */

public class Movie {

    protected double rating;
    protected String genres;
    protected String language;
    protected String title;
    protected String url;
    protected String titleLong;
    protected String imdbCode;
    protected long id;
    protected String state;
    protected int year;
    protected int runTime;
    protected String overView;
    protected String slug;
    protected String mpaRating;

    public double getRating() {
        return rating;
    }

    public String getGenres() {
        return genres;
    }

    public String getLanguage() {
        return language;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getTitleLong() {
        return titleLong;
    }

    public String getImdbCode() {
        return imdbCode;
    }

    public long getId() {
        return id;
    }

    public String getState() {
        return state;
    }

    public int getYear() {
        return year;
    }

    public int getRunTime() {
        return runTime;
    }

    public String getOverView() {
        return overView;
    }

    public String getSlug() {
        return slug;
    }

    public String getMpaRating() {
        return mpaRating;
    }


}
