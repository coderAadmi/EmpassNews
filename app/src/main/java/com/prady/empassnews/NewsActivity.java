package com.prady.empassnews;

import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;

import com.prady.empassnews.NewsDB.News;
import com.prady.empassnews.NewsDB.NewsDbHandler;

import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends AppCompatActivity {

    private TabLayout mNewsTab;
    private ViewPager mNewsViewPager;
    private Toolbar toolbar;
    private ActionBar actionBar;
    private NewsDbHandler newsDbHandler;
    private int currentTab;

    private static int[] Colors = { android.R.color.holo_orange_light, R.color.colorAccent, android.R.color.holo_blue_bright, android.R.color.holo_green_light
            , android.R.color.holo_orange_light,android.R.color.holo_blue_bright
            , android.R.color.holo_green_light, R.color.colorAccent};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        currentTab =  0;
        toolbar = findViewById(R.id.appbar);
        setSupportActionBar(toolbar);

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP)
        {
            getWindow().setStatusBarColor(ContextCompat.getColor(NewsActivity.this,Colors[currentTab]));
        }


        mNewsTab = findViewById(R.id.tab_list);
        mNewsViewPager = findViewById(R.id.view_pager);
        newsDbHandler = new NewsDbHandler(getApplicationContext());
        setNewsViewPagerAdapter(mNewsViewPager);
        mNewsTab.setupWithViewPager(mNewsViewPager);
        mNewsTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                currentTab = tab.getPosition();
                mNewsViewPager.setCurrentItem(currentTab);
                toolbar.setBackgroundColor(ContextCompat.getColor(NewsActivity.this,Colors[currentTab]));
                mNewsTab.setBackgroundColor(ContextCompat.getColor(NewsActivity.this,Colors[currentTab]));
                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP)
                {
                    getWindow().setStatusBarColor(ContextCompat.getColor(NewsActivity.this,Colors[currentTab]));
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        mNewsViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mNewsTab));

    }

    private void setNewsViewPagerAdapter(ViewPager viewPager) {
        ArrayList<NewsFragment> newsFragments = new ArrayList<NewsFragment>();

        NewsFragment newsFragment0 = new NewsFragment();
        newsFragment0.setName("" + 0);
        newsFragment0.setNewsList((ArrayList<News>) newsDbHandler.getAllNewsByType(0));
        newsFragments.add(newsFragment0);

        NewsFragment newsFragment1 = new NewsFragment();
        newsFragment1.setName("" + 1);
        newsFragment1.setNewsList((ArrayList<News>) newsDbHandler.getAllNewsByType(1));
        newsFragments.add(newsFragment1);

        NewsFragment newsFragment2 = new NewsFragment();
        newsFragment2.setName("" + 2);
        newsFragment2.setNewsList((ArrayList<News>) newsDbHandler.getAllNewsByType(2));
        newsFragments.add(newsFragment2);

        NewsFragment newsFragment3 = new NewsFragment();
        newsFragment3.setName("" + 3);
        newsFragment3.setNewsList((ArrayList<News>) newsDbHandler.getAllNewsByType(3));
        newsFragments.add(newsFragment3);

        NewsFragment newsFragment4 = new NewsFragment();
        newsFragment4.setName("" + 4);
        newsFragment4.setNewsList((ArrayList<News>) newsDbHandler.getAllNewsByType(4));
        newsFragments.add(newsFragment4);

        NewsFragment newsFragment5 = new NewsFragment();
        newsFragment5.setName("" + 5);
        newsFragment5.setNewsList((ArrayList<News>) newsDbHandler.getAllNewsByType(5));
        newsFragments.add(newsFragment5);

        NewsFragment newsFragment6 = new NewsFragment();
        newsFragment6.setName("" + 6);
        newsFragment6.setNewsList((ArrayList<News>) newsDbHandler.getAllNewsByType(6));
        newsFragments.add(newsFragment6);

        NewsFragment newsFragment7 = new NewsFragment();
        newsFragment7.setName("" + 7);
        newsFragment7.setNewsList((ArrayList<News>) newsDbHandler.getAllNews());
        newsFragments.add(newsFragment7);

        viewPager.setAdapter(new NewsPagerAdapter(getSupportFragmentManager(), newsFragments));
    }

    @Override
    public void onBackPressed() {
        if (currentTab == 0)
            super.onBackPressed();
        else{
            mNewsViewPager.setCurrentItem(0,true);
        }
    }

}
