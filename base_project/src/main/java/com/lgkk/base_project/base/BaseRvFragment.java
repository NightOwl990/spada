package com.lgkk.base_project.base;

public abstract class BaseRvFragment<T2> extends BaseFragment {

//
//    protected RecyclerView mRecyclerView;
//    protected BaseQuickAdapter<T2, BaseViewHolder> mAdapter;
//    protected int start = 0;
//    protected int limit = Constants.MAX_ITEM_LOAD_FIRST_TIME;
//    @BindView(R.id.rootView)
//    View rootView;
//
//    public abstract int getRecyclerViewId();
//
//
//    protected void initAdapter(boolean loadmoreable) {
//        mRecyclerView = mRootView.findViewById(getRecyclerViewId());
//
//        if (mRecyclerView != null) {
//            mRecyclerView.setLayoutManager(new LinearLayoutManager(getSupportActivity()));
//            mRecyclerView.setAdapter(mAdapter);
//        }
//
//        if (mAdapter != null && loadmoreable) {
//            mAdapter.setLoadMoreView(new CustomLoadMoreView());
//            mAdapter.setOnLoadMoreListener(() -> {
//
//                if (isEndLoading) {
//                    mAdapter.loadMoreEnd();
//                } else {
//                    if (!isErr) {
//                        getData(start, limit);
//                        mAdapter.loadMoreComplete();
//                    } else {
//                        //Get more data failed
//                        isErr = true;
//                        mAdapter.loadMoreFail();
//                    }
//                }
//            }, mRecyclerView);
//        }
//    }
//
//    public abstract void getData(int start, int limit);
//
//    protected void initAdapter(Class<? extends BaseQuickAdapter<T2, BaseViewHolder>> clazz, boolean loadmoreable) {
//        mAdapter = (BaseQuickAdapter<T2, BaseViewHolder>) createInstance(clazz);
//        initAdapter(loadmoreable);
//    }
//
//    public Object createInstance(Class<?> cls) {
//        Object obj;
//        try {
//            Constructor c1 = cls.getDeclaredConstructor(Context.class);
//            c1.setAccessible(true);
//            obj = c1.newInstance(this);
//        } catch (Exception e) {
//            obj = null;
//        }
//        return obj;
//    }
//
//    protected void onRefreshing() {
//
//        if (isErrorData) {
//            showLoadingScreen(rootView);
//        }
//        if (mAdapter != null) {
//            mAdapter.setLoadMoreView(new CustomLoadMoreView());
//        }
//        start = 0;
//        isRefreshing = true;
//        isEndLoading = false;
//        getData(start, limit);
//    }
//
//    public void showError() {
//        if (isRefreshing) {
//            isErrorData = true;
//            showErrorScreen(rootView);
//        }
//    }
//
//    @Override
//    public void onSkeletonViewClick(View view) {
//        switch (view.getId()) {
//            case R.id.page_tip_eventview:
//                onRefreshing();
//                break;
//        }
//    }
//
//    public void closeLoadingScreen() {
//        isErrorData = false;
//        if (skeletonScreen != null) {
//            skeletonScreen.hide();
//        }
//    }
}
