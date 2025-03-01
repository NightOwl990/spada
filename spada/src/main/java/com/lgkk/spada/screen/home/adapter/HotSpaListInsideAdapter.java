package com.lgkk.spada.screen.home.adapter;

import android.app.Activity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lgkk.base_project.widget.roundedimageview.RoundedImageView;
import com.lgkk.spada.R;
import com.lgkk.spada.model.SpaDetail;
import com.lgkk.spada.screen.home.activity.HospitalDetailActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

class HotSpaListInsideAdapter extends BaseQuickAdapter<SpaDetail, BaseViewHolder> {

    List<SpaDetail> list;
    @BindView(R.id.imgSpa)
    RoundedImageView imgSpa;
    @BindView(R.id.txtName)
    TextView txtName;
    @BindView(R.id.mainRvView)
    RelativeLayout mainRvView;
    private Activity activity;

    public HotSpaListInsideAdapter(Activity activity, List<SpaDetail> data) {
        super(R.layout.item_spa_inside, data);

        this.list = data;
        this.activity = activity;
    }

    @Override
    protected void convert(BaseViewHolder holder, SpaDetail item) {
        ButterKnife.bind(this, holder.itemView);

        mainRvView.setOnClickListener(v -> {
            HospitalDetailActivity.startActivity(activity, item.getId());
        });
    }
}

