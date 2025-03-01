package com.lgkk.spada.screen._main;

import androidx.lifecycle.MutableLiveData;

import com.lgkk.spada.api.RetrofitHelper;
import com.lgkk.base_project.base.BaseViewModel;
import com.lgkk.spada.model.account.Splash;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainViewModel extends BaseViewModel {

    private final RetrofitHelper retrofitHelper;
    private final MutableLiveData<Splash> splashData = new MutableLiveData<>();

    public MutableLiveData<Splash> getSplashData() {
        return splashData;
    }

    public MainViewModel(RetrofitHelper retrofitHelper) {
        this.retrofitHelper = retrofitHelper;
        compositeDisposable = new CompositeDisposable();
    }

    public void getUpdateVersionList(String token) {
        Disposable disposable = retrofitHelper.getUpdateVersionList(token)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                        (beans) -> {

                        }
                        ,
                        (e) -> {

                        }
                );

        addSubscrebe(disposable);
    }

}


