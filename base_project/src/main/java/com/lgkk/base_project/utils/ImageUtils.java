package com.lgkk.base_project.utils;

import android.content.Context;
import androidx.annotation.DrawableRes;
import android.widget.ImageView;

import com.lgkk.base_project.R;
import com.lgkk.base_project.base.Constants;
import com.lgkk.base_project.widget.CircleImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.lgkk.base_project.widget.roundedimageview.RoundedImageView;

import java.io.File;

public class ImageUtils {

    public static void loadImageByGlide(Context mContext, String imageUrl, ImageView imageView) {

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.centerCrop();
        requestOptions.placeholder(R.drawable.bg_round_card_1px);
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);

//        requestOptions.placeholder(R.drawable.insert_photo_default);

        if (imageUrl.contains("http")) {
            Glide.with(mContext)
                    .asBitmap()
                    .apply(requestOptions)
                    .load(imageUrl)
                    .into(imageView);
        } else {
            Glide.with(mContext)
                    .asBitmap()
                    .apply(requestOptions)
                    .load(Constants.BASE_API_URL + imageUrl)
                    .into(imageView);
        }
    }

    public static void loadImageByGlideWithType(Context mContext, String imageUrl, ImageView imageView, String type) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.bg_round_card_1px);
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);

        switch (type) {
            case "centerCrop":
                requestOptions.centerCrop();
                break;
            case "centerInside":
                requestOptions.centerInside();
                break;
            case "fitCenter":
                requestOptions.fitCenter();
                break;
        }

        if (imageUrl.contains("http")) {
            Glide.with(mContext)
                    .asBitmap()
                    .apply(requestOptions)
                    .load(imageUrl)
                    .into(imageView);
        } else {
            Glide.with(mContext)
                    .asBitmap()
                    .apply(requestOptions)
                    .load(Constants.BASE_API_URL + imageUrl)
                    .into(imageView);
        }
    }

    public static void loadImageByGlidePlaceHolder(Context mContext, String imageUrl, ImageView imageView) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.centerCrop();
        requestOptions.placeholder(R.drawable.apk_news_remindmeetyou);
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);


        if (imageUrl.contains("http")) {
            Glide.with(mContext)
                    .asBitmap()
                    .apply(requestOptions)
                    .load(imageUrl)
                    .into(imageView);
        } else {
            Glide.with(mContext)
                    .asBitmap()
                    .apply(requestOptions)
                    .load(Constants.BASE_API_URL + imageUrl)
                    .into(imageView);
        }
    }


    public static void loadImageByGlide(Context mContext, String imageUrl, ImageView imageView, String type) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.bg_round_card_1px);
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);

        switch (type) {
            case "centerCrop":
                requestOptions.centerCrop();
                break;
            case "centerInside":
                requestOptions.centerInside();
                break;
            case "fitCenter":
                requestOptions.fitCenter();
                break;
        }

        if (imageUrl.contains("http")) {
            Glide.with(mContext)
                    .asBitmap()
                    .apply(requestOptions)
                    .load(imageUrl)
                    .into(imageView);
        } else {
            Glide.with(mContext)
                    .asBitmap()
                    .apply(requestOptions)
                    .load(Constants.BASE_API_URL + imageUrl)
                    .into(imageView);
        }
    }

    public static void loadImageDrawableByGlide(Context mContext, @DrawableRes int imageUrl, ImageView imageView) {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.fitCenter();
        requestOptions.placeholder(R.drawable.bg_round_card_1px);
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);

        Glide.with(mContext)
                .asBitmap()
                .apply(requestOptions)
                .load(imageUrl)
                .into(imageView);
    }

    public static void loadImageDrawableByGlide(Context mContext, @DrawableRes int imageUrl, ImageView imageView, String type) {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.fitCenter();
        requestOptions.placeholder(R.drawable.bg_round_card_1px);
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);

        switch (type) {
            case "centerCrop":
                requestOptions.centerCrop();
                break;
            case "centerInside":
                requestOptions.centerInside();
                break;
            case "fitCenter":
                requestOptions.fitCenter();
                break;
        }

        Glide.with(mContext)
                .asBitmap()
                .apply(requestOptions)
                .load(imageUrl)
                .into(imageView);
    }

    public static void loadRoundedImageByGlide(Context mContext, String imageUrl, RoundedImageView imageView) {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.fitCenter();
        requestOptions.placeholder(R.drawable.bg_round_card_1px);
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);

        if (imageUrl.contains("http")) {
            Glide.with(mContext)
                    .asBitmap()
                    .apply(requestOptions)
                    .load(imageUrl)
                    .into(imageView);
        } else {
            Glide.with(mContext)
                    .asBitmap()
                    .apply(requestOptions)
                    .load(Constants.BASE_API_URL + imageUrl)
                    .into(imageView);
        }
    }

    public static void loadRoundedDrawableImageByGlide(Context mContext, @DrawableRes int imageUrl, RoundedImageView imageView) {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.fitCenter();
        requestOptions.placeholder(R.drawable.bg_round_card_1px);
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);

        Glide.with(mContext)
                .asBitmap()
                .apply(requestOptions)
                .load(imageUrl)
                .into(imageView);
    }

    public static void loadImageLocalbyGlide(Context mContext, String imageUrl, ImageView imageView) {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.centerCrop();
        requestOptions.placeholder(R.drawable.bg_round_card_1px);
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);

        Glide.with(mContext)
                .asBitmap()
                .apply(requestOptions)
                .load(new File(imageUrl))
                .into(imageView);

    }

    public static void loadImageByGlideWithResize(Context mContext, String imageUrl, ImageView imageView, int width, int height) {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.override(width, height);
        requestOptions.centerCrop();
        requestOptions.placeholder(R.drawable.bg_round_card_1px);
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);

        if (imageUrl.contains("http")) {
            Glide.with(mContext)
                    .asBitmap()
                    .apply(requestOptions)

                    .load(imageUrl)
                    .into(imageView);
        } else {
            Glide.with(mContext)
                    .asBitmap()
                    .apply(requestOptions)

                    .load(Constants.BASE_API_URL + imageUrl)
                    .into(imageView);
        }
    }


    public static void loadCircleImageByGlide(Context mContext, String imageUrl, CircleImageView imageView) {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.centerCrop();
        requestOptions.placeholder(R.drawable.bg_round_card_1px);
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);

        if (imageUrl.contains("http")) {
            Glide.with(mContext)
                    .asBitmap()
                    .apply(requestOptions)
                    .load(imageUrl)
                    .into(imageView);
        } else {
            Glide.with(mContext)
                    .asBitmap()
                    .apply(requestOptions)
                    .load(Constants.BASE_API_URL + imageUrl)
                    .into(imageView);
        }
    }

    public static void loadCircleImageAvatarByGlide(Context mContext, String imageUrl, CircleImageView imageView) {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.centerCrop();
        requestOptions.placeholder(R.drawable.apk_news_remindmeetyou);
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);

        if (imageUrl != null && imageUrl.contains("http")) {
            Glide.with(mContext)
                    .asBitmap()
                    .apply(requestOptions)
                    .load(imageUrl)
                    .into(imageView);
        } else if (imageUrl != null) {
            Glide.with(mContext)
                    .asBitmap()
                    .apply(requestOptions)
                    .load(Constants.BASE_API_URL + imageUrl)
                    .into(imageView);
        }
    }

    public static void loadCircleImageByGlideResize(Context mContext, String imageUrl, CircleImageView imageView, int width, int height) {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.centerCrop();
        requestOptions.override(width, height);
        requestOptions.placeholder(R.drawable.bg_round_card_1px);
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);

        if (imageUrl.contains("http")) {
            Glide.with(mContext)
                    .asBitmap()
                    .apply(requestOptions)
                    .load(imageUrl)
                    .into(imageView);
        } else {
            Glide.with(mContext)
                    .asBitmap()
                    .apply(requestOptions)
                    .load(Constants.BASE_API_URL + imageUrl)
                    .into(imageView);
        }
    }

}
