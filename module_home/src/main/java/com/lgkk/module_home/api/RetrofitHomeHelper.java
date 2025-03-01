package com.lgkk.module_home.api;

import com.lgkk.module_home.model.TestResult;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import retrofit2.http.GET;

public class RetrofitHomeHelper {
    private final HomeService homeService;

    @Inject
    public RetrofitHomeHelper(HomeService homeService) {
        this.homeService = homeService;
    }

    //    Shop -------------

    public Observable<List<TestResult>> getTestData() {
        return homeService.getTestData();
    }
}
