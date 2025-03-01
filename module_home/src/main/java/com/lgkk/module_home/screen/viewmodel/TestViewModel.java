package com.lgkk.module_home.screen.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.lgkk.base_project.base.BaseViewModel;
import com.lgkk.module_home.api.RetrofitHomeHelper;
import com.lgkk.module_home.model.TestResult;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TestViewModel extends BaseViewModel {

    private final RetrofitHomeHelper retrofitHelper;
    private final MutableLiveData<List<TestResult>> testResultList = new MutableLiveData<>();

    public MutableLiveData<List<TestResult>> getTestResultList() {
        return testResultList;
    }

    public TestViewModel(RetrofitHomeHelper retrofitHelper) {
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


