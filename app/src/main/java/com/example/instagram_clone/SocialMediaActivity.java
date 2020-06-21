package com.example.instagram_clone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
/*import androidx.appcompat.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;*/


import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;



public class SocialMediaActivity extends AppCompatActivity {

    private androidx.appcompat.widget.Toolbar toolbar;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private tabAdapter tabadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_media);
        setTitle("Social Media App!!!");
        toolbar=findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        viewPager=findViewById(R.id.viewPager);
        tabadapter= new tabAdapter(getSupportFragmentManager());
        viewPager.setAdapter(tabadapter);

        tabLayout = findViewById(R.id.TabLayout);
        tabLayout.setupWithViewPager(viewPager,false);



    }
}