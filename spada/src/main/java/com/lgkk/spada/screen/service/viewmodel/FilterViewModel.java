package com.lgkk.spada.screen.service.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.lgkk.spada.api.RetrofitHelper;
import com.lgkk.base_project.base.BaseViewModel;
import com.lgkk.spada.model.FilterTypeDetail;
import com.lgkk.spada.model.HospitalDetail;
import com.lgkk.spada.model.service.ServiceDetails;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FilterViewModel extends BaseViewModel {
    private final RetrofitHelper retrofitHelper;
    private final MutableLiveData<List<ServiceDetails>> serviceDetailList = new MutableLiveData<>();
    private final MutableLiveData<List<FilterTypeDetail>> multiTypeFilterList = new MutableLiveData<>();
    private final MutableLiveData<List<FilterTypeDetail>> productFilterList = new MutableLiveData<>();
    private final MutableLiveData<List<FilterTypeDetail>> cityFilterList = new MutableLiveData<>();
    private final MutableLiveData<List<FilterTypeDetail>> placeFilterList = new MutableLiveData<>();
    private final MutableLiveData<List<HospitalDetail>> hospitalDetailList = new MutableLiveData<>();

    public FilterViewModel(RetrofitHelper retrofitHelper) {
        this.retrofitHelper = retrofitHelper;
        compositeDisposable = new CompositeDisposable();
    }

    public MutableLiveData<List<HospitalDetail>> getListHospitalDetailByCategoryId() {
        return hospitalDetailList;
    }

    public MutableLiveData<List<ServiceDetails>> getListServiceDetailByCategoryId() {
        return serviceDetailList;
    }

    public MutableLiveData<List<FilterTypeDetail>> getMultiTypeFilterList() {
        return multiTypeFilterList;
    }

    public MutableLiveData<List<FilterTypeDetail>> getProductFilterList() {
        return productFilterList;
    }

    public MutableLiveData<List<FilterTypeDetail>> getCityFilterList() {
        return cityFilterList;
    }

    public MutableLiveData<List<FilterTypeDetail>> getPlaceFilterList() {
        return placeFilterList;
    }


    public void loadProductFilterList(String accessToken, String targetId) {
        Disposable disposable = retrofitHelper.getProductFilterList(accessToken, targetId)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                        (beans) -> {
                            productFilterList.setValue(beans);
                            repoLoadError.setValue(false);
                        }
                        ,
                        (e) -> {
                            repoLoadError.setValue(true);
                        }
                );
        addSubscrebe(disposable);
    }

    public void loadCityFilterList(String accessToken, String targetId) {
        Disposable disposable = retrofitHelper.getCityFilterList(accessToken, targetId)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                        (beans) -> {
                            cityFilterList.setValue(beans);
                            repoLoadError.setValue(false);
                        }
                        ,
                        (e) -> {
                            repoLoadError.setValue(true);
                        }
                );
        addSubscrebe(disposable);
    }

    public void loadPlaceFilterList(String accessToken, String targetId) {
        Disposable disposable = retrofitHelper.getPlaceFilterList(accessToken, targetId)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                        (beans) -> {
                            placeFilterList.setValue(beans);
                            repoLoadError.setValue(false);
                        }
                        ,
                        (e) -> {
                            repoLoadError.setValue(true);
                        }
                );
        addSubscrebe(disposable);
    }

    public void loadMultiTypeFilterList(String accessToken, String targetId) {
        Disposable disposable = retrofitHelper.getMultiTypeFilterList(accessToken, targetId)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                        (beans) -> {
                            multiTypeFilterList.setValue(beans);
                            repoLoadError.setValue(false);
                        }
                        ,
                        (e) -> {
                            repoLoadError.setValue(true);
                        }
                );
        addSubscrebe(disposable);
    }


    public void loadListServiceDetailByCategoryId(String accessToken, String categoryId, int start, int limit) {
        Disposable disposable = retrofitHelper.getServiceDetailByCategoryId(accessToken, categoryId, start, limit)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                        (beans) -> {
                            serviceDetailList.setValue(beans);
                            repoLoadError.setValue(false);
                        }
                        ,
                        (e) -> {
                            repoLoadError.setValue(true);
                        }
                );
        addSubscrebe(disposable);
    }

    public void loadListHospitalDetailByCategoryId(String accessToken, String categoryId, int start, int limit) {
        Disposable disposable = retrofitHelper.getHospitalDetailByCategoryId(accessToken, categoryId, start, limit)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                        (beans) -> {
                            hospitalDetailList.setValue(beans);
                            repoLoadError.setValue(false);
                        }
                        ,
                        (e) -> {
                            repoLoadError.setValue(true);
                        }
                );
        addSubscrebe(disposable);
    }
}



