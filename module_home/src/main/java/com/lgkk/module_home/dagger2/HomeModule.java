package com.lgkk.module_home.dagger2;

import androidx.lifecycle.ViewModelProvider;
import android.content.Context;

import com.lgkk.base_project._network.qualifier.GetDataUrl;
import com.lgkk.module_home.api.HomeService;
import com.lgkk.module_home.api.RetrofitHomeHelper;
import com.lgkk.module_home.base.HomeViewModelFactory;
import com.lgkk.base_project._network.helper.OkHttpHelper;
import com.lgkk.base_project.base.Constants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class HomeModule {
    private Context mContext;

    public HomeModule(Context context) {
        this.mContext = context;
    }

    @Provides
    public Context provideContext() {
        return mContext;
    }

    @Provides
    @Singleton
    ViewModelProvider.Factory getHomeViewModelFactory(RetrofitHomeHelper retrofitHomeHelper) {
        return new HomeViewModelFactory(retrofitHomeHelper);
    }


    public Retrofit createRetrofit(Retrofit.Builder builder, OkHttpClient client, String url) {
        return builder
                .baseUrl(url)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    @Singleton
    @Provides
    public RetrofitHomeHelper provideRetrofitHelper(HomeService homeService) {
        return new RetrofitHomeHelper(homeService);
    }



    @Provides
    @Singleton
    HomeService provideHomeService(@GetDataUrl Retrofit retrofit) {
        return retrofit.create(HomeService.class);
    }

    @Singleton
    @Provides
    @GetDataUrl
    public Retrofit provideHomeRetrofit(Retrofit.Builder builder, OkHttpClient client) {
        return createRetrofit(builder, client, Constants.GETDATA_URL);
    }


    @Singleton
    @Provides
    public OkHttpClient provideOkHttpClient() {
        return OkHttpHelper.getInstance().getOkHttpClient();
    }

    @Singleton
    @Provides
    public Retrofit.Builder provideRetrofitBuilder() {
        return new Retrofit.Builder();
    }

}
