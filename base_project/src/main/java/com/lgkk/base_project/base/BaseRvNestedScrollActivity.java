package com.lgkk.base_project.base;

public abstract class BaseRvNestedScrollActivity<T2> extends BaseActivity {
//
//    protected BaseQuickAdapter<T2, BaseViewHolder> mAdapter;
//    protected RecyclerView mRecyclerView;
//    protected int start = 0;
//    protected int startPosition = 0;
//    protected int limit = Constants.MAX_ITEM_LOAD_FIRST_TIME;
//    protected OnItemRvClickListener onItemRvClickListener;
//    @BindView(R.id.rootView)
//    View rootView;
//
//    protected void initDataRecyclerView(RecyclerView mRecyclerView, BaseQuickAdapter<T2, BaseViewHolder> mAdapter, boolean loadmoreable) {
//        this.mRecyclerView = mRecyclerView;
//        this.mAdapter = mAdapter;
//        initLoadMore(loadmoreable);
//    }
//
//    protected void setStartPosition(int startPosition) {
//        this.startPosition = startPosition;
//    }
//
//
//    public void initLoadMore(boolean loadmoreable) {
//        if (loadmoreable) {
//            mAdapter.setLoadMoreView(new CustomLoadMoreView());
//            mAdapter.setOnLoadMoreListener(() -> {
//                Disposable disposable = Observable.just(0).delay(300, TimeUnit.MILLISECONDS)
//                        .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
//                        (beans) -> {
//                            if (isEndLoading) {
//                                mAdapter.loadMoreEnd();
//                            } else {
//                                if (!isErr) {
//                                    getData(start, limit);
//                                    mAdapter.loadMoreComplete();
//                                    mAdapter.setEnableLoadMore(false);
//                                } else {
//                                    //Get more data failed
//                                    isErr = true;
//                                    mAdapter.loadMoreFail();
//                                }
//                            }
//                        }
//                        ,
//                        (e) -> {
//                        }
//                );
//                addDisposable(disposable);
//
//            }, mRecyclerView);
//        }
//    }
//
//    public abstract void getData(int start, int limit);
//
//
//    protected void onRefreshing() {
//
//        if (isErrorData) {
//            showLoadingScreen(rootView);
//        }
//        if (mAdapter != null) {
//            mAdapter.setLoadMoreView(new CustomLoadMoreView());
//            mAdapter.setEnableLoadMore(false);
//        }
//        start = startPosition;
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
//
//    public void checkingRefreshAndEndData(int size) {
//        if (size <= 0) {
//            isEndLoading = true;
//        }
//        if (isRefreshing) {
//            start = startPosition;
//        }
//    }
}

