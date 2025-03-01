package com.lgkk.base_project.utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;

import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.lgkk.base_project.R;
import com.lgkk.base_project.widget.TitleFlashSale;
import com.lgkk.base_project.widget.magicindicator.CommonNavigator;
import com.lgkk.base_project.widget.magicindicator.MagicIndicator;
import com.lgkk.base_project.widget.magicindicator.ScaleTransitionPagerTitleView;
import com.lgkk.base_project.widget.magicindicator.UIUtil;
import com.lgkk.base_project.widget.magicindicator.ViewPagerHelper;
import com.lgkk.base_project.widget.magicindicator.abs.CommonNavigatorAdapter;
import com.lgkk.base_project.widget.magicindicator.abs.IPagerIndicator;
import com.lgkk.base_project.widget.magicindicator.abs.IPagerTitleView;
import com.lgkk.base_project.widget.magicindicator.indicators.LinePagerIndicator;
import com.lgkk.base_project.widget.magicindicator.titles.CommonPagerTitleView;
import com.lgkk.base_project.widget.magicindicator.titles.SimplePagerTitleView;

import java.util.Arrays;
import java.util.List;

public class MagicIndicatorUtil {

    public static void settingMagicIndicatorTabLayout(Context context, int arrayInt, MagicIndicator magicTabLayout, ViewPager viewPager) {

        List<String>  titleList = Arrays.asList(context.getResources().getStringArray(arrayInt));
        magicTabLayout.setBackgroundColor(Color.WHITE);
        CommonNavigator commonNavigator = new CommonNavigator(context);
        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return titleList == null ? 0 : titleList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ScaleTransitionPagerTitleView(context);
                simplePagerTitleView.setText(titleList.get(index));
                simplePagerTitleView.setTextSize(TypedValue.COMPLEX_UNIT_PX, context.getResources().getDimensionPixelSize(R.dimen.d_16));
//                simplePagerTitleView.setSelectedTypeface(Typeface.BOLD);
                simplePagerTitleView.setNormalColor(context.getResources().getColor(R.color.text_main));
                simplePagerTitleView.setSelectedColor(context.getResources().getColor(R.color.text_dark));

                simplePagerTitleView.setOnClickListener(v -> viewPager.setCurrentItem(index));
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
//                indicator.setStartInterpolator(new AccelerateInterpolator());
//                indicator.setEndInterpolator(new DecelerateInterpolator(1.6f));
//                indicator.setYOffset(UIUtil.dip2px(context, 39));
                indicator.setLineHeight(context.getResources().getDimension(R.dimen.d_3));
                indicator.setColors(context.getResources().getColor(R.color.drop_down_selected));
                return indicator;
            }

            @Override
            public float getTitleWeight(Context context, int index) {
                if (index == 0) {
                    return 1.0f;
                } else if (index == 1) {
                    return 1.0f;
                } else {
                    return 1.0f;
                }
            }
        });
        magicTabLayout.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicTabLayout, viewPager);
    }

    public static void settingMagicIndicatorFlashSaleTabLayout(Context context, List<TitleFlashSale> titleList, MagicIndicator magicTabLayout, ViewPager viewPager) {
        CommonNavigator commonNavigator = new CommonNavigator(context);
        commonNavigator.setAdjustMode(false);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {

            @Override
            public int getCount() {
                return titleList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                CommonPagerTitleView commonPagerTitleView = new CommonPagerTitleView(context);

                // load custom layout
                View customLayout = LayoutInflater.from(context).inflate(R.layout.layout_item_flash_sale_title, null);
                final TextView txtHour = customLayout.findViewById(R.id.txtHour);
                final TextView txtStatus = customLayout.findViewById(R.id.txtStatus);
                txtHour.setText(titleList.get(index).getHour()+":00");
                txtStatus.setText(titleList.get(index).getStatus());
                commonPagerTitleView.setContentView(customLayout);
                commonPagerTitleView.setOnPagerTitleChangeListener(new CommonPagerTitleView.OnPagerTitleChangeListener() {

                    @Override
                    public void onSelected(int index, int totalCount) {

                    }

                    @Override
                    public void onDeselected(int index, int totalCount) {

                    }

                    @Override
                    public void onLeave(int index, int totalCount, float leavePercent, boolean leftToRight) {
                        txtHour.setScaleX(1.2f + (0.9f - 1.2f) * leavePercent);
                        txtHour.setScaleY(1.2f + (0.9f - 1.2f) * leavePercent);
                        txtStatus.setScaleX(1.2f + (0.9f - 1.2f) * leavePercent);
                        txtStatus.setScaleY(1.2f + (0.9f - 1.2f) * leavePercent);
                    }

                    @Override
                    public void onEnter(int index, int totalCount, float enterPercent, boolean leftToRight) {
                        txtHour.setScaleX(0.9f + (1.2f - 0.9f) * enterPercent);
                        txtHour.setScaleY(0.9f + (1.2f - 0.9f) * enterPercent);
                        txtStatus.setScaleX(0.9f + (1.2f - 0.9f) * enterPercent);
                        txtStatus.setScaleY(0.9f + (1.2f - 0.9f) * enterPercent);
                    }
                });

                commonPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewPager.setCurrentItem(index);
                    }
                });

                return commonPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                return null;
            }
        });
        magicTabLayout.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicTabLayout, viewPager);
    }
}
