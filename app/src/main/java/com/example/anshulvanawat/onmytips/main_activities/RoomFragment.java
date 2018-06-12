package com.example.anshulvanawat.onmytips.main_activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.anshulvanawat.onmytips.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RoomFragment extends Fragment implements View.OnClickListener {

    ImageView imageViewRoom1;
    ImageView imageViewRoom2;
    ImageView imageViewRoom3;


    public RoomFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_room, container, false);
        imageViewRoom1=(ImageView) view.findViewById(R.id.imageViewRoom1);
        imageViewRoom2=(ImageView) view.findViewById(R.id.imageViewRoom2);
        imageViewRoom3=(ImageView) view.findViewById(R.id.imageViewRoom3);

        imageViewRoom1.setOnClickListener(this);
        imageViewRoom2.setOnClickListener(this);
        imageViewRoom3.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        int id=view.getId();

        Bundle bundle=new Bundle();
        if(id==R.id.imageViewRoom1){
            bundle.putString(MainActivity.ROOM_NO,"1");
        }else if(id==R.id.imageViewRoom2){
            bundle.putString(MainActivity.ROOM_NO,"2");
        }else if(id==R.id.imageViewRoom3){
            bundle.putString(MainActivity.ROOM_NO,"3");
        }
        MainActivityFragment mainActivityFragment=new MainActivityFragment();
        mainActivityFragment.setArguments(bundle);
        FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_frame_layout,mainActivityFragment).addToBackStack(MainActivity.ROOM_FRAGMENT);
        fragmentTransaction.commit();
    }
}
