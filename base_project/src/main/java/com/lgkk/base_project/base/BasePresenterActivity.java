package com.lgkk.base_project.base;

import android.os.Bundle;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

public abstract class BasePresenterActivity<T extends BaseContract.BasePresenter> extends  BaseActivity implements BaseContract.BaseView {
    @Inject
    protected T mPresenter;
    private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initPresenter();
    }

    private void initPresenter() {
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null) mPresenter.detachView();
        super.onDestroy();
    }
}

