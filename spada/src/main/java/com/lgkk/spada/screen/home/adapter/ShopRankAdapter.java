package com.lgkk.spada.screen.home.adapter;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.palette.graphics.Palette;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.DrawableImageViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lgkk.base_project.base.listener.OnItemRvClickListener;
import com.lgkk.base_project.utils.ColorTransparentUtils;
import com.lgkk.spada.R;
import com.lgkk.spada.model.RankingDetail;
import com.lgkk.base_project.widget.roundedimageview.RoundedImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShopRankAdapter extends BaseQuickAdapter<RankingDetail, BaseViewHolder> {

    @BindView(R.id.imgBackground)
    RoundedImageView imgBackground;
    @BindView(R.id.imgPattern1)
    ImageView imgPattern1;
    @BindView(R.id.imgPattern2)
    ImageView imgPattern2;
    @BindView(R.id.txtTitle)
    TextView txtTitle;
    @BindView(R.id.txtTop1)
    TextView txtTop1;
    @BindView(R.id.txtTop2)
    TextView txtTop2;
    @BindView(R.id.txtTop3)
    TextView txtTop3;
    private Context context;
    OnItemRvClickListener onItemRvClickListener;
    private List<RankingDetail> list;
    HotSpaListInsideAdapter mAdapter;

    public ShopRankAdapter(Context context, List<RankingDetail> data, OnItemRvClickListener onItemRvClickListener) {
        super(R.layout.item_ranking_spa, data);
        this.onItemRvClickListener = onItemRvClickListener;
        this.list = data;
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder holder, RankingDetail item) {
        ButterKnife.bind(this, holder.itemView);
        txtTitle.setText(item.getTitle());
        txtTop1.setText(item.getTop1());
        txtTop2.setText(item.getTop2());
        txtTop3.setText(item.getTop3());
        txtTop1.setCompoundDrawablesWithIntrinsicBounds(item.getIconType(), 0, 0, 0);
        txtTop2.setCompoundDrawablesWithIntrinsicBounds(item.getIconType(), 0, 0, 0);
        txtTop3.setCompoundDrawablesWithIntrinsicBounds(item.getIconType(), 0, 0, 0);

        Glide.with(context)
                .load(item.getImage())
                .into(new DrawableImageViewTarget(imgBackground) {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        super.onResourceReady(resource, transition);

                        Bitmap iconBitmap = ((BitmapDrawable) resource).getBitmap();
                        Palette.from(iconBitmap).maximumColorCount(16).generate(palette -> {
                            int mainColor = palette.getMutedColor(0x000000);

                            ColorTransparentUtils.setBackgroundColorAndRetainShape(ColorTransparentUtils.lighter(mainColor, 0.2f), txtTitle.getBackground());
                            ColorTransparentUtils.setBackgroundColorAndRetainShape(mainColor, imgPattern2.getBackground());

                            int transparentWhite = context.getResources().getColor(R.color.transparent_white);
                            GradientDrawable gd = new GradientDrawable(
                                    GradientDrawable.Orientation.TOP_BOTTOM,
                                    new int[]{transparentWhite, mainColor});
                            gd.setShape(GradientDrawable.RECTANGLE);
                            gd.setCornerRadius(0f);
                            imgPattern1.setBackground(gd);
                        });
                    }
                });

    }
}


