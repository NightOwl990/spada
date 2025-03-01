package com.lgkk.base_project.widget;

import com.lgkk.base_project.R;
import com.chad.library.adapter.base.loadmore.LoadMoreView;

public class CustomLoadMoreView extends LoadMoreView {

    @Override
    public int getLayoutId() {
        return R.layout.common_view_loadmore;
    }

    @Override
    public boolean isLoadEndGone() {
        return false;
    }


    @Override
    protected int getLoadingViewId() {
        return R.id.load_more_loading_view;
    }

    @Override
    protected int getLoadFailViewId() {

//        return R.id.load_more_load_fail_view;
        return R.id.load_more_load_fail_view;
    }

    @Override
    protected int getLoadEndViewId() {
        return R.id.load_more_load_end_view;
    }
}
