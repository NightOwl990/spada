package com.lgkk.base_project.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.LayoutRes;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lgkk.base_project.R;

import com.lgkk.base_project.base.listener.OnSkeletonViewClickListener;

import com.lgkk.base_project.widget.LoadingDialog;
import com.lgkk.base_project.widget.skeleton.Skeleton;
import com.lgkk.base_project.widget.skeleton.SkeletonScreen;
import com.lgkk.base_project.utils.SharedPreferencesUtil;
import com.lgkk.base_project.utils.TinyDB;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tbruyelle.rxpermissions2.RxPermissions;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BaseFragment extends RxFragment implements OnSkeletonViewClickListener {

    protected String token;
    protected TinyDB tinyDB;
    protected View mRootView;
    protected boolean isRefreshing = false;
    protected boolean isErr = false;
    protected boolean isResume = false;
    protected boolean isEndLoading = false;
    protected int mCurrentCounter;
    protected int limit = Constants.MAX_ITEM_LOAD_FIRST_TIME;
    protected int start = 0;
    protected CompositeDisposable mDisposable;
    protected SkeletonScreen skeletonScreen;
    protected boolean isErrorData = false;

    protected boolean isPrepared;
    protected RxPermissions rxPermissions;
    protected Activity mActivity;
    protected LayoutInflater inflater;
    protected Context mContext;
    protected boolean isVisible;
    private Unbinder unbinder;
    private LoadingDialog dialog;
    private String baseRequestJson;

    public abstract @LayoutRes
    int getLayoutResId();

    @Override
    public void onAttach(Context context) {
        mActivity = (Activity) context;
        mContext = context;
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle state) {
        if (mRootView != null) {
            ViewGroup parent = (ViewGroup) mRootView.getParent();
            if (parent != null)
                parent.removeView(mRootView);

        } else {
            mRootView = inflater.inflate(getLayoutResId(), container, false);
            mActivity = getSupportActivity();
            mContext = mActivity;
            this.inflater = inflater;
        }
        return mRootView;
    }

    @Override
    public void onDestroy() {
        unbinder.unbind();
        if (mDisposable != null) {
            mDisposable.clear();
        }
        super.onDestroy();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rxPermissions = new RxPermissions(getActivity());
        tinyDB = new TinyDB(getContext());
        token = SharedPreferencesUtil.getInstance().getString("nodeToken");
        baseRequestJson = SharedPreferencesUtil.getInstance().getString(Constants.BASE_REQUEST, "{}");
        initDataGetFromArgument(savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        attachView();
        finishCreateView(savedInstanceState);
        initDatas();
        configViews();
    }

    public void finishCreateView(Bundle state) {
        isPrepared = true;
        lazyLoad();
    }

    public void closeLoadingScreen() {
        isErrorData = false;
        if (skeletonScreen != null) {
            skeletonScreen.hide();
        }
    }

    protected void lazyLoad() {
        if (!isPrepared || !isVisible) return;
        lazyLoadData();
        isPrepared = false;
    }

    protected void lazyLoadData() {

    }

    protected void onVisible() {
        lazyLoad();
    }

    /**
     * Fragment数据的懒加载
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    protected void onInvisible() {

    }

    protected void initDataGetFromArgument(Bundle savedInstanceState) {

    }


    public abstract void attachView();

    public abstract void initDatas();

    public abstract void configViews();


    public FragmentActivity getSupportActivity() {
        return super.getActivity();
    }

    public Context getApplicationContext() {
        return this.getActivity() == null ? (getActivity() == null ? null : getActivity()
                .getApplicationContext()) : this.getActivity().getApplicationContext();
    }

    public LoadingDialog getDialog() {
        if (dialog == null) {
            dialog = LoadingDialog.instance(getActivity());
            dialog.setCancelable(false);
        }
        return dialog;
    }

    public void hideDialog() {
        dialog.stopLoadingView();
        if (dialog != null)
            dialog.hide();
    }

    public void showDialog() {
        getDialog().show();
    }

    public void dismissDialog() {
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }

    //view action
    protected void gone(final View... views) {
        if (views != null && views.length > 0) {
            for (View view : views) {
                if (view != null) {
                    view.setVisibility(View.GONE);
                }
            }
        }
    }

    protected void visible(final View... views) {
        if (views != null && views.length > 0) {
            for (View view : views) {
                if (view != null) {
                    view.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    protected boolean isVisible(View view) {
        return view.getVisibility() == View.VISIBLE;
    }


    public void showLoadingScreen(View rootView) {
        if (skeletonScreen != null) {
            skeletonScreen.hide();
        }
        skeletonScreen = Skeleton.bind(rootView)
                .load(R.layout.common_loading_view)
                .duration(1500)
                .color(R.color.shimmer_color_for_image)
                .show();
    }

    public void showErrorScreen(View rootView) {
        if (skeletonScreen != null) {
            skeletonScreen.hide();
        }
        skeletonScreen = Skeleton.bind(rootView)
                .load(R.layout.common_empty_view, this)
                .duration(0)
                .color(R.color.shimmer_color_for_image)
                .show();
    }

    protected void addDisposable(Disposable d) {
        if (mDisposable == null) {
            mDisposable = new CompositeDisposable();
        }
        mDisposable.add(d);
    }

    public void settingLinearRecyclerView(RecyclerView rv, BaseQuickAdapter adapter, boolean isHasFixSize, boolean isEnableNestedScrollView) {
        rv.setHasFixedSize(isHasFixSize);
        rv.setNestedScrollingEnabled(isEnableNestedScrollView);
        LinearLayoutManager lnLayoutManager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(lnLayoutManager);
        rv.setAdapter(adapter);
    }

    public void settingHorizontalLinearRecyclerView(RecyclerView rv, BaseQuickAdapter adapter, boolean isHasFixSize, boolean isEnableNestedScrollView) {
        rv.setHasFixedSize(isHasFixSize);
        rv.setNestedScrollingEnabled(isEnableNestedScrollView);
        LinearLayoutManager lnLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rv.setLayoutManager(lnLayoutManager);
        rv.setAdapter(adapter);
    }

    public void settingGridRecyclerView(RecyclerView rv, BaseQuickAdapter adapter, int spanCount, boolean isHasFixSize, boolean isEnableNestedScrollView) {
        rv.setHasFixedSize(isHasFixSize);
        rv.setNestedScrollingEnabled(isEnableNestedScrollView);
        GridLayoutManager gridlayoutManager = new GridLayoutManager(getContext(), spanCount);
        rv.setLayoutManager(gridlayoutManager);
        rv.setAdapter(adapter);
    }

    public void settingLinearRecyclerViewWithoutAnimation(RecyclerView rv, BaseQuickAdapter adapter, int spanCount, boolean isHasFixSize, boolean isEnableNestedScrollView) {
        rv.setHasFixedSize(isHasFixSize);
        rv.setNestedScrollingEnabled(isEnableNestedScrollView);
        rv.setItemAnimator(null);
        LinearLayoutManager gridlayoutManager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(gridlayoutManager);
        rv.setAdapter(adapter);
    }

    public void settingStaggedGridRecyclerView(RecyclerView rv, BaseQuickAdapter adapter, int spanCount, boolean isHasFixSize, boolean isEnableNestedScrollView) {
        rv.setNestedScrollingEnabled(isEnableNestedScrollView);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(spanCount, LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(staggeredGridLayoutManager);
        rv.setAdapter(adapter);
    }


}