package com.lgkk.spada.screen.service.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.lgkk.spada.api.RetrofitHelper;
import com.lgkk.spada.model.community.post.PostService;
import com.lgkk.spada.model.service.ServiceDetails;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SuggestTabViewModel extends ViewModel {

    private final RetrofitHelper retrofitHelper;
    private CompositeDisposable compositeDisposable;

    private final MutableLiveData<List<PostService>> postServiceList = new MutableLiveData<>();
    private final MutableLiveData<List<ServiceDetails>> serviceDetailList = new MutableLiveData<>();
    private final MutableLiveData<Boolean> repoLoadError = new MutableLiveData<>();


    public MutableLiveData<List<PostService>> getListPostServiceByCategoryId() {
        return postServiceList;
    }

    public MutableLiveData<List<ServiceDetails>> getListServiceDetailByCategoryId() {
        return serviceDetailList;
    }


    public MutableLiveData<Boolean> getError() {
        return repoLoadError;
    }


    public SuggestTabViewModel(RetrofitHelper retrofitHelper) {
        this.retrofitHelper = retrofitHelper;
        compositeDisposable = new CompositeDisposable();
    }

    public void loadListPostServiceByCategoryId(String accessToken, String categoryId, int start, int limit) {
        Disposable disposable = retrofitHelper.getListPostServiceByCategoryId(accessToken, categoryId, start, limit)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                        (beans) -> {
                            postServiceList.setValue(beans);

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


