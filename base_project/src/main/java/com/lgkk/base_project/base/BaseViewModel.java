package com.lgkk.base_project.base;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class BaseViewModel extends ViewModel {
    protected final MutableLiveData<Boolean> repoLoadError = new MutableLiveData<>();
    protected final MutableLiveData<Boolean> loading = new MutableLiveData<>();

    protected CompositeDisposable compositeDisposable;

    public MutableLiveData<Boolean> getError() {
        return repoLoadError;
    }
    public MutableLiveData<Boolean> getLoading() {
        return loading;
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
