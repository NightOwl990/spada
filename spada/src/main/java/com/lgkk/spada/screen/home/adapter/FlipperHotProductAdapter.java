package com.lgkk.spada.screen.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lgkk.base_project.base.listener.OnItemRvClickListener;
import com.lgkk.base_project.utils.FormatUtils;
import com.lgkk.base_project.utils.ImageUtils;
import com.lgkk.base_project.utils.StringUtils;
import com.lgkk.base_project.utils.toasty.Toasty;
import com.lgkk.base_project.widget.SimpleEvaluateStarView;
import com.lgkk.base_project.widget.roundedimageview.RoundedImageView;
import com.lgkk.spada.R;
import com.lgkk.spada.model.shop.Product;
import com.lgkk.spada.screen.service.activity.ServiceDetailActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FlipperHotProductAdapter extends BaseAdapter {
    private List<Product> list;
    private Context mContext;
    private OnItemRvClickListener onItemRvClickListener;

    public FlipperHotProductAdapter(List<Product> list, Context mContext, OnItemRvClickListener onItemRvClickListener) {
        this.list = list;
        this.mContext = mContext;
        this.onItemRvClickListener = onItemRvClickListener;
        notifyDataSetChanged();
    }

    public void setList(List<Product> list) {
        this.list = list;
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Product getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_flipper_hot_product, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Product item = getItem(position);
        holder.txtName.setText(item.getName());
        ImageUtils.loadImageByGlide(mContext, item.getAvatar(), holder.txtImage);
        holder.txtPrice.setText("đ" + StringUtils.formatPrice(item.getPrice() + ""));
        holder.txtVipPrice.setText("đ" + StringUtils.formatPrice(item.getDiscountPrice() + ""));

        holder.rbShop.setScore(Float.parseFloat(item.getRateScore() + ""));
        holder.rbShop.setStartWidget(mContext.getResources().getDimensionPixelSize(R.dimen.d_9));
        holder.rootItemView.setOnClickListener(v -> {
            ServiceDetailActivity.startActivity(mContext, item.getId());
        });
        return convertView;
    }

    public static class ViewHolder {
        @BindView(R.id.txtImage)
        RoundedImageView txtImage;
        @BindView(R.id.rootItemView)
        LinearLayout rootItemView;
        @BindView(R.id.txtName)
        TextView txtName;
        @BindView(R.id.rbShop)
        SimpleEvaluateStarView rbShop;
        @BindView(R.id.txtPrice)
        TextView txtPrice;
        @BindView(R.id.txtVipPrice)
        TextView txtVipPrice;
        @BindView(R.id.txtAddress)
        TextView txtAddress;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

