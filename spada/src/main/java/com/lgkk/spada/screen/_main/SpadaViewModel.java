package com.lgkk.spada.screen._main;

import androidx.lifecycle.MutableLiveData;

import com.lgkk.spada.api.RetrofitHelper;
import com.lgkk.base_project.base.BaseViewModel;
import com.lgkk.spada.model.account.Splash;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SpadaViewModel extends BaseViewModel {

    private final RetrofitHelper retrofitHelper;
    private final MutableLiveData<Splash> splashData = new MutableLiveData<>();

    public MutableLiveData<Splash> getSplashData() {
        return splashData;
    }

    public SpadaViewModel(RetrofitHelper retrofitHelper) {
        this.retrofitHelper = retrofitHelper;
        compositeDisposable = new CompositeDisposable();
    }

    public void loadSplashData(String accessToken) {
        Disposable disposable = retrofitHelper.getSplashData(accessToken)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                        (beans) -> {
                            splashData.setValue(beans);
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

