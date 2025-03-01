package com.example.module_main.api;


import com.example.module_main.model.TestResult;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class RetrofitMainHelper {
    private final MainService mainService;

    @Inject
    public RetrofitMainHelper(MainService mainService) {
        this.mainService = mainService;
    }

    //    Shop -------------

    public Observable<List<TestResult>> getTestData() {
        return mainService.getTestData();
    }
}
