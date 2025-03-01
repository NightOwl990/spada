package com.lgkk.spada.screen.home.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.lgkk.base_project.base.BaseViewModel;
import com.lgkk.spada.api.RetrofitHelper;
import com.lgkk.spada.model.shop.Product;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FlashSaleViewModel extends BaseViewModel {

    private final RetrofitHelper retrofitHelper;
    private final MutableLiveData<List<Product>> flashSaleByHourList = new MutableLiveData<>();

    public MutableLiveData<List<Product>> getFlashSaleByHourList() {
        return flashSaleByHourList;
    }

    public FlashSaleViewModel(RetrofitHelper retrofitHelper) {
        this.retrofitHelper = retrofitHelper;
        compositeDisposable = new CompositeDisposable();
    }

    public void loadFlashSaleByHourList(String accessToken, long startTime) {
        Disposable disposable = retrofitHelper.getFlashSaleByHourList(accessToken, startTime)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                        (beans) -> {
                            flashSaleByHourList.setValue(beans);
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

