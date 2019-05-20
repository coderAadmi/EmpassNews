package com.prady.empassnews;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class NewsPagerAdapter extends FragmentPagerAdapter {

    ArrayList<NewsFragment> mNewsFragmentList;
    public static String[] PageTitles = {"TOP","Sports","Business","Health","Science","Tech","Fun","ALL"};

    public NewsPagerAdapter(FragmentManager fm,ArrayList<NewsFragment> NewsFragmentList) {
        super(fm);
        mNewsFragmentList = NewsFragmentList;
    }

    @Override
    public Fragment getItem(int i) {
        return mNewsFragmentList.get(i);
    }

    @Override
    public int getCount() {
        return mNewsFragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return PageTitles[position];
    }

}
