package com.lgkk.base_project.widget.skeleton;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

public class Skeleton {
    public static RecyclerViewSkeletonScreen.Builder bind(RecyclerView recyclerView) {
        return new RecyclerViewSkeletonScreen.Builder(recyclerView);
    }

    public static ViewSkeletonScreen.Builder bind(View view) {
        return new ViewSkeletonScreen.Builder(view);
    }
}
