package com.example.module_main.screen.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.example.module_main.api.RetrofitMainHelper;
import com.example.module_main.model.TestResult;
import com.lgkk.base_project.base.BaseViewModel;


import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class MainViewModel extends BaseViewModel {

    private final RetrofitMainHelper retrofitHelper;
    private final MutableLiveData<List<TestResult>> testResultList = new MutableLiveData<>();

    public MutableLiveData<List<TestResult>> getTestResultList() {
        return testResultList;
    }

    public MainViewModel(RetrofitMainHelper retrofitHelper) {
        this.retrofitHelper = retrofitHelper;
        compositeDisposable = new CompositeDisposable();
    }

    public void loadTestResultList() {
        Disposable disposable = retrofitHelper.getTestData()
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                        (beans) -> {
                            testResultList.setValue(beans);
                            loading.setValue(false);
                            repoLoadError.setValue(false);
                        }
                        ,
                        (e) -> {
                            loading.setValue(false);
                            repoLoadError.setValue(true);
                        }
                );
        addSubscrebe(disposable);
    }
}


