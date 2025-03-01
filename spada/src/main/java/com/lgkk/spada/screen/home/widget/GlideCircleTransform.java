package com.lgkk.spada.screen.home.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import androidx.annotation.NonNull;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

import java.security.MessageDigest;

public class GlideCircleTransform extends BitmapTransformation {
    private Paint mBorderPaint = new Paint();
    private float mBorderWidth;

    public GlideCircleTransform(Context context, int i, int i2) {
        this.mBorderWidth = Resources.getSystem().getDisplayMetrics().density * ((float) i);
        this.mBorderPaint.setDither(true);
        this.mBorderPaint.setAntiAlias(true);
        this.mBorderPaint.setColor(i2);
        this.mBorderPaint.setStyle(Paint.Style.STROKE);
        this.mBorderPaint.setStrokeWidth(this.mBorderWidth);
    }

    private Bitmap circleCrop(BitmapPool bitmapPool, Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        int min = (int) (((float) Math.min(bitmap.getWidth(), bitmap.getHeight())) - (this.mBorderWidth / 2.0f));
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, (bitmap.getWidth() - min) / 2, (bitmap.getHeight() - min) / 2, min, min);
        Bitmap bitmap2 = bitmapPool.get(min, min, Bitmap.Config.ARGB_8888);
        if (bitmap2 == null) {
            bitmap2 = Bitmap.createBitmap(min, min, Bitmap.Config.ARGB_8888);
        }
        Canvas canvas = new Canvas(bitmap2);
        Paint paint = new Paint();
        paint.setShader(new BitmapShader(createBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        paint.setAntiAlias(true);
        float f = ((float) min) / 2.0f;
        canvas.drawCircle(f, f, f, paint);
        Paint paint2 = this.mBorderPaint;
        if (paint2 != null) {
            canvas.drawCircle(f, f, f - (this.mBorderWidth / 2.0f), paint2);
        }
        return bitmap2;
    }


    public void updateDiskCacheKey(MessageDigest messageDigest) {
    }

    @Override
    protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
        return circleCrop(pool, toTransform);
    }
}
