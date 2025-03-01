package com.lgkk.spada.screen.service.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.lgkk.base_project.base.BaseViewModel;
import com.lgkk.spada.api.RetrofitHelper;
import com.lgkk.spada.model.shop.Cart;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CartViewModel extends BaseViewModel {

    private final RetrofitHelper retrofitHelper;
    private final MutableLiveData<List<Cart>> cartServiceList = new MutableLiveData<>();

    public MutableLiveData<List<Cart>> getListCartService() {
        return cartServiceList;
    }

    public CartViewModel(RetrofitHelper retrofitHelper) {
        this.retrofitHelper = retrofitHelper;
        compositeDisposable = new CompositeDisposable();
    }


    public void getListCartService(String token) {
        Disposable disposable = retrofitHelper.getListCartService(token)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                        (beans) -> {
                            cartServiceList.setValue(beans);
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


