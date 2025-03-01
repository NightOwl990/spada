package com.lgkk.spada.widget;


import android.content.Context;
import android.content.res.TypedArray;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lgkk.base_project.widget.flowlayout.AutoFlowLayout;
import com.lgkk.base_project.widget.flowlayout.FlowAdapter;
import com.lgkk.spada.R;
import com.lgkk.spada.model.service.ServiceTagComment;

import java.util.ArrayList;
import java.util.List;

public class ServiceDetailTagFlowLayout extends LinearLayout implements View.OnClickListener {
    private List<ServiceTagComment> mData = new ArrayList<>();
    private AutoFlowLayout flowLayout;
    private LinearLayout lnGetMore;
    private boolean isExpanded;
    private LayoutInflater mLayoutInflater;
    private ImageView imgGetMore;
    private float paddingLeft;
    private FlowAdapter flowAdapter;

    public ServiceDetailTagFlowLayout(Context context) {
        this(context, null);
    }

    public ServiceDetailTagFlowLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs, 0);
        View view = LayoutInflater.from(context).inflate(R.layout.layout_service_detail_tag_flowlayout, this, false);

        mLayoutInflater = LayoutInflater.from(context);
        flowLayout = view.findViewById(R.id.flowLayout);
        lnGetMore = view.findViewById(R.id.lnGetMore);
        imgGetMore = view.findViewById(R.id.imgGetMore);
        flowLayout.setLineCenter(false);
        flowLayout.setMaxLines(2);
        flowLayout.setMultiChecked(true);

        flowLayout.setOnItemClickListener((position, view1) -> {
            mData.get(position).setSelected(!mData.get(position).isSelected());
            flowAdapter.getView(position).setSelected(mData.get(position).isSelected());
            flowLayout.removeAllViews();
            flowLayout.setAdapter(flowAdapter);
//            Log.d("kiemtra", getCheckedPositionList().toString());
        });

//        flowLayout.setOnItemClickListener((position, view1) -> {
//            Log.d("kiemtra", getCheckedPositionList().toString());
//        });

        lnGetMore.setOnClickListener(this);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.ServiceDetailTagFLowLayout, 0, 0);

        try {
            paddingLeft = a.getDimension(R.styleable.ServiceDetailTagFLowLayout_sdtfl_paddingLeft, 0f);
        } finally {
            a.recycle();
        }
        flowLayout.setPadding((int) paddingLeft, 0, 0, 0);
        addView(view);

    }

    public void setData(Context context, List<ServiceTagComment> mDataSet) {
        mData.clear();
        mData.add(new ServiceTagComment("Tất cả", 0));
        mData.addAll(mDataSet);
        initSetTag();

    }

    public List<Integer> getCheckedPositionList() {

        List<Integer> listPosition = new ArrayList<>();
        listPosition.clear();
        for (int i = 0; i < mData.size(); i++) {
            if (flowAdapter.getView(i).isSelected())
                listPosition.add(i);
        }
        return listPosition;
    }

    private void initSetTag() {
        flowLayout.removeAllViews();
        flowAdapter = new FlowAdapter(mData) {
            @Override
            public View getView(int position) {
                View item = mLayoutInflater.inflate(R.layout.layout_service_detail_flow_tag_comment, null);
                TextView txtTagComment = item.findViewById(R.id.txtTagComment);
                TextView txtCountComment = item.findViewById(R.id.txtCountComment);
                LinearLayout  lnLayout = item.findViewById(R.id.lnServiceTag);
                lnLayout.setTag(position);
                item.setTag(position);
                txtTagComment.setText(mData.get(position).getTagName());
                txtCountComment.setText(mData.get(position).getCommentCount() + "");
                txtTagComment.setSelected(mData.get(position).isSelected());
                lnLayout.setSelected(mData.get(position).isSelected());
                txtCountComment.setSelected(mData.get(position).isSelected());
                return item;
            }
        };
        flowLayout.setAdapter(flowAdapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lnGetMore:
                if (!isExpanded) {
                    flowLayout.setMaxLines(10);
                    imgGetMore.setImageDrawable(getResources().getDrawable(R.drawable.up_arrow_project));
                    isExpanded = true;
                } else {
                    flowLayout.setMaxLines(2);
                    imgGetMore.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow_project));
                    isExpanded = false;
                }
                break;
        }
    }
}
