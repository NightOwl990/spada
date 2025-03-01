package com.lgkk.spada.screen.service.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.lgkk.spada.api.RetrofitHelper;
;
import com.lgkk.base_project.base.BaseViewModel;
import com.lgkk.spada.model.BannerDetail;
import com.lgkk.spada.model.Doctor;
import com.lgkk.spada.model.community.post.PostServiceMostView;
import com.lgkk.spada.model.service.FirstServiceCategory;
import com.lgkk.spada.model.service.MainServiceCategory;
import com.lgkk.spada.model.service.SecondServiceCategory;
import com.lgkk.spada.model.service.ServiceDetails;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ServiceViewModel extends BaseViewModel {

    private final RetrofitHelper retrofitHelper;
    private MutableLiveData<ServiceDetails> serviceDetails = new MutableLiveData<>();
    private MutableLiveData<List<FirstServiceCategory>> firstList = new MutableLiveData<>();
    private MutableLiveData<List<SecondServiceCategory>> secondList = new MutableLiveData<>();
    private MutableLiveData<List<MainServiceCategory>> mainList = new MutableLiveData<>();
    private MutableLiveData<List<BannerDetail>> bannerList = new MutableLiveData<>();
    private MutableLiveData<List<PostServiceMostView>> mostViewList = new MutableLiveData<>();
    private MutableLiveData<List<Doctor>> doctorList = new MutableLiveData<>();

    public MutableLiveData<ServiceDetails> getServiceDetailById() {
        return serviceDetails;
    }

    public MutableLiveData<List<FirstServiceCategory>> getFirstListServiceByServId() {
        return firstList;
    }
    public MutableLiveData<List<SecondServiceCategory>> getSecondListServiceByServId() {
        return secondList;
    }
    public MutableLiveData<List<MainServiceCategory>> getMainListServiceByServId() {
        return mainList;
    }
    public MutableLiveData<List<BannerDetail>> getListBannerByServId() {
        return bannerList;
    }
    public MutableLiveData<List<PostServiceMostView>> getMostViewListPostByServId() {
        return mostViewList;
    }
    public MutableLiveData<List<Doctor>> getDoctorListByServId() {
        return doctorList;
    }

    public ServiceViewModel(RetrofitHelper retrofitHelper) {
        this.retrofitHelper = retrofitHelper;
        compositeDisposable = new CompositeDisposable();
    }


    public void loadFirstListServiceByServId(String accessToken, String serviceId, int start, int limit) {
        Disposable disposable = retrofitHelper.getFirstListServiceByServId(accessToken, serviceId, start, limit)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                        (beans) -> {
                            firstList.setValue(beans);
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
    
    public void loadSecondListServiceByServId(String accessToken, String serviceId, int start, int limit) {
        Disposable disposable = retrofitHelper.getSecondListServiceByServId(accessToken, serviceId, start, limit)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                        (beans) -> {
                            secondList.setValue(beans);
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

    
    public void loadMainListServiceByServId(String accessToken, String serviceId, int start, int limit) {
        Disposable disposable = retrofitHelper.getMainListServiceByServId(accessToken, serviceId, start, limit)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                        (beans) -> {
                            mainList.setValue(beans);
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

    
    public void loadListBannerByServId(String accessToken, String serviceId, int start, int limit) {
        Disposable disposable = retrofitHelper.getServiceBannerByServiceId(accessToken, serviceId, start, limit)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                        (beans) -> {
                            bannerList.setValue(beans);
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

    
    public void loadMostViewListPostByServId(String accessToken, String serviceId, int start, int limit) {
        Disposable disposable = retrofitHelper.getMostViewPostListByServId(accessToken, serviceId, start, limit)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                        (beans) -> {
                            mostViewList.setValue(beans);
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

    public void loadDoctorListByServId(String accessToken, String serviceId, int start, int limit) {
        Disposable disposable = retrofitHelper.getDoctorListByServId(accessToken, serviceId, start, limit)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                        (beans) -> {
                            doctorList.setValue(beans);
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


    public void loadServiceDetailById(String accessToken, String serviceId) {
        Disposable disposable = retrofitHelper.getServiceDetailById(accessToken, serviceId)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                        (beans) -> {
                            serviceDetails.setValue(beans);
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


