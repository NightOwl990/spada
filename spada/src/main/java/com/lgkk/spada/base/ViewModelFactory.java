package com.lgkk.spada.base;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;

import com.lgkk.spada.api.RetrofitHelper;
import com.lgkk.spada.screen._account.viewmodel.LoginViewModel;
import com.lgkk.spada.screen._account.viewmodel.RegisterViewModel;
import com.lgkk.spada.screen._main.MainViewModel;
import com.lgkk.spada.screen._main.SpadaViewModel;
import com.lgkk.spada.screen.home.viewmodel.FlashSaleViewModel;
import com.lgkk.spada.screen.notification.viewmodel.NotificationViewModel;
import com.lgkk.spada.screen.service.viewmodel.CartViewModel;
import com.lgkk.spada.screen.service.viewmodel.DoctorViewModel;
import com.lgkk.spada.screen.service.viewmodel.FilterViewModel;
import com.lgkk.spada.screen.service.viewmodel.ServiceViewModel;
import com.lgkk.spada.screen.service.viewmodel.SuggestServiceViewModel;
import com.lgkk.spada.screen.service.viewmodel.SuggestTabViewModel;
import com.lgkk.spada.screen.home.viewmodel.ProductDetailViewModel;
import com.lgkk.spada.screen.home.viewmodel.ShopViewModel;
import com.lgkk.spada.screen.user.viewmodel.UserViewModel;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ViewModelFactory implements ViewModelProvider.Factory {

    private RetrofitHelper retrofitHelper;

    @Inject
    public ViewModelFactory(RetrofitHelper retrofitHelper) {
        this.retrofitHelper = retrofitHelper;
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ProductDetailViewModel.class)) {
            return (T) new ProductDetailViewModel(retrofitHelper);
        }
        if (modelClass.isAssignableFrom(SuggestServiceViewModel.class)) {
            return (T) new SuggestServiceViewModel(retrofitHelper);
        }

        if (modelClass.isAssignableFrom(SuggestTabViewModel.class)) {
            return (T) new SuggestTabViewModel(retrofitHelper);
        }

        if (modelClass.isAssignableFrom(UserViewModel.class)) {
            return (T) new UserViewModel(retrofitHelper);
        }

        if (modelClass.isAssignableFrom(DoctorViewModel.class)) {
            return (T) new DoctorViewModel(retrofitHelper);
        }
        if (modelClass.isAssignableFrom(NotificationViewModel.class)) {
            return (T) new NotificationViewModel(retrofitHelper);
        }
        if (modelClass.isAssignableFrom(FilterViewModel.class)) {
            return (T) new FilterViewModel(retrofitHelper);
        }
        if (modelClass.isAssignableFrom(ShopViewModel.class)) {
            return (T) new ShopViewModel(retrofitHelper);
        }
        if (modelClass.isAssignableFrom(SpadaViewModel.class)) {
            return (T) new SpadaViewModel(retrofitHelper);
        }

        if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            return (T) new LoginViewModel(retrofitHelper);
        }

        if (modelClass.isAssignableFrom(RegisterViewModel.class)) {
            return (T) new RegisterViewModel(retrofitHelper);
        }

        if (modelClass.isAssignableFrom(MainViewModel.class)) {
            return (T) new MainViewModel(retrofitHelper);
        }

        if (modelClass.isAssignableFrom(ServiceViewModel.class)) {
            return (T) new ServiceViewModel(retrofitHelper);
        }

        if (modelClass.isAssignableFrom(FlashSaleViewModel.class)) {
            return (T) new FlashSaleViewModel(retrofitHelper);
        }
        if (modelClass.isAssignableFrom(CartViewModel.class)) {
            return (T) new CartViewModel(retrofitHelper);
        }

        throw new IllegalArgumentException("Unknown class name");
    }
}
