package com.devfest.musicstore.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.devfest.musicstore.R;
import com.devfest.musicstore.adapters.ViewPagerAdapter;
import com.devfest.musicstore.custom.NonSwipeableViewPager;

public class MainActivity extends AppCompatActivity {


    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    String[] titles = {"Home", "Radio"};
    private int[] tabIcons = {
            R.drawable.ic_home,
            R.drawable.ic_radio

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Setting Up ViewPager and Toolbar
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPager = (NonSwipeableViewPager) findViewById(R.id.pager);

        renderLayout();
    }


    void renderLayout() {
        final ViewPager pager = (ViewPager) findViewById(R.id.pager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), titles);
        pager.setAdapter(adapter);
        tabLayout.setupWithViewPager(pager);
        pager.setOnPageChangeListener(new NonSwipeableViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        setupTabIcons();
    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_home);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_radio);
    }
}
