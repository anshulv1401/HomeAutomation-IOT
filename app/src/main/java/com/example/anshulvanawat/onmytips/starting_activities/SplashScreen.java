package com.example.anshulvanawat.onmytips.starting_activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.Snackbar;
import android.os.Bundle;

import android.view.View;

import android.widget.TextView;
import android.widget.Toast;

import com.example.anshulvanawat.onmytips.R;
import com.example.anshulvanawat.onmytips.WebConnector;
import com.example.anshulvanawat.onmytips.main_activities.MainActivity;

import java.util.StringTokenizer;


public class SplashScreen extends AppCompatActivity implements Runnable {


    String ratid;
    String firstname;
    String lastname;
    String email;
    String password;
    String contactno;
    String address;
    WebConnector wc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        wc=new WebConnector();

        SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(getApplication());

        ratid=preferences.getString(MainActivity.USER_ID,"");
        firstname=preferences.getString(MainActivity.FIRST_NAME,"");
        lastname=preferences.getString(MainActivity.LAST_NAME,"");
        email=preferences.getString(MainActivity.EMAIL,"");
        password=preferences.getString(MainActivity.PASSWORD,"");
        contactno=preferences.getString(MainActivity.PHONE_NO,"");
        address=preferences.getString(MainActivity.ADDRESS,"");
        Handler h=new Handler();
        h.postDelayed(this,1500);

    }

    // Method to manually check connection status
    private boolean checkConnection() {

        ConnectivityManager check = (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] info = check.getAllNetworkInfo();


        String message = "No connection";
        int color= Color.WHITE;
        for (int i = 0; i<info.length; i++) {

            if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                if (wc.checkConnection()){
                    return true;
                } else {
                    message="Server connection problem";
                    color=Color.RED;
                }
            }
        }

        Snackbar snackbar = Snackbar
                .make(findViewById(R.id.splash_screen), message, Snackbar.LENGTH_LONG);
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(color);
        snackbar.show();
        return false;
    }

    @Override
    public void run() {
        if(checkConnection()) {
            if (ratid.equals("") || firstname.equals("") || lastname.equals("") || email.equals("") || password.equals("") || contactno.equals("")) {
                Intent i = new Intent(SplashScreen.this,LoginActivity.class);
                startActivity(i);
                finish();
            } else {

                String userInfo= null;
                try {
                    userInfo = wc.checkUser(email,password);
                } catch (Exception e) {
                    Toast.makeText(getApplication(),"something went wrong",Toast.LENGTH_SHORT);
                    e.printStackTrace();
                }

                if(userInfo.contains("fail")) {
                    Intent i = new Intent(SplashScreen.this,LoginActivity.class);
                    startActivity(i);
                    finish();
                }else {
                    saveToSharedPrefrences(userInfo);
                    Intent intent=new Intent(getApplication(),MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }else{
            FragmentManager fragmentManager=getSupportFragmentManager();
            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
            NetworkConnectionErrorFragment networkConnectionErrorFragment=new NetworkConnectionErrorFragment();
            fragmentTransaction.replace(R.id.splash_frame_layout,networkConnectionErrorFragment,"Error_Fragment");
            fragmentTransaction.commit();
        }
    }


        protected void saveToSharedPrefrences(String data){

            StringTokenizer st=new StringTokenizer(data,"~",false);

            //data send from webconnector
            String userId=st.nextToken().trim();
            String firstName=st.nextToken().trim();
            String lastName=st.nextToken().trim();
            String email=st.nextToken().trim();
            String password=st.nextToken().trim();
            String phoneNo=st.nextToken().trim();
            String address=st.nextToken().trim();

            SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor editor=preferences.edit();
            editor.putString(MainActivity.USER_ID,userId);
            editor.putString(MainActivity.FIRST_NAME,firstName);
            editor.putString(MainActivity.LAST_NAME,lastName);
            editor.putString(MainActivity.EMAIL,email);
            editor.putString(MainActivity.PASSWORD,password);
            editor.putString(MainActivity.ADDRESS,address);
            editor.putString(MainActivity.PHONE_NO,phoneNo);
            editor.commit();


        }
}
