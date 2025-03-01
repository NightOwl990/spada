package com.lgkk.spada.screen.service.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.lgkk.spada.api.RetrofitHelper;
import com.lgkk.spada.model.BannerDetail;
import com.lgkk.spada.model.service.ServiceCategory;
import com.lgkk.spada.model.service.ServiceUtility;
import com.lgkk.spada.model.shop.Category;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SuggestServiceViewModel extends ViewModel {

    private final RetrofitHelper retrofitHelper;
    private CompositeDisposable compositeDisposable;

    private final MutableLiveData<List<ServiceCategory>> categoryServiceList = new MutableLiveData<>();
    private final MutableLiveData<List<BannerDetail>> bannerDetailList = new MutableLiveData<>();
    private final MutableLiveData<List<ServiceUtility>> serviceUtilityList = new MutableLiveData<>();
    private final MutableLiveData<List<Category>> suggestTabList = new MutableLiveData<>();

    private final MutableLiveData<Boolean> repoLoadError = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>();

    public MutableLiveData<List<ServiceCategory>> getCategoryServiceList() {
        return categoryServiceList;
    }

    public MutableLiveData<List<ServiceUtility>> getServiceUtilityList() {
        return serviceUtilityList;
    }
    public MutableLiveData<List<BannerDetail>> getBannerDetailList() {
        return bannerDetailList;
    }
    public MutableLiveData<List<Category>> getSuggestTabList() {
        return suggestTabList;
    }
    public LiveData<Boolean> getError() {
        return repoLoadError;
    }
    public LiveData<Boolean> getLoading() {
        return loading;
    }

    public SuggestServiceViewModel(RetrofitHelper retrofitHelper) {
        this.retrofitHelper = retrofitHelper;
        compositeDisposable = new CompositeDisposable();
    }

    public void loadListSuggestTab(String accessToken, int start, int limit) {
        Disposable disposable = retrofitHelper.getListSuggestTab(accessToken, start, limit)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                        (beans) -> {
                            suggestTabList.setValue(beans);
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

    public void loadListCategoryService(String accessToken, int start, int limit) {
        Disposable disposable = retrofitHelper.getCategoryServiceList(accessToken, start, limit)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                        (beans) -> {
                            categoryServiceList.setValue(beans);
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

    public void loadListUtilityService(String accessToken, int start, int limit) {
        Disposable disposable = retrofitHelper.getListUtilityService(accessToken, start, limit)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                        (beans) -> {
                            serviceUtilityList.setValue(beans);
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

    public void loadListBannerDetail(String accessToken, int start, int limit) {
        Disposable disposable = retrofitHelper.getServiceBannerList(accessToken, start, limit)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                        (beans) -> {
                            bannerDetailList.setValue(beans);
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

    protected void addSubscrebe(Disposable disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
        }
    }
}

