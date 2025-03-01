package com.lgkk.base_project.widget;

import android.content.Context;
import android.widget.ImageView;

import com.lgkk.base_project.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.lgkk.base_project.banner.loader.ImageLoader;

public class GlideImageLoader extends ImageLoader {
    Context context;

    public GlideImageLoader(Context context) {
        this.context = context;
    }

    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        //具体方法内容自己去选择，次方法是为了减少banner过多的依赖第三方包，所以将这个权限开放给使用者去选择
        RequestOptions requestOptions = new RequestOptions();
//        requestOptions.centerCrop();
        requestOptions.placeholder(R.drawable.bg_round_card_1px);
//        requestOptions.placeholder(R.drawable.glide_placeholder);
//        requestOptions.error(R.drawable.glide_placeholder);

        Glide.with(context)
                .applyDefaultRequestOptions(requestOptions)
                .load(path)
                .into(imageView);
    }

//    @Override
//    public ImageView createImageView(Context context) {
//        //圆角
//        return new RoundAngleImageView(context);
//    }
}
