package com.example.anshulvanawat.onmytips.Other_Fragment_Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.example.anshulvanawat.onmytips.R;
import com.example.anshulvanawat.onmytips.ZoomOutPageTransformer;
import com.example.anshulvanawat.onmytips.customSwip;
import com.example.anshulvanawat.onmytips.main_activities.MainActivity;

public class ImageDisplayActivity extends Activity {

    ViewPager viewPager;
    customSwip  customSwip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_display);
        Intent intent=getIntent();
        int position=intent.getExtras().getInt(MainActivity.IMAGE_POSITION,0);

        viewPager=(ViewPager)findViewById(R.id.viewPager);
        customSwip=new customSwip(this);
        viewPager.setAdapter(customSwip);
        viewPager.setCurrentItem(position);
        viewPager.setPageTransformer(true, new ZoomOutPageTransformer());
    }
}
