package com.example.fragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager2 = findViewById(R.id.viewPager2);

        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(this);
        viewPager2.setAdapter(myPagerAdapter);
    }
}