package com.lgkk.spada.screen._main;

import androidx.lifecycle.ProcessLifecycleOwner;
import androidx.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.net.Uri;
import androidx.fragment.app.Fragment;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.lgkk.base_project.utils.PermissionUtils;
import com.lgkk.base_project.utils.RxBus;
import com.lgkk.base_project.utils.SharedPreferencesUtil;
import com.lgkk.base_project.utils.StringUtils;
import com.lgkk.base_project.widget.FlipVerticalSwingEnterDialog.BaseAnimatorSet;
import com.lgkk.base_project.widget.FlipVerticalSwingEnterDialog.OnBottomSettingClickListener;
import com.lgkk.base_project.widget.NonSwipeableViewPager;
import com.lgkk.base_project.widget.bottomflyco.CustomTabEntity;
import com.lgkk.base_project.widget.bottomflyco.FlycoAdapter;
import com.lgkk.base_project.widget.bottomflyco.TabEntity;
import com.lgkk.base_project.widget.flycotablayout.CommonTabLayout;
import com.lgkk.base_project.widget.flycotablayout.OnTabSelectListener;
import com.lgkk.spada.BuildConfig;
import com.lgkk.spada.R;
import com.lgkk.spada.api.Constants;

import com.lgkk.spada.base.BaseSpadaActivity;
import com.lgkk.spada.base.ViewModelFactory;
import com.lgkk.spada.event.ChangeAvatarActivityEvent;
import com.lgkk.spada.event.ChangeAvatarEvent;
import com.lgkk.spada.event.ChangeCommunityTabEvent;
import com.lgkk.spada.event.RefreshMainViewPagerEvent;
import com.lgkk.spada.model.Data;
import com.lgkk.spada.model.UpdateVersionBean;

import com.lgkk.spada.screen._account.fragment.EmptyFragment;
import com.lgkk.spada.screen.notification.fragment.NotificationFragment;
import com.lgkk.spada.screen.service.activity.ServiceDetailActivity;
import com.lgkk.spada.screen.service.fragment.ServiceFragment;

import com.lgkk.spada.screen.home.fragment.ShopV2Fragment;

import com.lgkk.spada.screen.user.fragment.UserV2Fragment;
import com.lgkk.spada.widget.ChangeAvatarDialog;
import com.lgkk.spada.widget.CheckUpdatePopupDialog;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;
import com.yalantis.ucrop.UCrop;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Hoang Nam on 12/06/2017.
 */

@Route(path = Constants.url_main)
public class MainActivity extends BaseSpadaActivity implements OnBottomSettingClickListener {
    private static final int REQUEST_POST_DETAIL = 888;
    private static final String A_HREF = "<a href=\"";
    private static final String HREF_CLOSED = "\">";
    private static final String HREF_END = "</a>";
    private static final String HTML = "<html>";
    private static final String HTML_END = "</html>";
    boolean doubleBackToExitPressedOnce = false;
    @BindView(R.id.tabLayout)
    CommonTabLayout tabLayout;
    @BindView(R.id.viewPager)
    NonSwipeableViewPager mViewPager;

    @Inject
    ViewModelFactory viewModelFactory;
    private MainViewModel viewModel;

    private String[] mTitles;
    private int[] mIconUnselectIds;
    private int[] mIconSelectIds;
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private List<Fragment> mFragments = new ArrayList<>();
    private String notificationId = "";
    private ChangeAvatarDialog changeAvatarDialog;
    private List<LocalMedia> listImageVideo = new ArrayList<>();

    private FlycoAdapter flycoAdapter;
    private int amountCart;
    private boolean isCheckPermission = false;
    private BaseAnimatorSet mBasIn, mBasOut;
    protected CompositeDisposable compositeDisposable1, compositeDisposable2;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (Data.isShop) {
            tabLayout.setCurrentTab(2);
            mViewPager.setCurrentItem(2);
            Data.isShop = false;
        }
        if (Data.isOrderList) {
            tabLayout.setCurrentTab(2);
            mViewPager.setCurrentItem(2);
            Data.isOrderList = false;
//            OrderListActivity.startActivity(this, "1234");
        }

