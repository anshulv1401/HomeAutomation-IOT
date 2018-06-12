package com.example.anshulvanawat.onmytips.main_activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.anshulvanawat.onmytips.R;
import com.example.anshulvanawat.onmytips.WebConnector;

public class DeviceDetail extends AppCompatActivity {

    TextView deviceIdView,deviceNameView,roomNoView,powerConsumptionView;
    ToggleButton statusToggleButton;

    String deviceId,deviceName,roomNo,powerConsumption,status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_detail);
        deviceIdView=(TextView)findViewById(R.id.idText);
        deviceNameView=(TextView)findViewById(R.id.deviceNameText);
        roomNoView=(TextView)findViewById(R.id.roomNoText);
        powerConsumptionView=(TextView)findViewById(R.id.powerText);
        statusToggleButton=(ToggleButton)findViewById(R.id.statusToggle);
        statusToggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                WebConnector wc=new WebConnector();
                boolean debug=wc.switchStatus(status,deviceId);
                if(status.equalsIgnoreCase(MainActivity.STATE_ON)){//device is in on state, hence switch it off
                    status=MainActivity.STATE_OFF;
                    Toast.makeText(getApplicationContext(), deviceName+" switched off",
                            Toast.LENGTH_SHORT).show();
                }else {
                    status=MainActivity.STATE_ON;
                    Toast.makeText(getApplicationContext(), deviceName+" switched on",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        Intent intent=getIntent();

        deviceId=intent.getExtras().getString(MainActivity.DEVICE_ID);
        deviceName=intent.getExtras().getString(MainActivity.DEVICE_NAME);
        roomNo=intent.getExtras().getString(MainActivity.ROOM_NO);
        powerConsumption=intent.getExtras().getString(MainActivity.POWER_CONSUPTION);
        status=intent.getExtras().getString(MainActivity.STATUS);

        deviceIdView.setText(deviceId);
        deviceNameView.setText(deviceName);
        roomNoView.setText(roomNo);
        powerConsumptionView.setText(powerConsumption);
        statusToggleButton.setChecked(status.equalsIgnoreCase(MainActivity.STATE_ON)?true:false);
    }
}
