package com.louiedonios.android.applicationdeveloperassessment.repositories;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.louiedonios.android.applicationdeveloperassessment.models.Movie;
import com.louiedonios.android.applicationdeveloperassessment.models.MovieFromJsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Louie on 11/5/2017.
 */

public class DownloadMoviesOnlineOperation implements DownloadOperation {

    private Context context;

    public DownloadMoviesOnlineOperation(Context context){

        this.context = context;
    }

    @Override
    public void download(final OnDownloadOperationComplete callback) {

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this.context);
        String url ="http://cayaco.info/movielist/list_movies_page1.json";

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                List<Movie> movies = new ArrayList<Movie>();

                try {
                    JSONObject dataJsonObj = response.getJSONObject("data");
                    JSONArray moviesJsonArr = dataJsonObj.getJSONArray("movies");

                   for(int i=0;i<moviesJsonArr.length();i++){

                       JSONObject movieJsonObj = moviesJsonArr.getJSONObject(i);
                       Movie movie = new MovieFromJsonObject();
                       ((MovieFromJsonObject)movie).deserializedJsonObj(movieJsonObj);
                       movies.add((Movie) movie);
                       //Log.d("download success", "title: "+movie.getTitle());
                   }

                   callback.onDownloadComplete(movies);
                } catch (JSONException e) {
                    if(movies.isEmpty())
                        callback.onDownloadFailed();
                    else
                        callback.onDownloadComplete(movies);
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                //Log.d("download failed", "response: "+error.getLocalizedMessage());
                callback.onDownloadFailed();
            }
        });

        queue.add(jsonRequest);
    }
}
