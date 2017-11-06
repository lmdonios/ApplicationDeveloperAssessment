package com.louiedonios.android.applicationdeveloperassessment.repositories;

import com.louiedonios.android.applicationdeveloperassessment.models.Movie;

import java.util.List;

/**
 * Created by Louie on 11/5/2017.
 */

public interface SaveOperation {

    public void save(OnSaveOperationComplete callback);
    public void setItems(List<?> items);
}
