package com.lgkk.spada.screen.service.widget.filter;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.lgkk.base_project.widget.flowlayout.AutoFlowLayout;
import com.lgkk.base_project.widget.flowlayout.FlowAdapter;
import com.lgkk.spada.R;
import com.lgkk.spada.model.FilterTypeDetail;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FilterTagFlowLayout extends LinearLayout implements View.OnClickListener {


    @BindView(R.id.txtTitle)
    TextView txtTitle;
    @BindView(R.id.btnExpand)
    ImageView btnExpand;
    @BindView(R.id.rlTitle)
    RelativeLayout rlTitle;
    @BindView(R.id.flowLayout)
    AutoFlowLayout flowLayout;
    @BindView(R.id.divider)
    View divider;
    private List<FilterTypeDetail> mData = new ArrayList<>();

    private boolean isExpanded;
    private boolean expandVisible;
    private LayoutInflater mLayoutInflater;
    private Context context;
    private Unbinder unbinder;
    private String title = "";
    private boolean firstLoad = true;
    private FlowAdapter flowAdapter;
    private boolean dividerVisible = true;
    private boolean titleVisible = true;

    public FilterTagFlowLayout(Context context) {
        this(context, null);
    }

    public FilterTagFlowLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs, 0);
        View view = LayoutInflater.from(context).inflate(R.layout.layout_filter_tag_flowlayout, this, true);
        unbinder = ButterKnife.bind(this, view);
        this.context = context;
        mLayoutInflater = LayoutInflater.from(context);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.FilterTagFLowLayout, 0, 0);
        expandVisible = a.getBoolean(R.styleable.FilterTagFLowLayout_ffl_expanded, false);
        dividerVisible = a.getBoolean(R.styleable.FilterTagFLowLayout_ffl_dividerVisible, true);
        titleVisible = a.getBoolean(R.styleable.FilterTagFLowLayout_ffl_titleVisible, true);
        title = a.getString(R.styleable.FilterTagFLowLayout_ffl_title);
        a.recycle();
        initView();


    }

    private void setVisibleView(View view, boolean visible) {
        view.setVisibility(visible ? VISIBLE : GONE);
    }

    private void initView() {
        setVisibleView(rlTitle, titleVisible);
        txtTitle.setText(title);
//        flowLayout.setLineCenter(false);
        flowLayout.setMaxLines(2);
        flowLayout.setMultiChecked(true);
        btnExpand.setOnClickListener(this);
        divider.setVisibility(dividerVisible ? VISIBLE : GONE);
        flowLayout.setOnItemClickListener((position, view) -> {
            mData.get(position).setSelected(!mData.get(position).isSelected());
            flowAdapter.getView(position).setSelected(mData.get(position).isSelected());
            flowLayout.removeAllViews();
            flowLayout.setAdapter(flowAdapter);
//            Log.d("kiemtra", getCheckedPositionList().toString());
        });
    }


    public void setData(List<FilterTypeDetail> mDataSet) {
        mData.clear();
        mData.addAll(mDataSet);
        initSetTag();

    }

    public void setTitle(String title) {
        txtTitle.setText(title);
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

    public List<FilterTypeDetail> getCheckedInfoList() {
        List<FilterTypeDetail> listChecked = new ArrayList<>();
        listChecked.clear();
        for (int i = 0; i < mData.size(); i++) {
            if (flowAdapter.getView(i).isSelected())
                listChecked.add(mData.get(i));
        }
        return listChecked;
    }

    public void removeAllSelected() {
        for (int i = 0; i < mData.size(); i++) {
            if (flowAdapter.getView(i).isSelected())
                mData.get(i).setSelected(!mData.get(i).isSelected());
            flowAdapter.getView(i).setSelected(mData.get(i).isSelected());
        }
        flowLayout.removeAllViews();
        flowLayout.setAdapter(flowAdapter);
    }

    private void initSetTag() {
        flowLayout.removeAllViews();
        flowAdapter = new FlowAdapter(mData) {
            @Override
            public View getView(int position) {
                View item = mLayoutInflater.inflate(R.layout.layout_service_detail_flow_tag_comment, null);
                TextView txtTagComment = item.findViewById(R.id.txtTagComment);
                TextView txtCountComment = item.findViewById(R.id.txtCountComment);
                LinearLayout lnLayout = item.findViewById(R.id.lnServiceTag);
                lnLayout.setTag(position);
                item.setTag(position);
                txtTagComment.setText(mData.get(position).getTagName());
                txtTagComment.setSelected(mData.get(position).isSelected());
                lnLayout.setSelected(mData.get(position).isSelected());
                txtCountComment.setVisibility(GONE);
                return item;
            }
        };
        flowLayout.setAdapter(flowAdapter);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (firstLoad) {
            btnExpand.setVisibility(flowLayout.getTotalLineNumber() >= 2 ? VISIBLE : GONE);
            firstLoad = false;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnExpand:
                if (!isExpanded) {
                    flowLayout.setMaxLines(30);
                    btnExpand.setImageDrawable(getResources().getDrawable(R.drawable.up_arrow_project));
                    isExpanded = true;
                } else {
                    flowLayout.setMaxLines(2);
                    btnExpand.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow_project));
                    isExpanded = false;
                }
                break;
        }
    }
}

