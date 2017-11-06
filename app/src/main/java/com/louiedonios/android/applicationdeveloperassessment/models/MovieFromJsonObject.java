package com.louiedonios.android.applicationdeveloperassessment.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Louie on 11/5/2017.
 */

public class MovieFromJsonObject extends Movie {

    public void deserializedJsonObj(JSONObject jsonObject) throws JSONException {

        rating = jsonObject.getDouble("rating");

        JSONArray genresJsonArr = jsonObject.getJSONArray("genres");
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<genresJsonArr.length();i++){
            String genre = genresJsonArr.getString(i);
            sb.append(genre);
            if(i<genresJsonArr.length()-1)
                sb.append(", ");
        }

        genres = sb.toString();
        language = jsonObject.getString("language");
        title= jsonObject.getString("title");
        url= jsonObject.getString("url");
        titleLong= jsonObject.getString("title_long");
        imdbCode= jsonObject.getString("imdb_code");
        id= jsonObject.getLong("id");
        state= jsonObject.getString("state");
        year = jsonObject.getInt("year");
        runTime = jsonObject.getInt("runtime");
        overView= jsonObject.getString("overview");
        slug= jsonObject.getString("slug");
        mpaRating= jsonObject.getString("mpa_rating");

    }


}
