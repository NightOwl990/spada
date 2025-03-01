package com.lgkk.spada.screen.user.adapter;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lgkk.base_project.base.listener.OnItemMultiRvClickListener;
import com.lgkk.base_project.widget.CircleImageView;
import com.lgkk.spada.R;
import com.lgkk.spada.model.home.NotificationBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lgkk.spada.screen.service.activity.ServiceDetailActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NotificationListAdapter extends BaseQuickAdapter<NotificationBean, BaseViewHolder> {

    public List<NotificationBean> list;
    @BindView(R.id.imgAvatar)
    CircleImageView imgAvatar;
    @BindView(R.id.txtDescription)
    TextView txtDescription;
    @BindView(R.id.txtTime)
    TextView txtTime;
    @BindView(R.id.lnText)
    LinearLayout lnText;
    @BindView(R.id.btnSetting)
    ImageView btnSetting;
    @BindView(R.id.rlRootView)
    RelativeLayout rlRootView;
    private Activity activity;
    private OnItemMultiRvClickListener onItemMultiRvClickListener;
    private String type;

    public NotificationListAdapter(Activity activity, List<NotificationBean> data, OnItemMultiRvClickListener onItemMultiRvClickListener, String type) {
        super(R.layout.item_notification, data);
        this.onItemMultiRvClickListener = onItemMultiRvClickListener;
        this.list = data;
        this.type = type;
        this.activity = activity;
    }

    public NotificationListAdapter(Activity activity, List<NotificationBean> data, OnItemMultiRvClickListener onItemMultiRvClickListener) {
        super(R.layout.item_notification, data);
        this.onItemMultiRvClickListener = onItemMultiRvClickListener;
        this.list = data;
        this.type = "new";
        this.activity = activity;
    }

    @Override
    protected void convert(BaseViewHolder holder, NotificationBean item) {
        ButterKnife.bind(this, holder.itemView);

        rlRootView.setOnClickListener(v -> {
            ServiceDetailActivity.startActivity(activity, item.getId());
        });
//        String textDescription = "<font color='black'><b>" + item.getData().getUserName() + "</b></font>" + item.getContent() + "<font color='black'><b>" + item.getData().getGroupName() + "</b></font>.";
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            txtDescription.setText(Html.fromHtml(textDescription, Html.FROM_HTML_MODE_LEGACY), TextView.BufferType.SPANNABLE);
//        } else {
//            txtDescription.setText(Html.fromHtml(textDescription), TextView.BufferType.SPANNABLE);
//        }
//        holder.setText(R.id.txtTime, FormatUtils.getDescriptionTimeFromDateString(item.getCreatedAt()));
//        ImageUtils.loadCircleImageByGlide(activity, item.getIcon(), imgAvatar);
//        rlRootView.setBackgroundColor(activity.getResources().getColor(item.isSeen() ? R.color.white : R.color.uncheck_notification_color));
//        rlRootView.setOnClickListener(v -> {
//            onItemMultiRvClickListener.onItemRvClick(v, item, holder.getAdapterPosition(), type);
//        });
//        btnSetting.setOnClickListener(v -> {
//            onItemMultiRvClickListener.onItemRvClick(v, item, holder.getAdapterPosition(), type);
//        });

    }
}