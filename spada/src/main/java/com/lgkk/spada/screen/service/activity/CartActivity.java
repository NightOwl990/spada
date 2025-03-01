package com.lgkk.spada.screen.service.activity;

import androidx.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lgkk.base_project.base.listener.OnItemRvClickListener;
import com.lgkk.base_project.utils.RxBus;
import com.lgkk.base_project.utils.StringUtils;
import com.lgkk.base_project.widget.CustomFragmentHeader;
import com.lgkk.base_project.widget.FlipVerticalSwingEnterDialog.BaseAnimatorSet;
import com.lgkk.base_project.widget.animationstyle.SlideBottomEnter;
import com.lgkk.base_project.widget.animationstyle.SlideBottomExit;
import com.lgkk.base_project.widget.dialog.NormalDialog;
import com.lgkk.spada.R;
import com.lgkk.spada.base.BaseSpadaActivity;
import com.lgkk.spada.base.ViewModelFactory;
import com.lgkk.spada.event.ChangeProductAmountEvent;
import com.lgkk.spada.event.CheckboxCartEvent;
import com.lgkk.spada.event.CheckboxProductEvent;
import com.lgkk.spada.model.shop.Cart;
import com.lgkk.spada.model.shop.Product;
import com.lgkk.spada.model.shop.ProductSection;
import com.lgkk.spada.screen.service.adapter.CartAdapter;
import com.lgkk.spada.screen.service.viewmodel.CartViewModel;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class CartActivity extends BaseSpadaActivity implements OnItemRvClickListener<Object> {


    @BindView(R.id.rvCart)
    RecyclerView rvCart;
    @BindView(R.id.rvRecommend)
    RecyclerView rvRecommend;
    @BindView(R.id.rootView)
    LinearLayout rootView;
    @BindView(R.id.nestedScrollView)
    NestedScrollView nestedScrollView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.btnBack)
    ImageView btnBack;
    @BindView(R.id.btnDelete)
    TextView btnDelete;
    @BindView(R.id.btnFinish)
    TextView btnFinish;
    @BindView(R.id.toolBar)
    RelativeLayout toolBar;
    @BindView(R.id.txtTip)
    TextView txtTip;
    @BindView(R.id.cbAll)
    CheckBox cbAll;
    @BindView(R.id.btnCheckAll)
    LinearLayout btnCheckAll;
    @BindView(R.id.textd)
    TextView textd;
    @BindView(R.id.txtTotalPrice)
    TextView txtTotalPrice;
    @BindView(R.id.txtSaveDeliver)
    TextView txtSaveDeliver;
    @BindView(R.id.rlTotalPrice)
    RelativeLayout rlTotalPrice;
    @BindView(R.id.btnSubmit)
    TextView btnSubmit;
    @BindView(R.id.btnSubmitDelete)
    TextView btnSubmitDelete;
    @BindView(R.id.lnDelete)
    LinearLayout lnDelete;
    @BindView(R.id.lnBottomBar)
    LinearLayout lnBottomBar;
    private Button btnCartGoHome;
    private View emptyView;

    private CartAdapter cartAdapter;
    private List<Product> cartProductList = new ArrayList<>();
    private List<ProductSection> cartProductSectionList = new ArrayList<>();

    private String productId = "";
    private long totalPrice = 0;

    private BaseAnimatorSet mBasIn;
    private BaseAnimatorSet mBasOut;

    @Inject
    ViewModelFactory viewModelFactory;
    private CartViewModel viewModel;


    @Override
    public int getLayoutId() {
        return R.layout.activity_cart;
    }

    private static final String PRODUCT_ID = "targetId";

    public static void startActivity(Context context, String productId) {
        Intent intent = new Intent(context, CartActivity.class);
        intent.putExtra(PRODUCT_ID, productId);
        context.startActivity(intent);
    }

    @Override
    protected void initDataGetFromIntent(Bundle savedInstanceState) {
        super.initDataGetFromIntent(savedInstanceState);
        if (savedInstanceState != null) {
            productId = savedInstanceState.getString(PRODUCT_ID);
        } else {
            productId = getIntent().getStringExtra(PRODUCT_ID);
        }
    }

    @Override
    public void initDatas() {
        appComponent().doInjection(this);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(CartViewModel.class);
        initMarginTopToolbar(true);
        showTip = false;
        observableViewModel();
        showLoadingScreen(rootView);
        onRefreshing();

    }

    private void observableViewModel() {
        viewModel.getListCartService().observe(this, result -> {
            showListCartService(result);
        });

        viewModel.getError().observe(this, isError -> {
            if (isError != null && isError) {
                showError();
            }
        });
    }

    private void showListCartService(List<Cart> cartListResult) {
        cartProductList.clear();
        //TODO : Delete here
        cartProductSectionList.clear();
        cartProductSectionList.add(new ProductSection(true, "Shop A"));
        cartProductSectionList.add(new ProductSection(new Product(), 1));
        cartProductSectionList.add(new ProductSection(true, "Shop B"));
        cartProductSectionList.add(new ProductSection(new Product(), 2));
        cartProductSectionList.add(new ProductSection(true, "Shop C"));
        cartProductSectionList.add(new ProductSection(new Product(), 3));
        cartAdapter.notifyDataSetChanged();
//        for (int i=0;i<cartListResult.size();i++) {
//            Cart cart = cartListResult.get(i);
//            Product product = cartListResult.get(i).getProduct();
//            product.setCartId(cart.getId());
//            product.setOrderAmount(cart.getQuantity());
//            cartProductList.add(product);
//        }
//        if (cartListResult.size() > 0) {
//            String currentShopId = cartProductList.get(0).getShop().getId();
//            cartProductSectionList.clear();
//            currentPositionShop = 0;
//            productSection = new ProductSection(true, cartProductList.get(0).getShop().getName());
//            cartProductSectionList.add(productSection);
//            while (cartProductList.size() > 0) {
//                for (int i = 0; i < cartProductList.size(); i++) {
//                    if (cartProductList.get(i).getShop().getId().equals(currentShopId)) {
//                        productSection = new ProductSection(cartProductList.get(i), currentPositionShop);
//                        cartProductSectionList.add(productSection);
//                        cartProductSectionList.get(currentPositionShop).setCheckedShop(true);
//                        cartProductList.remove(i);
//                        i--;
//                    }
//                }
//                cartProductSectionList.get(cartProductSectionList.size() - 1).setEnd(true);
//                if (cartProductList.size() > 0) {
//                    currentShopId = cartProductList.get(0).getShop().getId();
//                    currentPositionShop = cartProductSectionList.size();
//                    cartProductSectionList.add(new ProductSection(true, cartProductList.get(0).getShop().getName()));
//                }
//            }
//            calculateTotalPrice();
//            checkAllProduct();
//            cartAdapter.notifyDataSetChanged();
//        }
//
//        checkedSizeCart(false);

        //   Close loading screen ------------------
        closeLoadingScreen();
    }

    @Override
    protected void onRefreshing() {
        if (isErrorData) {
            showLoadingScreen(rootView);
        }

        viewModel.getListCartService(token);
    }


    @Override
    public void configViews() {
//  Setting RefreshLayout -----------------
        refreshLayout.setRefreshHeader(new CustomFragmentHeader(this));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh(1000/*,false*/);
                onRefreshing();
            }
        });

