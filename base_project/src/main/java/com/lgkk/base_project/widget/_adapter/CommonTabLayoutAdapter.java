package com.lgkk.base_project.widget._adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class CommonTabLayoutAdapter extends FragmentPagerAdapter {
    List<Fragment> fragmentList;
    List<String> mDatas;

    public CommonTabLayoutAdapter(FragmentManager fm, List<Fragment> fragmentList, List<String> mDatas) {
        super(fm);
        this.fragmentList = fragmentList;
        this.mDatas = mDatas;
    }


    @Override
    public Fragment getItem(int i) {
        return fragmentList.get(i);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mDatas.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
