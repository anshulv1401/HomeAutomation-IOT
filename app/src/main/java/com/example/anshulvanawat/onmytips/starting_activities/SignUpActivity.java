package com.example.anshulvanawat.onmytips.starting_activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.anshulvanawat.onmytips.R;

public class SignUpActivity extends AppCompatActivity {

    public static final String RATID="com.example.anshulvanawat.askrat.RATID";
    public static final String FIRSTNAME="com.example.anshulvanawat.askrat.FIRSTNAME";
    public static final String LASTNAME="com.example.anshulvanawat.askrat.LASTNAME";
    public static final String EMAIL="com.example.anshulvanawat.askrat.EMAIL";
    public static final String PASSWORD="com.example.anshulvanawat.askrat.PASSWORD";
    public static final String DATE="com.example.anshulvanawat.askrat.DATE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }
}
