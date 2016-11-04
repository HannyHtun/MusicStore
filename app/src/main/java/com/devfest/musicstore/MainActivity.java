package com.devfest.musicstore;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import com.devfest.musicstore.adapters.ViewPagerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import model.Screen;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth mFireBaseAuth;
    DatabaseReference mDatabase;
    boolean initialLoad = true;

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
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPager = (ViewPager) findViewById(R.id.pager);

        setSupportActionBar(toolbar);
        renderLayout();
    }

    void firebase() {
        mFireBaseAuth = FirebaseAuth.getInstance();
        mFireBaseAuth.signInAnonymously();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child("0").getParent().addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if (initialLoad) {

                    // TODO initial Fetch
                    Screen screen = new Screen(dataSnapshot);
                    screen.getSections();
                    // now we get the Whole Screen and will pass the screen to Recycler Views
                    initialLoad = false;
                } else {
                    // TODO onChildAdded

                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                // TODO play with data when onChildChanged
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                // TODO play with data when childElements were removed
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                // TODO play with data when child element is moved
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    void renderLayout() {
        final ViewPager pager = (ViewPager) findViewById(R.id.pager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), titles);
        pager.setAdapter(adapter);
        tabLayout.setupWithViewPager(pager);
        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