//      Setting Recycler View ---------------

        cartAdapter = new CartAdapter(this, cartProductSectionList, this);
        LinearLayoutManager layoutManagerNews = new LinearLayoutManager(this);
        rvCart.setHasFixedSize(true);
        rvCart.setNestedScrollingEnabled(false);
        rvCart.setLayoutManager(layoutManagerNews);
        rvCart.setAdapter(cartAdapter);
        cartAdapter.setOnItemClickListener((adapter, view, position) -> {
            if (cartProductSectionList.get(position).isHeader) {
            } else {

//                Intent intent = new Intent(CartActivity.this, ProductDetailRatingActivity.class);
//                intent.putExtra("targetId", cartProductSectionList.get(position).t.getId());
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(intent);
            }
        });
        //   Set EmptyView ------------------------
        emptyView = getLayoutInflater().inflate(R.layout.layout_empty_cart_view, (ViewGroup) rvCart.getParent(), false);
        cartAdapter.setEmptyView(emptyView);
        btnCartGoHome = emptyView.findViewById(R.id.btnCartGoHome);
        btnCartGoHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //                Intent intent = new Intent(CartActivity.this, MainActivity.class);
                //                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                //                startActivity(intent);
                finish();
            }
        });

//        Event Checkbox  ----------------------------------
        Disposable disposable = RxBus.getInstance()
                .toObservable(CheckboxCartEvent.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        (event) -> {
                            cartProductSectionList.get(event.adapterPosition).setCheckedShop(event.isChecked);
                            for (int i = event.adapterPosition + 1; i < cartProductSectionList.size(); i++) {
                                if (cartProductSectionList.get(i).isHeader) break;
                                cartProductSectionList.get(i).t.setCheckedProduct(event.isChecked);
                                int finalI = i;
//                                Completable.fromAction(() -> mRealmHelper.insertProduct(cartProductSectionList.get(finalI).t)).subscribeOn(Schedulers.io())
//                                        .subscribe();
                            }
                            calculateTotalPrice();
                            checkAllProduct();
                            cartAdapter.notifyDataSetChanged();
                        }
                );
        addDisposable(disposable);

        Disposable disposable1 = RxBus.getInstance()
                .toObservable(CheckboxProductEvent.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        (event) -> {
                            Product product = cartProductSectionList.get(event.adapterPosition).t;
                            product.setCheckedProduct(event.isChecked);
//
//                            Completable.fromAction(() -> mRealmHelper.insertProduct(cartProductSectionList.get(event.adapterPosition).t)).subscribeOn(Schedulers.io())
//                                    .subscribe();
                            calculateTotalPrice();
                            int positionShop = cartProductSectionList.get(event.adapterPosition).getPositionShop();

                            if (!event.isChecked) {
                                cartProductSectionList.get(positionShop).setCheckedShop(false);
                                cartAdapter.notifyDataSetChanged();
                            } else {
                                boolean checkedShop = true;
                                for (int i = positionShop + 1; i < cartProductSectionList.size(); i++) {
                                    if (!cartProductSectionList.get(i).isHeader) {
                                        if (!cartProductSectionList.get(i).t.isCheckedProduct()) {
                                            checkedShop = false;
                                            break;
                                        }
                                    } else break;
                                }
                                cartProductSectionList.get(positionShop).setCheckedShop(checkedShop);
                            }
                            checkAllProduct();
                            cartAdapter.notifyDataSetChanged();
                        }

                );
        addDisposable(disposable1);

        Disposable disposable3 = RxBus.getInstance()
                .toObservable(ChangeProductAmountEvent.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        (event) -> {
                            if (event.formula.equals("decrease") || event.formula.equals("increase")) {
//                                mPresenter.addProductToCart(token, cartProductSectionList.get(event.adapterPosition).t.getId(), event.amountProduct, event.isCheck);
                                calculateTotalPrice();
                            }
                        }
                );
        addDisposable(disposable3);


        cbAll.setOnClickListener(v -> {
            CheckBox cb = (CheckBox) v;
            for (ProductSection productSection : cartProductSectionList) {
                if (productSection.isHeader) {
                    productSection.setCheckedShop(cb.isChecked());
                } else {
                    productSection.t.setCheckedProduct(cb.isChecked());
//                    Completable.fromAction(() -> mRealmHelper.insertProduct(productSection.t)).subscribeOn(Schedulers.io())
//                            .subscribe();
                }
                calculateTotalPrice();
                cartAdapter.notifyDataSetChanged();
            }
        });
    }

    @OnClick(R.id.btnCheckAll)
    public void setCheckAll() {
        cbAll.performClick();
    }


    public void checkAllProduct() {
        boolean isCheckAll = true;
        for (int i = 0; i < cartProductSectionList.size(); i++) {
            if (!cartProductSectionList.get(i).isHeader) {
                if (!cartProductSectionList.get(i).t.isCheckedProduct()) {
                    isCheckAll = false;
                    break;
                }
            } else {
                if (!cartProductSectionList.get(i).isCheckedShop()) {
                    isCheckAll = false;
                    break;
                }
            }
        }
        cbAll.setChecked(isCheckAll);
    }

    public void calculateTotalPrice() {
        totalPrice = 0;
        for (int i = 0; i < cartProductSectionList.size(); i++) {
            if (!cartProductSectionList.get(i).isHeader) {
                if (cartProductSectionList.get(i).t.isCheckedProduct()) {
                    long discountPrice = cartProductSectionList.get(i).t.getPrice() * (100 - cartProductSectionList.get(i).t.getDiscountPercent()) / 100;
                    totalPrice = totalPrice + discountPrice * cartProductSectionList.get(i).t.getOrderAmount();
                }
            }
        }
        txtTotalPrice.setText(StringUtils.formatPrice(totalPrice + ""));
    }

    @OnClick(R.id.btnSubmitDelete)
    public void onSubmitDelete() {
        boolean isCheckAtLeast = false;
        for (int i = 0; i < cartProductSectionList.size(); i++) {
            if (!cartProductSectionList.get(i).isHeader && cartProductSectionList.get(i).t.isCheckedProduct()) {
                isCheckAtLeast = true;
                break;
            }
        }
        if (isCheckAtLeast) {
            mBasIn = new SlideBottomEnter();
            mBasOut = new SlideBottomExit();
            final NormalDialog dialog = new NormalDialog(this);
            dialog.setmBtnMiddleText("Xóa");
            dialog.setmBtnRightText("Xóa");
            dialog.content("Bạn có muốn xóa sản phẩm đang chọn ?")//
                    .style(NormalDialog.STYLE_TWO)//
                    .titleTextSize(20)//
                    .showAnim(mBasIn)//
                    .dismissAnim(mBasOut)//
                    .show();

            dialog.setOnBtnClickL(
                    () -> dialog.dismiss(),
                    () -> {
                        deleteCheckedProduct();
                        dialog.dismiss();
                    });
        } else {
            showTipViewAndDelayClose("Vui lòng chọn ít nhất 1 sản phẩm", txtTip);
        }
    }

    private void deleteCheckedProduct() {
        for (int i = 0; i < cartProductSectionList.size(); i++) {
            ProductSection productSection = cartProductSectionList.get(i);
            if (productSection.isHeader) {
                if (productSection.isCheckedShop()) {
                    cartProductSectionList.remove(i);
                    i--;
                }

            } else {
                if (productSection.t.isCheckedProduct()) {
//                    mPresenter.removeOneProductOnCartByCartId(token, productSection.t.getCartId());
                    cartProductSectionList.remove(i);
                    i--;
                }
            }
        }
        checkedSizeCart(true);
        calculateTotalPrice();
        cartAdapter.notifyDataSetChanged();
    }

    private void checkedSizeCart(boolean isDelete) {
        if (cartProductSectionList.size() > 0) {
            lnBottomBar.setVisibility(View.VISIBLE);
            btnDelete.setVisibility(View.VISIBLE);
            if (isDelete) {
                btnDelete.setVisibility(View.GONE);
                btnFinish.setVisibility(View.VISIBLE);
            } else {
                btnDelete.setVisibility(View.VISIBLE);
                btnFinish.setVisibility(View.GONE);
            }
        } else {
            lnBottomBar.setVisibility(View.GONE);
            btnDelete.setVisibility(View.GONE);
            btnFinish.setVisibility(View.GONE);
        }
    }

    @OnClick(R.id.btnBack)
    public void onBack() {
        onBackPressed();
    }

    @OnClick(R.id.btnSubmit)
    public void onSubmit() {
        boolean checkProduct = false;
        List<Product> checkedProductList = new ArrayList<>();
        for (int i = 0; i < cartProductSectionList.size(); i++) {
            if (!cartProductSectionList.get(i).isHeader && cartProductSectionList.get(i).t.isCheckedProduct()) {
                checkProduct = true;
                break;
            }
        }
        if (checkProduct) {
//            mPresenter.addListProductToCart(token, cartProductSectionList);
        } else {
            showTipViewAndDelayClose("Vui lòng chọn ít nhất 1 sản phẩm", txtTip);
        }
    }


    @OnClick(R.id.btnFinish)
    public void onFinish() {
        btnFinish.setVisibility(View.GONE);
        btnDelete.setVisibility(View.VISIBLE);
        btnSubmit.setVisibility(View.VISIBLE);
        rlTotalPrice.setVisibility(View.VISIBLE);
        lnDelete.setVisibility(View.GONE);
    }

    @OnClick(R.id.btnDelete)
    public void onDelete() {
        btnFinish.setVisibility(View.VISIBLE);
        btnDelete.setVisibility(View.GONE);
        btnSubmit.setVisibility(View.GONE);
        rlTotalPrice.setVisibility(View.INVISIBLE);
        lnDelete.setVisibility(View.VISIBLE);
    }

    @Override
    public void onItemRvClick(View view, Object item, int adapterPosition) {
        if (item instanceof Product) {
            switch (view.getId()) {
                case R.id.lnCbProduct:
                    CheckBox cbProduct = rvCart.findViewHolderForAdapterPosition(adapterPosition).itemView.findViewById(R.id.cbProduct);
                    cbProduct.performClick();
                    break;
            }
        } else {
            if (item instanceof ProductSection) {
                switch (view.getId()) {
                    case R.id.lnCbShop:
                        CheckBox cbShop = rvCart.findViewHolderForAdapterPosition(adapterPosition).itemView.findViewById(R.id.cbShop);
                        cbShop.performClick();
                        break;
                }
            }
        }
    }

    //    show error screen -------------
    @Override
    public void showError() {
        isErrorData = true;
        showErrorScreen(rootView);
    }

    @Override
    public void complete() {
    }

    private int currentPositionShop;
    private ProductSection productSection;


//    @Override
//    public void finishAddListProductToCart(Message message) {
//        OrderActivity.startActivity(this, "cart", "", 0);
//    }

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

