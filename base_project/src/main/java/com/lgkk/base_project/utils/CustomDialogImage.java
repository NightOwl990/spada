package com.lgkk.base_project.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.lgkk.base_project.R;
import com.squareup.picasso.Picasso;

/**
 * Created by Hoang Nam on 13/06/2017.
 */

public class CustomDialogImage {

    public static void Show(final Context context, final String url) {
        final Dialog dialog = new Dialog(context, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_diaglog_image);

        ImageView imageView = (ImageView) dialog.findViewById(R.id.img_Dialog);
        ImageView backImage = (ImageView) dialog.findViewById(R.id.img_BackDialog);

        ImageView shareImage = (ImageView) dialog.findViewById(R.id.img_ShareDialog);
        shareImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent share = new Intent(android.content.Intent.ACTION_SEND);
                share.setType("text/plain");
                share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

                share.putExtra(Intent.EXTRA_SUBJECT, "Tieu de");
                share.putExtra(Intent.EXTRA_TEXT, url);

                context.startActivity(Intent.createChooser(share, "chia sẻ ảnh cho..."));
            }
        });
        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        Picasso.get().load(url).into(imageView);
//        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        dialog.show();

    }
}
