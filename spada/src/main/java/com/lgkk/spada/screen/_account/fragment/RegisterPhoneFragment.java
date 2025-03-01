package com.lgkk.spada.screen._account.fragment;


import androidx.lifecycle.ViewModelProviders;
import androidx.fragment.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.lgkk.base_project.base.listener.OnItemRvClickListener;
import com.lgkk.base_project.utils.RxBus;
import com.lgkk.spada.R;
import com.lgkk.spada.base.BaseSpadaFragment;
import com.lgkk.spada.base.ViewModelFactory;
import com.lgkk.spada.event.EventManager;
import com.lgkk.spada.screen._account.viewmodel.RegisterViewModel;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterPhoneFragment extends BaseSpadaFragment implements OnItemRvClickListener<Object> {

    @BindView(R.id.rootView)
    LinearLayout rootView;

    @Inject
    ViewModelFactory viewModelFactory;
    @BindView(R.id.txtCountryCode)
    TextView txtCountryCode;
    @BindView(R.id.down_arrow)
    ImageView downArrow;
    @BindView(R.id.edtPhoneNumber)
    EditText edtPhoneNumber;
    @BindView(R.id.btnGetSmsCode)
    Button btnGetSmsCode;
    @BindView(R.id.edtVerifyCode)
    EditText edtVerifyCode;
    @BindView(R.id.btnRegister)
    TextView btnRegister;
    Unbinder unbinder;
    private RegisterViewModel viewModel;


    private String statusType;
    private static final String STATUS_TYPE = "status";

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_register_phone;
    }


    @Override
    public void attachView() {

    }

    //    show loading screen ---------
    @Override
    public void initDatas() {
        appComponent().doInjection(this);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(RegisterViewModel.class);
        observableViewModel();

        showLoadingScreen(rootView);
        onRefreshing();
    }

    //    Setting view model ------
    private void observableViewModel() {
//        viewModel.getProductDetail().observe(this, product -> {
////            showProduct(product);
//        });

        viewModel.getError().observe(this, isError -> {
            if (isError != null && isError) {
                showError();
            }
        });
    }

    //    onRefreshing data ------------
    protected void onRefreshing() {
        if (isErrorData) {
            showLoadingScreen(rootView);
        }
        isRefreshing = true;

//        viewModel.loadProduct(token, targetId);
    }

    @Override
    public void configViews() {
        closeLoadingScreen();
    }

    @OnClick(R.id.btnRegister)
    void onBack() {
        RxBus.getInstance().post(new EventManager.StepChooseEvent("next"));
    }

    @Override
    public void onItemRvClick(View view, Object item, int adapterPosition) {

    }


    //    show error screen -------------
    public void showError() {
        if (isRefreshing) {
            isErrorData = true;
            showErrorScreen(rootView);
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
}


