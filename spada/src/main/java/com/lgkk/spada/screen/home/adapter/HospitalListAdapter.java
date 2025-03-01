package com.lgkk.spada.screen.home.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lgkk.base_project.widget.CircleImageView;
import com.lgkk.base_project.widget.SimpleEvaluateStarView;
import com.lgkk.base_project.widget.flowlayout.AutoFlowLayout;
import com.lgkk.base_project.widget.flowlayout.FlowAdapter;
import com.lgkk.spada.R;
import com.lgkk.spada.model.FilterTypeDetail;
import com.lgkk.spada.model.HospitalDetail;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HospitalListAdapter extends BaseQuickAdapter<HospitalDetail, BaseViewHolder> {


    @BindView(R.id.imgAvatar)
    CircleImageView imgAvatar;
    @BindView(R.id.txtName)
    TextView txtName;
    @BindView(R.id.iv_award)
    ImageView ivAward;
    @BindView(R.id.tvAwards)
    TextView tvAwards;
    @BindView(R.id.ll_award)
    LinearLayout llAward;
    @BindView(R.id.txtType)
    TextView txtType;
    @BindView(R.id.txtCity)
    TextView txtCity;
    @BindView(R.id.txtAddress)
    TextView txtAddress;
    @BindView(R.id.ratingbar)
    SimpleEvaluateStarView ratingbar;
    @BindView(R.id.txtReservation)
    TextView txtReservation;
    @BindView(R.id.line_anli)
    TextView lineAnli;
    @BindView(R.id.txtCase)
    TextView txtCase;
    @BindView(R.id.txtAppointment)
    TextView txtAppointment;
    @BindView(R.id.flowLayout)
    AutoFlowLayout flowLayout;
    @BindView(R.id.rl_main)
    RelativeLayout rlMain;
    private Context context;
    public List<HospitalDetail> list;


    public HospitalListAdapter(Context context, List<HospitalDetail> data) {
        super(R.layout.item_hospital_list, data);

        this.list = data;
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder holder, HospitalDetail item) {
        ButterKnife.bind(this, holder.itemView);

//        FormatUtils.changeSizeSimpleValuateRatingBar(mContext, ratingbar, 1.5);

        ratingbar.setScore(Float.parseFloat(4.3 + ""));
        ratingbar.setStartWidget(mContext.getResources().getDimensionPixelSize(R.dimen.d_13));
        dataFlow.clear();
        dataFlow.add(new FilterTypeDetail("1", "Sửa mũi 666 ca", "nose"));
        dataFlow.add(new FilterTypeDetail("2", "Sửa cằm 656 ca", "face"));
        dataFlow.add(new FilterTypeDetail("3", "Mát xa 1466 ca", "massage"));
        initSetTag();
    }

    private FlowAdapter flowAdapter;
    private List<FilterTypeDetail> dataFlow = new ArrayList<>();

    private void initSetTag() {
        flowLayout.setMaxLines(2);
        flowLayout.setMultiChecked(false);
        flowLayout.removeAllViews();
        flowAdapter = new FlowAdapter(dataFlow) {
            @Override
            public View getView(int position) {
                View item = mLayoutInflater.inflate(R.layout.layout_hospital_flow_tag, null);
                TextView txtTagComment = item.findViewById(R.id.txtTagComment);
                TextView txtCountComment = item.findViewById(R.id.txtCountComment);
                LinearLayout lnLayout = item.findViewById(R.id.lnServiceTag);
                lnLayout.setTag(position);
                item.setTag(position);
                txtTagComment.setText(dataFlow.get(position).getTagName());
                txtTagComment.setSelected(dataFlow.get(position).isSelected());
                lnLayout.setSelected(dataFlow.get(position).isSelected());
                txtCountComment.setVisibility(View.GONE);
                return item;
            }
        };
        flowLayout.setAdapter(flowAdapter);
    }
}

