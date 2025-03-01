package com.lgkk.spada.dagger2;


import androidx.lifecycle.ViewModelProvider;
import android.content.Context;

import com.lgkk.base_project._network.helper.OkHttpHelper;
import com.lgkk.base_project._network.qualifier.SpadaUrl;
import com.lgkk.base_project._network.qualifier.GetDataUrl;
import com.lgkk.base_project._network.qualifier.StorageUrl;
import com.lgkk.base_project.base.Constants;
import com.lgkk.spada.base.ViewModelFactory;
import com.lgkk.spada.api.BiboService;
import com.lgkk.spada.api.GetDataService;
import com.lgkk.spada.api.RetrofitHelper;
import com.lgkk.spada.api.StorageService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class SpadaModule {

    private Context mContext;

    public SpadaModule(Context context) {
        this.mContext = context;
    }

    @Provides
    public Context provideContext() {
        return mContext;
    }

    @Provides
    @Singleton
    ViewModelProvider.Factory getViewModelFactory(RetrofitHelper retrofitHelper) {
        return new ViewModelFactory(retrofitHelper);
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
    public RetrofitHelper provideRetrofitHelper(BiboService biboService, GetDataService getDataService, StorageService storageService) {
        return new RetrofitHelper(biboService, getDataService, storageService);
    }


    @Provides
    @Singleton
    BiboService provideBiboService(@SpadaUrl Retrofit retrofit) {
        return retrofit.create(BiboService.class);
    }

    @Provides
    @Singleton
    GetDataService provideGetDataService(@GetDataUrl Retrofit retrofit) {
        return retrofit.create(GetDataService.class);
    }

    @Provides
    @Singleton
    StorageService provideStorageService(@StorageUrl Retrofit retrofit) {
        return retrofit.create(StorageService.class);
    }


    @Singleton
    @Provides
    @GetDataUrl
    public Retrofit provideGetDataRetrofit(Retrofit.Builder builder, OkHttpClient client) {
        return createRetrofit(builder, client, Constants.GETDATA_URL);
    }


    @Singleton
    @Provides
    @SpadaUrl
    public Retrofit provideBiboRetrofit(Retrofit.Builder builder, OkHttpClient client) {
        return createRetrofit(builder, client, Constants.BASE_RETROFIT_API_URL);
    }

    @Singleton
    @Provides
    @StorageUrl
    public Retrofit provideStorageRetrofit(Retrofit.Builder builder, OkHttpClient client) {
        return createRetrofit(builder, client, Constants.STORAGE_URL);
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
