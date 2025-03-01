package com.lgkk.spada.screen.home.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.lgkk.spada.api.RetrofitHelper;
import com.lgkk.spada.model.shop.Product;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class ProductDetailViewModel extends ViewModel {

    private final RetrofitHelper retrofitHelper;
    private CompositeDisposable disposable;

    private final MutableLiveData<Product> productDetail = new MutableLiveData<>();
    private final MutableLiveData<Boolean> repoLoadError = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>();

    public MutableLiveData<Product> getProductDetail() {
        return productDetail;
    }

    public LiveData<Boolean> getError() {
        return repoLoadError;
    }

    public LiveData<Boolean> getLoading() {
        return loading;
    }

    public ProductDetailViewModel(RetrofitHelper retrofitHelper) {
        this.retrofitHelper = retrofitHelper;
        disposable = new CompositeDisposable();
    }

    public void loadProduct(String token, String id) {
        loading.setValue(true);
        disposable.add(retrofitHelper.getProduct(token, id)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                        (beans) -> {
                            productDetail.setValue(beans);
                            loading.setValue(false);
                            repoLoadError.setValue(false);
                        }
                        ,
                        (e) -> {
                            loading.setValue(false);
                            repoLoadError.setValue(true);
                        }
                ));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (disposable != null) {
            disposable.clear();
            disposable = null;
        }
    }
}