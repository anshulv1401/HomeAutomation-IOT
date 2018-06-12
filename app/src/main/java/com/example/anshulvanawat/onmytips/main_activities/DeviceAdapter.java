package com.example.anshulvanawat.onmytips.main_activities;

/**
 * Created by anshul vanawat on 1/7/2017.
 */


import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.anshulvanawat.onmytips.R;


import java.util.ArrayList;


public class DeviceAdapter extends ArrayAdapter<Device> {

    customButtonListener customListener;

    public interface customButtonListener{
        void onButtonClickListener(int position,Device device);
    }

    public void setCustomButtonListener(customButtonListener listener){
        this.customListener=listener;
    }

    private Context context;
    private ArrayList<Device> data=new ArrayList<Device>();


    public DeviceAdapter(Context context, ArrayList<Device> objects) {
        super(context, 0, objects);
        this.data=objects;
        this.context=context;
    }



    public static class ViewHolder {
        ImageView deviceIconView;
        TextView deviceNameView;
        Switch switchView;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        //get data for this position
        Device device = getItem(position);

        ViewHolder viewHolder;
        if (convertView == null) {

            viewHolder = new ViewHolder();

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_device_list, parent, false);
            viewHolder.deviceIconView = (ImageView) convertView.findViewById(R.id.device_icon_view);
            viewHolder.deviceNameView = (TextView) convertView.findViewById(R.id.device_name_view);
            viewHolder.switchView = (Switch) convertView.findViewById(R.id.switch1);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.deviceIconView.setImageResource(device.getAssociateSubjectDrawable(device.getStatus()));
        viewHolder.deviceNameView.setText(device.getDeviceName());
        viewHolder.switchView.setChecked(device.getStatus().equalsIgnoreCase(MainActivity.STATE_ON)?true:false);
        final Device temp = getItem(position);
        viewHolder.switchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (customListener != null) {
                    customListener.onButtonClickListener(position, temp);
                }
            }
        });
        return convertView;
    }
}


