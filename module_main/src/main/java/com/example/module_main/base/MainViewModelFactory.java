package com.example.module_main.base;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;

import com.example.module_main.api.RetrofitMainHelper;
import com.example.module_main.screen.viewmodel.MainViewModel;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class MainViewModelFactory implements ViewModelProvider.Factory {

    private RetrofitMainHelper retrofitHelper;

    @Inject
    public MainViewModelFactory(RetrofitMainHelper retrofitHelper) {
        this.retrofitHelper = retrofitHelper;
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainViewModel.class)) {
            return (T) new MainViewModel(retrofitHelper);
        }

        throw new IllegalArgumentException("Unknown class name");
    }
}
