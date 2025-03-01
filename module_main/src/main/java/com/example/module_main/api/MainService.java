package com.example.module_main.api;



import com.example.module_main.model.TestResult;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface MainService {

    @GET("questions")
    Observable<List<TestResult>> getTestData();
}
