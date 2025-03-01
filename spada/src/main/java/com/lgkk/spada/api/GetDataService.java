package com.lgkk.spada.api;

import com.lgkk.spada.model._getdata.DataMainObject;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetDataService {

    @GET("all_category_forums")
    Observable<DataMainObject> getData(@Query("mode") String mode, @Query("is_my_category") String myCategory, @Query("device_id") String deviceId);

}
