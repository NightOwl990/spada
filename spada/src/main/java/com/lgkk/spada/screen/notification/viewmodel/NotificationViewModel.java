package com.lgkk.spada.screen.notification.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.lgkk.spada.api.RetrofitHelper;
import com.lgkk.base_project.base.BaseViewModel;
import com.lgkk.spada.model.home.NotificationBean;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class NotificationViewModel extends BaseViewModel {

    private final RetrofitHelper retrofitHelper;
    private final MutableLiveData<List<NotificationBean>> notificationList = new MutableLiveData<>();

    public MutableLiveData<List<NotificationBean>> getNotificationList() {
        return notificationList;
    }

    public NotificationViewModel(RetrofitHelper retrofitHelper) {
        this.retrofitHelper = retrofitHelper;
        compositeDisposable = new CompositeDisposable();
    }

    public void loadNotificationList(String accessToken, int skip, int limit) {
        Disposable disposable = retrofitHelper.getNotificationList(accessToken, skip, limit)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                        (beans) -> {
                            notificationList.setValue(beans);
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



