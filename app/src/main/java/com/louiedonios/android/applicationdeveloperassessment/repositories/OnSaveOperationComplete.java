package com.louiedonios.android.applicationdeveloperassessment.repositories;

import java.util.List;

/**
 * Created by Louie on 11/5/2017.
 */

public interface OnSaveOperationComplete {

    public void onSaveComplete(List<?> savedMovies);
    public void onSaveFailed();
}
