package com.louiedonios.android.applicationdeveloperassessment.repositories;

import java.util.List;

/**
 * Created by Louie on 11/5/2017.
 */

public interface OnDownloadOperationComplete {

    public void onDownloadComplete(List<?> result);
    public void onDownloadFailed();
}
