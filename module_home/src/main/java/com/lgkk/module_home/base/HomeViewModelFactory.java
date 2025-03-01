package com.lgkk.module_home.base;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;

import com.lgkk.module_home.api.RetrofitHomeHelper;
import com.lgkk.module_home.screen.viewmodel.TestViewModel;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class HomeViewModelFactory implements ViewModelProvider.Factory {

    private RetrofitHomeHelper retrofitHelper;

    @Inject
    public HomeViewModelFactory(RetrofitHomeHelper retrofitHelper) {
        this.retrofitHelper = retrofitHelper;
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(TestViewModel.class)) {
            return (T) new TestViewModel(retrofitHelper);
        }

        throw new IllegalArgumentException("Unknown class name");
    }
}
