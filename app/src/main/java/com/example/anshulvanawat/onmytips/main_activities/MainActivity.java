package com.example.anshulvanawat.onmytips.main_activities;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.anshulvanawat.onmytips.Other_Fragment_Activities.AboutUsFragment;
import com.example.anshulvanawat.onmytips.Other_Fragment_Activities.GalleryFragment;
import com.example.anshulvanawat.onmytips.Other_Fragment_Activities.MyProfileFragment;
import com.example.anshulvanawat.onmytips.R;
import com.example.anshulvanawat.onmytips.starting_activities.LoginActivity;

import android.text.Html;
import android.view.Gravity;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{


    public static final String ADMIN_ID="adminId";
    public static final String REGISTRATION_ID="registrationId";

    public static final String FIRST_NAME="firstName";
    public static final String LAST_NAME="lastName";
    public static final String EMAIL="email";
    public static final String PASSWORD="password";
    public static final String PHONE_NO="phoneNo";
    public static final String ADDRESS="address";

    public static final String DEVICE_ID="deviceId";
    public static final String USER_ID="userId";
    public static final String DEVICE_NAME="deviceName";
    public static final String STATUS="status";
    public static final String ON_TIME="onTime";
    public static final String STATE_ON="on";
    public static final String STATE_OFF="off";
    public static final String POWER_CONSUPTION="powerConsumption";
    public static final String ROOM_NO="roomNo";

    public static final String ROOM_FRAGMENT="roomFragment";
    public static final String IMAGE_POSITION="imagePosition";
    public static final String CHECK_ENTRY="check";//used in php code to check that entry is creating or updating,true for
    // creating and false for updating

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        SharedPreferences sharedPreferences=PreferenceManager.getDefaultSharedPreferences(getApplication());
        final String firstname=sharedPreferences.getString(MainActivity.FIRST_NAME,"NAN");
        final String lastname=sharedPreferences.getString(MainActivity.LAST_NAME,"NAN");
        final String email=sharedPreferences.getString(MainActivity.EMAIL,"noEmail");

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View hview = navigationView.getHeaderView(0);
        ImageView nav_userImage=(ImageView)hview.findViewById(R.id.imageView);
        TextView userNameView=(TextView)hview.findViewById(R.id.userName);
        TextView userEmailIdView=(TextView)hview.findViewById(R.id.userEmailId);

        String userNameText=firstname.substring(0,1).toUpperCase()+firstname.substring(1)+" "+lastname;//capitalizing firstletter of name
        userNameView.setText(userNameText);
        userEmailIdView.setText(email);
        nav_userImage.setImageResource(R.drawable.user_image);

        navigationView.setNavigationItemSelectedListener(this);

        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();

        RoomFragment roomFragment=new RoomFragment();
        fragmentTransaction.replace(R.id.main_frame_layout,roomFragment);
        fragmentTransaction.commit();


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }else{
            FragmentManager fm=getSupportFragmentManager();
            int count=fm.getBackStackEntryCount();
            if(count>0){
                fm.popBackStackImmediate();
            }else{
                final Dialog dialog=new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.custom_dialog1);
               // dialog.setTitle("ALERT");
                dialog.setTitle( Html.fromHtml("<font color='#e63900'>ALERT</font>"));

                TextView positiveView=(TextView) dialog.findViewById(R.id.positiveResponseExit);
                TextView nagativeView=(TextView) dialog.findViewById(R.id.nagativeResponseExit);

                positiveView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MainActivity.super.onBackPressed();
                        dialog.dismiss();
                    }
                });

                nagativeView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //do nothing
                        dialog.dismiss();
                    }
                });

                dialog.show();

            }
        }
    }

    @Override
    protected void onResumeFragments() {

        super.onResumeFragments();
        //refeshing fragment eachtime it get resumed
        FragmentManager fm=getSupportFragmentManager();

        Fragment frg = fm.findFragmentById(R.id.main_frame_layout);

        FragmentTransaction ft = fm.beginTransaction();
        ft.detach(frg);
        ft.attach(frg);
        ft.commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.logout_button) {

            final Dialog dialog=new Dialog(MainActivity.this);
            dialog.setContentView(R.layout.custom_dialog2);
            //dialog.setTitle("ALERT");
            dialog.setTitle( Html.fromHtml("<font color='#e63900'>ALERT</font>"));
            TextView positiveView=(TextView) dialog.findViewById(R.id.positiveResponse);
            TextView nagativeView=(TextView) dialog.findViewById(R.id.nagativeResponse);

            positiveView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplication());
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString(MainActivity.USER_ID,"");
                    editor.putString(MainActivity.FIRST_NAME,"");
                    editor.putString(MainActivity.LAST_NAME,"");
                    //editor.putString(MainActivity.EMAIL,email);
                    //email address is not set to "", so that we can fill the previous email
                    editor.putString(MainActivity.PASSWORD,"");
                    editor.putString(MainActivity.ADDRESS,"");
                    editor.putString(MainActivity.PHONE_NO,"");
                    editor.apply();

                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                    finish();
                    dialog.dismiss();
                }
            });

            nagativeView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //do nothing
                    dialog.dismiss();
                }
            });

            dialog.show();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();

        Fragment fragment=new MainActivityFragment();
        int backstackEntryCnt= fragmentManager.getBackStackEntryCount();
        if(backstackEntryCnt>0){
            for(int i=backstackEntryCnt; i>0;i--) {
                fragmentManager.popBackStackImmediate();
            }
        }

        if (id == R.id.rooms) {
            fragment=new RoomFragment();
        } else if (id == R.id.about_us) {
            fragment=new AboutUsFragment();
        } else if (id == R.id.user_profile){
            fragment=new MyProfileFragment();
        } else if (id == R.id.gallery){
            fragment=new GalleryFragment();
        } else if (id == R.id.device_list){
            fragment=new MainActivityFragment();
        }
        fragmentTransaction.replace(R.id.main_frame_layout,fragment);
        fragmentTransaction.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

