package com.lgkk.spada.screen._account.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.lgkk.spada.api.RetrofitHelper;
import com.lgkk.base_project.base.BaseViewModel;
import com.lgkk.spada.model.Doctor;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginViewModel extends BaseViewModel {

    private final RetrofitHelper retrofitHelper;
    private final MutableLiveData<Doctor> doctorInfo = new MutableLiveData<>();

    public MutableLiveData<Doctor> getDoctorInfo() {
        return doctorInfo;
    }

    public LoginViewModel(RetrofitHelper retrofitHelper) {
        this.retrofitHelper = retrofitHelper;
        compositeDisposable = new CompositeDisposable();
    }

    public void loadDoctorInfo(String accessToken, String doctorId) {
        Disposable disposable = retrofitHelper.getDoctorInfo(accessToken, doctorId)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                        (beans) -> {
                            doctorInfo.setValue(beans);
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

