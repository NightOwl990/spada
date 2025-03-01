package com.lgkk.spada.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lgkk.spada.R;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ServiceDetailHeader extends LinearLayout {
    @BindView(R.id.txtTitle)
    TextView txtTitle;
    @BindView(R.id.txtTotalComment)
    TextView txtTotalComment;
    @BindView(R.id.txtScore)
    TextView txtScore;
    @BindView(R.id.imgStar)
    ImageView imgStar;
    @BindView(R.id.imgIcon)
    ImageView imgIcon;
    @BindView(R.id.rlScore)
    RelativeLayout rlScore;
    @BindView(R.id.rlComment)
    RelativeLayout rlComment;
    @BindView(R.id.txtNoComment)
    TextView txtNoComment;
    @BindView(R.id.lnNoComment)
    LinearLayout lnNoComment;
    @BindView(R.id.short_title_root_layout)
    LinearLayout shortTitleRootLayout;
    private String title;
    private String score;
    private int totalComment;
    private String noComment;
    private Unbinder unbinder;

    public ServiceDetailHeader(Context context) {
        this(context, null);
    }

    public ServiceDetailHeader(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs, 0);
        View view = LayoutInflater.from(context).inflate(R.layout.layout_service_detail_header, this, true);
        unbinder = ButterKnife.bind(this, view);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.ServiceDetailHeader, 0, 0);

        try {
            this.title = a.getString(R.styleable.ServiceDetailHeader_svdh_title);
            this.score = a.getString(R.styleable.ServiceDetailHeader_svdh_score);
            this.totalComment = a.getInt(R.styleable.ServiceDetailHeader_svdh_total_comment, 0);
            this.noComment = a.getString(R.styleable.ServiceDetailHeader_svdh_no_comment);
        } finally {
            a.recycle();
        }

        // Throw an exception if required attributes are not set
        if (this.title == null) {
            throw new RuntimeException("No title provided");
        }
        if (this.score == null) {
            throw new RuntimeException("No subtitle provided");
        }

        initSetText(title, score, totalComment, noComment);
    }

    public void setTotalComment(int totalComment) {
        this.totalComment = totalComment;
        initSetText(title, score, totalComment, noComment);

    }

    public void setScore(String score) {
        this.score = score;
        initSetText(title, score, totalComment, noComment);

    }

    public void setTitle(String title) {
        this.title = title;
        initSetText(title, score, totalComment, noComment);

    }

    private void initSetText(String title, String score, int totalComment, String noComment) {
        txtTitle.setText(title);
        txtScore.setText(score);
        txtNoComment.setText(noComment);
        txtTotalComment.setText("(" + totalComment + ")");
        if (totalComment > 0) {
            lnNoComment.setVisibility(GONE);
            rlComment.setVisibility(VISIBLE);
            rlScore.setVisibility(VISIBLE);

        } else {
            lnNoComment.setVisibility(VISIBLE);
            rlComment.setVisibility(VISIBLE);
            rlScore.setVisibility(GONE);
        }

    }
}
