package com.lgkk.spada.screen.service.adapter;

import android.app.Activity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lgkk.base_project.base.Constants;
import com.lgkk.base_project.base.listener.OnItemRvClickListener;
import com.lgkk.base_project.utils.ImageUtils;
import com.lgkk.base_project.utils.RxBus;
import com.lgkk.base_project.utils.StringUtils;
import com.lgkk.base_project.widget.roundedimageview.RoundedImageView;
import com.lgkk.spada.R;
import com.lgkk.spada.event.ChangeProductAmountEvent;
import com.lgkk.spada.event.CheckboxCartEvent;
import com.lgkk.spada.event.CheckboxProductEvent;
import com.lgkk.spada.model.shop.ProductSection;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CartAdapter extends BaseSectionQuickAdapter<ProductSection, BaseViewHolder> {

    public List<ProductSection> list;
    Activity activity;
    OnItemRvClickListener onItemRvClickListener;
    @BindView(R.id.cbProduct)
    CheckBox cbProduct;
    @BindView(R.id.lnCbProduct)
    LinearLayout lnCbProduct;
    @BindView(R.id.imgService)
    RoundedImageView imgService;
    @BindView(R.id.flService)
    FrameLayout flService;
    @BindView(R.id.txtSaleValid)
    TextView txtSaleValid;
    @BindView(R.id.txtSaleLimit)
    TextView txtSaleLimit;
    @BindView(R.id.txtName)
    TextView txtName;
    @BindView(R.id.txtDescription)
    TextView txtDescription;
    @BindView(R.id.txtTruePrice)
    TextView txtTruePrice;
    @BindView(R.id.lnTruePrice)
    LinearLayout lnTruePrice;
    @BindView(R.id.txtDiscountPercent)
    TextView txtDiscountPercent;
    @BindView(R.id.txtPrice)
    TextView txtPrice;
    @BindView(R.id.btnDelete)
    ImageView btnDelete;
    @BindView(R.id.imgPromote)
    ImageView imgPromote;
    @BindView(R.id.txtTitlePromote)
    TextView txtTitlePromote;
    @BindView(R.id.txtPromote)
    TextView txtPromote;
    @BindView(R.id.lnPromote)
    LinearLayout lnPromote;
    @BindView(R.id.imgFreeShip)
    TextView imgFreeShip;
    @BindView(R.id.txtTitleFreeship)
    TextView txtTitleFreeship;
    @BindView(R.id.tv_fenqi)
    TextView tvFenqi;
    @BindView(R.id.lnFreeship)
    LinearLayout lnFreeship;
    @BindView(R.id.btnDecrease)
    ImageView btnDecrease;
    @BindView(R.id.txtAmount)
    TextView txtAmount;
    @BindView(R.id.btnIncrease)
    ImageView btnIncrease;
    @BindView(R.id.txtPriceTotal)
    TextView txtPriceTotal;
    @BindView(R.id.txtToPayTotal)
    TextView txtToPayTotal;
    @BindView(R.id.lastDiv)
    View lastDiv;
    @BindView(R.id.rootItemView)
    LinearLayout rootItemView;

    public CartAdapter(Activity activity, List<ProductSection> data, OnItemRvClickListener onItemRvClickListener) {
        super(R.layout.item_cart, R.layout.item_header_cart, data);
        this.onItemRvClickListener = onItemRvClickListener;
        this.list = data;
        this.activity = activity;
    }

    private CheckBox cbShop;
    private LinearLayout lnCbShop;
    private TextView txtShopName;

    @Override
    protected void convertHead(BaseViewHolder holder, ProductSection item) {
//        Setting View ----------------
        txtShopName = holder.getView(R.id.txtShopName);
        cbShop = holder.getView(R.id.cbShop);
        lnCbShop = holder.getView(R.id.lnCbShop);

//        Event -----------------------
        lnCbShop.setOnClickListener(v -> onItemRvClickListener.onItemRvClick(v, item, holder.getAdapterPosition()));
        cbShop.setChecked(item.isCheckedShop());
        holder.setText(R.id.txtShopName, item.header);
        cbShop.setOnClickListener(v -> {
            CheckBox cb = (CheckBox) v;
            RxBus.getInstance().post(new CheckboxCartEvent(holder.getAdapterPosition(), cb.isChecked()));
        });
    }

    @Override
    protected void convert(BaseViewHolder holder, ProductSection item) {
        //        Config View -----------------------
        ButterKnife.bind(this, holder.itemView);

//        Setting Data ------------------------
//        holder.setGone(R.id.lnFreeship, item.t.getFreeShip());
//        lnCbProduct.setOnClickListener(v -> onItemRvClickListener.onItemRvClick(v, item.t, holder.getAdapterPosition()));
//
//        txtAmount.setText(item.t.getOrderAmount() + "");
//        txtName.setText(item.t.getName());
//        if (item.t.getDescription().length() > 0) {
//            txtDescription.setText(item.t.getDescription());
//        } else {
//            txtDescription.setVisibility(View.GONE);
//        }
//        long discountPrice = item.t.getPrice() * (100 - item.t.getDiscountPercent()) / 100;
//        txtPrice.setText(StringUtils.formatPrice(item.t.getPrice() + ""));
//        if (item.t.getDiscountPercent() > 0) {
//            txtDiscountPercent.setText("-" + item.t.getDiscountPercent() + "%");
//            txtDiscountPercent.setVisibility(View.VISIBLE);
//            lnTruePrice.setVisibility(View.VISIBLE);
//        } else {
//            txtDiscountPercent.setVisibility(View.GONE);
//            lnTruePrice.setVisibility(View.GONE);
//        }
//        cbProduct.setChecked(item.t.isCheckedProduct());
//
//
//        ImageUtils.loadImageByGlide(activity, item.t.getAvatar(), imgService);
//
//        btnDecrease.setOnClickListener(v -> {
//            if (item.t.getOrderAmount() > 1) {
//
//                item.t.setOrderAmount(item.t.getOrderAmount() - 1);
////                Completable.fromAction(() -> mRealmHelper.insertProduct(item.t)).subscribeOn(Schedulers.io())
////                        .subscribe();
//                RxBus.getInstance().post(new ChangeProductAmountEvent(item.t.getOrderAmount(), "decrease", holder.getAdapterPosition(), item.t.isCheckedProduct()));
//                notifyDataSetChanged();
//            }
//        });
//
//        cbProduct.setOnClickListener(v -> {
//            CheckBox cb = (CheckBox) v;
//            RxBus.getInstance().post(new CheckboxProductEvent(holder.getAdapterPosition(), cb.isChecked()));
//        });
//
//        btnIncrease.setOnClickListener(v -> {
//            if (item.t.getOrderAmount() < Constants.MAX_QUANTITY_PRODUCT) {
//
//                item.t.setOrderAmount(item.t.getOrderAmount() + 1);
////                Completable.fromAction(() -> mRealmHelper.insertProduct(item.t)).subscribeOn(Schedulers.io())
////                        .subscribe();
//                RxBus.getInstance().post(new ChangeProductAmountEvent(item.t.getOrderAmount(), "increase", holder.getAdapterPosition(), item.t.isCheckedProduct()));
//                notifyDataSetChanged();
//            }
//        });

    }

}

