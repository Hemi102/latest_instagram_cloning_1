package com.example.latest_instagram_cloning_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.Toolbar;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;


public class socialActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private Toolbar toolbar;
    private ViewPager viewPager;
    private AppBarLayout appBarLayout;
    private TabFragment tabFragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social);

        setTitle("GPS PRESENCE SYSTEM");
        //toolbar=findViewById(R.id.myToolBar);
        //setSupportActionBar(toolbar);
        tabLayout=findViewById(R.id.tabLayout);


        viewPager=findViewById(R.id.viewPager);

        tabFragment=new TabFragment(getSupportFragmentManager());
        viewPager.setAdapter(tabFragment);
        tabLayout.setupWithViewPager(viewPager,false);

    }
}
