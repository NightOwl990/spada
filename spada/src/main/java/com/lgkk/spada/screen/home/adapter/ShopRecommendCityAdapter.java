package com.lgkk.spada.screen.home.adapter;

import android.app.Activity;
import android.widget.TextView;

import com.lgkk.base_project.utils.ImageUtils;
import com.lgkk.spada.R;
import com.lgkk.spada.model.RecommendCity;
import com.lgkk.base_project.widget.roundedimageview.RoundedImageView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShopRecommendCityAdapter extends BaseQuickAdapter<RecommendCity, BaseViewHolder> {

    List<RecommendCity> list;
    @BindView(R.id.imgBackground)
    RoundedImageView imgBackground;
    @BindView(R.id.txtDescription)
    TextView txtDescription;
    @BindView(R.id.txtCity)
    TextView txtCity;
    @BindView(R.id.txtSpaAndDoctorCount)
    TextView txtSpaAndDoctorCount;
    private Activity activity;

    public ShopRecommendCityAdapter(Activity activity, List<RecommendCity> data) {
        super(R.layout.item_shop_recommend_city, data);
        this.list = data;
        this.activity = activity;
    }

    @Override
    protected void convert(BaseViewHolder holder, RecommendCity item) {
        ButterKnife.bind(this, holder.itemView);

        ImageUtils.loadImageByGlide(activity, item.getImage(), imgBackground);
        txtDescription.setText(item.getDescription());
        txtCity.setText(item.getCity());
        txtSpaAndDoctorCount.setText(String.format(activity.getResources().getString(R.string.spa_and_doctor_count), item.getSpaCount(), item.getDoctorCount()));
    }
}