        if (Data.isFindGroup) {
            tabLayout.setCurrentTab(1);
            mViewPager.setCurrentItem(1);
            RxBus.getInstance().post(new ChangeCommunityTabEvent(1));
        }
    }

    //    show loading screen ---------
    @Override
    public void initDatas() {
        appComponent().doInjection(this);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel.class);
        ProcessLifecycleOwner.get().getLifecycle().addObserver(this);//

        StringUtils.getHashKey(this);
        onRefreshing();
    }

    //    show error screen -------------
    @Override
    public void showError() {
//        isErrorData = true;
//        showErrorScreen(rootView);
    }

    //    onRefreshing data ------------
    @Override
    protected void onRefreshing() {
        initMarginTopToolbar(false);

    }

    @OnClick(R.id.btnHomeMore)
    void btnHomeMore() {
        ServiceDetailActivity.startActivity(this, "123");
    }

    private int countUpdateDialog = 0;

    public void showUpdateVersionList(List<UpdateVersionBean> updateListResult) {
        int versionApi = Integer.parseInt(updateListResult.get(0).getVersion());
        if (versionApi > BuildConfig.VERSION_CODE) {
            Disposable disposable = Observable.just(0).delay(1500, TimeUnit.MILLISECONDS)
                    .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                            (beans) -> {
                                CheckUpdatePopupDialog popup = new CheckUpdatePopupDialog(MainActivity.this, updateListResult.get(0).getContent(), updateListResult.get(0).getName());
                                popup.setLink(updateListResult.get(0).getLink());
                                popup.showPopupWindow();
                            }
                            ,
                            (e) -> {
                            }
                    );
            addDisposable(disposable);
        }
    }

    //    click loading screen -----------
    @Override
    public void onSkeletonViewClick(View view) {
        switch (view.getId()) {
            case R.id.page_tip_eventview:
                onRefreshing();
                break;
        }
    }

    @Override
    public void configViews() {
        initRxBusListener();
        isCheckPermission = SharedPreferencesUtil.getInstance().getBoolean("isCheckPermission", false);
        if (!isCheckPermission) {
            PermissionUtils.requestRxPermission(rxPermissions, this);
            SharedPreferencesUtil.getInstance().putBoolean("isCheckPermission", true);
        }
        mTitles = getResources().getStringArray(R.array.bottom_layout_icon_titles);
        mIconUnselectIds = new int[]{
                R.drawable.home_new_n,
                R.drawable.home_shop_n,
                R.drawable.empty_drawable,
                R.drawable.home_msg_n,
                R.drawable.home_me_n};
        mIconSelectIds = new int[]{
                R.drawable.home_new_d,
                R.drawable.home_shop_d,
                R.drawable.empty_drawable,
                R.drawable.home_msg_d,
                R.drawable.home_me_d};
        initBottomBar();
    }

    private Intent intent;
    private String title = "";
    private String content = "";
    private String targetId = "";
    private String image = "";
    private String status = "";
    private String subCategoryId = "";
    private String url = "";
    private String type = "";
    private String route = "";
    private boolean musicNotification =  false;

    private void checkExistNotification() {
        tabLayout.showDot(3);
        int tabDotLeftPadding = - getResources().getDimensionPixelSize(R.dimen.length_12);
        int tabDotBottomPadding = getResources().getDimensionPixelSize(R.dimen.length_3);
        tabLayout.setMsgMargin(3, tabDotLeftPadding, tabDotBottomPadding);

        route = getIntent().getStringExtra("route");
        if (route != null && !route.equals("")) {
            title = getIntent().getStringExtra("title");
            content = getIntent().getStringExtra("content");
            targetId = getIntent().getStringExtra("targetId");
            url = getIntent().getStringExtra("url");
            notificationId = getIntent().getStringExtra("notificationId");
            subCategoryId = getIntent().getStringExtra("subCategoryId");
            type = getIntent().getStringExtra("type");
            image = getIntent().getStringExtra("image");
            status = getIntent().getStringExtra("status");

            ARouter.getInstance()
                    .build(route)
                    .withString("title", title)
                    .withString("content", content)
                    .withString("targetId", targetId)
                    .withString("notificationId", notificationId)
                    .withString("url", url)
                    .withString("type", type)
                    .withString("image", image)
                    .withString("status", status)
                    .navigation();

            route = null;
        } else {
        }
    }

    private void initRxBusListener() {
        Disposable disposable = RxBus.getInstance()
                .toObservable(RefreshMainViewPagerEvent.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        (event) -> {
                            mViewPager.getAdapter().notifyDataSetChanged();
                        }
                );
        addDisposable(disposable);

        Disposable disposable2 = RxBus.getInstance()
                .toObservable(ChangeAvatarActivityEvent.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        (event) -> {
//                            PictureSelectorUtils.selectPictureOrImageInsideActivity(this, "image", "single", false, listImageVideo);
                        }
                );

        addDisposable(disposable2);
    }

    private void initBottomBar() {
        mFragments.clear();
        mFragments.add(new ShopV2Fragment());
        mFragments.add(new ServiceFragment());
        mFragments.add(new EmptyFragment());
        mFragments.add(new NotificationFragment());
        mFragments.add(new UserV2Fragment());
//        mFragments.add(new LoginFragment());

        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        flycoAdapter = new FlycoAdapter(getSupportFragmentManager(), mFragments, mTitles);
        mViewPager.setOffscreenPageLimit(4);
        mViewPager.disableScroll(true);
        mViewPager.setAdapter(flycoAdapter);
        tabLayout.setTabData(mTabEntities);
//        mTabLayout.setTabData(mTabEntities, this, R.targetId.fl_change, mFragments);
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                if (position!=2) {
                    mViewPager.setCurrentItem(position);
                }

            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        checkExistNotification();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ProcessLifecycleOwner.get().getLifecycle().removeObserver(this);
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            finish();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Bấm back một lần nữa để thoát ứng dụng", Toast.LENGTH_SHORT).show();
        Disposable disposable = Observable.just(0).delay(2000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                        (beans) -> {
                            doubleBackToExitPressedOnce = false;
                        }
                        ,
                        (e) -> {
                        }
                );
        addDisposable(disposable);
    }

    @Override
    public void onBottomSettingClick(int position, Object customView) {
        switch (position) {
            case 1:
                RxBus.getInstance().post(new ChangeAvatarEvent(listImageVideo.get(0).getPath()));
                break;
            case 0:
                listImageVideo.clear();
                break;
        }
        changeAvatarDialog.dismiss();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    listImageVideo = PictureSelector.obtainMultipleResult(data);
                    if (listImageVideo.size() > 0) {
                        String videoPath = listImageVideo.get(0).getPath();
//                        PictureSelectorUtils.startCrop(this, videoPath);
                    }
                    break;
                case UCrop.REQUEST_CROP:
                    Uri resultUri = UCrop.getOutput(data);
                    String path = StringUtils.getRealPathFromURI(this, resultUri);
                    listImageVideo.get(0).setPath(path);
                    changeAvatarDialog = new ChangeAvatarDialog(this, null, path, this);
                    changeAvatarDialog.showAnim(mBasIn)
                            .dismissAnim(mBasOut)
                            .show();
                    break;
            }

        }
    }

    @Override
    public void complete() {

    }

}
