package com.lgkk.spada.screen.home.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.lgkk.spada.api.RetrofitHelper;
import com.lgkk.base_project.base.BaseViewModel;
import com.lgkk.spada.model.BannerDetail;
import com.lgkk.spada.model.FlashCategory;
import com.lgkk.spada.model.RankingDetail;
import com.lgkk.spada.model.RecommendCity;
import com.lgkk.spada.model.service.ServiceCategory;
import com.lgkk.spada.model.shop.Product;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ShopViewModel extends BaseViewModel {

    private final RetrofitHelper retrofitHelper;
    private final MutableLiveData<List<FlashCategory>> flashList = new MutableLiveData<>();
    private final MutableLiveData<List<RankingDetail>> rankingList = new MutableLiveData<>();
    private final MutableLiveData<List<ServiceCategory>> categoryServiceList = new MutableLiveData<>();
    private final MutableLiveData<List<BannerDetail>> bannerDetailList = new MutableLiveData<>();
    private final MutableLiveData<List<RecommendCity>> recommendCityList = new MutableLiveData<>();
    private final MutableLiveData<List<Product>> hotFlipperProductList = new MutableLiveData<>();

    public MutableLiveData<List<Product>> getHotFlipperProductList() {
        return hotFlipperProductList;
    }

    public MutableLiveData<List<FlashCategory>> getFlashCategoryList() {
        return flashList;
    }
    public MutableLiveData<List<RankingDetail>> getRankingDetailList() {
        return rankingList;
    }
    public MutableLiveData<List<ServiceCategory>> getCategoryServiceList() {
        return categoryServiceList;
    }
    public MutableLiveData<List<BannerDetail>> getBannerDetailList() {
        return bannerDetailList;
    }

    public MutableLiveData<List<RecommendCity>> getShopRecommendCityList() {
        return recommendCityList;
    }


    public ShopViewModel(RetrofitHelper retrofitHelper) {
        this.retrofitHelper = retrofitHelper;
        compositeDisposable = new CompositeDisposable();
    }

    public void loadHotFlipperProductList(String accessToken, int start, int limit) {
        Disposable disposable = retrofitHelper.getHotFlipperProductList(accessToken, start, limit)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                        (beans) -> {
                            hotFlipperProductList.setValue(beans);
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

    public void loadShopRecommendCityList(String accessToken, int start, int limit) {
        Disposable disposable = retrofitHelper.getShopRecommendCityList(accessToken, start, limit)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                        (beans) -> {
                            recommendCityList.setValue(beans);
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

    public void loadBannerDetailList(String accessToken, int start, int limit) {
        Disposable disposable = retrofitHelper.getShopServiceBannerList(accessToken, start, limit)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                        (beans) -> {
                            bannerDetailList.setValue(beans);
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

    public void loadCategoryServiceList(String accessToken, int start, int limit) {
        Disposable disposable = retrofitHelper.getShopCategoryServiceList(accessToken, start, limit)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                        (beans) -> {
                            categoryServiceList.setValue(beans);
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

    public void loadFlashCategoryList(String accessToken) {
        Disposable disposable = retrofitHelper.getFlashCategoryList(accessToken)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                        (beans) -> {
                            flashList.setValue(beans);
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

    public void loadRankingDetailList(String accessToken) {
        Disposable disposable = retrofitHelper.getRankingDetailList(accessToken)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                        (beans) -> {
                            rankingList.setValue(beans);
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


