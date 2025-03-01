package com.lgkk.spada.screen.home.adapter;


import android.app.Activity;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lgkk.base_project.base.listener.OnItemRvClickListener;
import com.lgkk.spada.R;
import com.lgkk.spada.model.HotSpaDetail;
import com.lgkk.spada.model.SpaDetail;
import com.lgkk.spada.screen.home.activity.FilterHospitalListHandlerActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HotSpaAdapter extends BaseQuickAdapter<HotSpaDetail, BaseViewHolder> {

    public List<HotSpaDetail> list;
    @BindView(R.id.txtLocation)
    TextView txtLocation;
    @BindView(R.id.txtSpaCount)
    TextView txtSpaCount;
    @BindView(R.id.txtDescription)
    TextView txtDescription;
    @BindView(R.id.rvSpaList)
    RecyclerView rvSpaList;
    @BindView(R.id.mainRvView)
    LinearLayout mainRvView;
    private Activity activity;
    OnItemRvClickListener onItemRvClickListener;

    HotSpaListInsideAdapter mAdapter;

    public HotSpaAdapter(Activity activity, List<HotSpaDetail> data, OnItemRvClickListener onItemRvClickListener) {
        super(R.layout.item_hot_spa, data);
        this.onItemRvClickListener = onItemRvClickListener;
        this.list = data;
        this.activity = activity;
    }

    @Override
    protected void convert(BaseViewHolder holder, HotSpaDetail item) {
        ButterKnife.bind(this, holder.itemView);
        List<SpaDetail> spaList = new ArrayList<>();
        spaList.add(new SpaDetail());
        spaList.add(new SpaDetail());
        spaList.add(new SpaDetail());
        mAdapter = new HotSpaListInsideAdapter(activity, spaList);
        rvSpaList.setHasFixedSize(true);
        rvSpaList.setNestedScrollingEnabled(false);
        rvSpaList.setAdapter(mAdapter);

        mainRvView.setOnClickListener(v -> {
            FilterHospitalListHandlerActivity.startActivity(activity, item.getId());
        });
    }
}

