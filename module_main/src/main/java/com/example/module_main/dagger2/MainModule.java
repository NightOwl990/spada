package com.example.module_main.dagger2;

import androidx.lifecycle.ViewModelProvider;
import android.content.Context;

import com.example.module_main.api.MainService;
import com.example.module_main.api.RetrofitMainHelper;
import com.example.module_main.base.MainViewModelFactory;
import com.lgkk.base_project._network.helper.OkHttpHelper;
import com.lgkk.base_project._network.qualifier.SpadaUrl;
import com.lgkk.base_project.base.Constants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class MainModule {
    private Context mContext;

    public MainModule(Context context) {
        this.mContext = context;
    }

    @Provides
    public Context provideContext() {
        return mContext;
    }

    @Provides
    @Singleton
    ViewModelProvider.Factory getHomeViewModelFactory(RetrofitMainHelper retrofitHomeHelper) {
        return new MainViewModelFactory(retrofitHomeHelper);
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
    public RetrofitMainHelper provideRetrofitHelper(MainService mainService) {
        return new RetrofitMainHelper(mainService);
    }



    @Provides
    @Singleton
    MainService provideMainService(@SpadaUrl Retrofit retrofit) {
        return retrofit.create(MainService.class);
    }

    @Singleton
    @Provides
    @SpadaUrl
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
