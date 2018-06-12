package com.example.anshulvanawat.onmytips.Other_Fragment_Activities;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.anshulvanawat.onmytips.R;
import com.example.anshulvanawat.onmytips.main_activities.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyProfileFragment extends Fragment {


    private static int RESULT_LOAD_IMAGE = 1;

    TextView EmailTextView;
    TextView addressTextView;
    TextView userNameView;
    TextView userIdTextView;
    TextView contactNoTextView;

    ImageView userImageView;



    public MyProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_my_profile, container, false);

        contactNoTextView = (TextView)view.findViewById(R.id.contactNoText);
        EmailTextView=(TextView)view.findViewById(R.id.emailIdText);
        userNameView=(TextView)view.findViewById(R.id.userNameText);
        userIdTextView=(TextView)view.findViewById(R.id.userIdText);
        addressTextView=(TextView)view.findViewById(R.id.addressText);

        userImageView=(ImageView)view.findViewById(R.id.userImageButton);

        SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(getActivity());

        final String userId=preferences.getString(MainActivity.USER_ID,"NAN");
        final String firstname=preferences.getString(MainActivity.FIRST_NAME,"NAN");
        final String lastname=preferences.getString(MainActivity.LAST_NAME,"NAN");
        final String email=preferences.getString(MainActivity.EMAIL,"noEmail");
        final String password=preferences.getString(MainActivity.PASSWORD,"NAN");
        final String address=preferences.getString(MainActivity.ADDRESS,"NAN");
        final String contactno=preferences.getString(MainActivity.PHONE_NO,"noContact");

        String userName=firstname.substring(0,1).toUpperCase()+firstname.substring(1)+" "+lastname;//capitalizing firstletter of name
        userNameView.setText(userName);
        EmailTextView.setText(email);
        contactNoTextView.setText(contactno);
        userIdTextView.setText(userId);
        addressTextView.setText(address);
        return view;
    }
}
