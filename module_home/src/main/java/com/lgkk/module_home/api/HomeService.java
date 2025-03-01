package com.lgkk.module_home.api;

import com.lgkk.module_home.model.TestResult;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface HomeService {

    @GET("questions")
    Observable<List<TestResult>> getTestData();
}
