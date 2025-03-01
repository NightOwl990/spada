package com.lgkk.base_project.base;

public interface BaseContract {
    interface BaseView {
        void showError();
        void complete();
    }

    interface BasePresenter<T>  {
        void attachView(T view);
        void detachView();

    }
}
