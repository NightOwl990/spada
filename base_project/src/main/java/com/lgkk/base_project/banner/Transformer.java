package com.lgkk.base_project.banner;

import androidx.viewpager.widget.ViewPager.PageTransformer;

import com.lgkk.base_project.banner.transformer.AccordionTransformer;
import com.lgkk.base_project.banner.transformer.BackgroundToForegroundTransformer;
import com.lgkk.base_project.banner.transformer.CubeInTransformer;
import com.lgkk.base_project.banner.transformer.CubeOutTransformer;
import com.lgkk.base_project.banner.transformer.DefaultTransformer;
import com.lgkk.base_project.banner.transformer.DepthPageTransformer;
import com.lgkk.base_project.banner.transformer.FlipHorizontalTransformer;
import com.lgkk.base_project.banner.transformer.FlipVerticalTransformer;
import com.lgkk.base_project.banner.transformer.ForegroundToBackgroundTransformer;
import com.lgkk.base_project.banner.transformer.RotateDownTransformer;
import com.lgkk.base_project.banner.transformer.RotateUpTransformer;
import com.lgkk.base_project.banner.transformer.ScaleInOutTransformer;
import com.lgkk.base_project.banner.transformer.StackTransformer;
import com.lgkk.base_project.banner.transformer.TabletTransformer;
import com.lgkk.base_project.banner.transformer.ZoomInTransformer;
import com.lgkk.base_project.banner.transformer.ZoomOutSlideTransformer;
import com.lgkk.base_project.banner.transformer.ZoomOutTranformer;

public class Transformer {
    public static Class<? extends PageTransformer> Default = DefaultTransformer.class;
    public static Class<? extends PageTransformer> Accordion = AccordionTransformer.class;
    public static Class<? extends PageTransformer> BackgroundToForeground = BackgroundToForegroundTransformer.class;
    public static Class<? extends PageTransformer> ForegroundToBackground = ForegroundToBackgroundTransformer.class;
    public static Class<? extends PageTransformer> CubeIn = CubeInTransformer.class;
    public static Class<? extends PageTransformer> CubeOut = CubeOutTransformer.class;
    public static Class<? extends PageTransformer> DepthPage = DepthPageTransformer.class;
    public static Class<? extends PageTransformer> FlipHorizontal = FlipHorizontalTransformer.class;
    public static Class<? extends PageTransformer> FlipVertical = FlipVerticalTransformer.class;
    public static Class<? extends PageTransformer> RotateDown = RotateDownTransformer.class;
    public static Class<? extends PageTransformer> RotateUp = RotateUpTransformer.class;
    public static Class<? extends PageTransformer> ScaleInOut = ScaleInOutTransformer.class;
    public static Class<? extends PageTransformer> Stack = StackTransformer.class;
    public static Class<? extends PageTransformer> Tablet = TabletTransformer.class;
    public static Class<? extends PageTransformer> ZoomIn = ZoomInTransformer.class;
    public static Class<? extends PageTransformer> ZoomOut = ZoomOutTranformer.class;
    public static Class<? extends PageTransformer> ZoomOutSlide = ZoomOutSlideTransformer.class;
}
