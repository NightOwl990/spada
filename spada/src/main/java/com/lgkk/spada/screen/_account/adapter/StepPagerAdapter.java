package com.lgkk.spada.screen._account.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

public class StepPagerAdapter extends FragmentStatePagerAdapter {
    List<Fragment> fragmentList;
    String[] mDatas;

    public StepPagerAdapter(FragmentManager fm, List<Fragment> fragmentList, String[] mDatas) {
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
        return mDatas[position];
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}