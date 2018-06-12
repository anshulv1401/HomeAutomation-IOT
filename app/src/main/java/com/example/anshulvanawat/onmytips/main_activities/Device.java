package com.example.anshulvanawat.onmytips.main_activities;

import com.example.anshulvanawat.onmytips.R;
//import com.example.anshulvanawat.onmytip.R;

/**
 * Created by anshul vanawat on 1/7/2017.
 */

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class Device {


    private String deviceId,deviceName,status,onTime,roomNo,powerConsumption;
 


    public Device(String deviceId, String deviceName, String status, String onTime,String roomNo,String powerConsumption){

        this.deviceId=deviceId;
        this.deviceName=deviceName;

        this.status=status;
        this.onTime=onTime;
        this.roomNo=roomNo;
        this.powerConsumption=powerConsumption;

    }


    public String getDeviceName(){return deviceName;}
    public String getDeviceId(){return deviceId;}
    public String getRoomNo(){
        return roomNo;
    }
    public String getPowerConsumption(){
        return powerConsumption;
    }
    public String getOnTime(){return onTime;}
    public String getStatus(){
        return status;
    }
    public void setStatus(String status){
        this.status=status;
    }

    public String toString(){
        return "ID: "+deviceId+" deviceName: "+ deviceName+" status: "+status+" ontime: "
                +onTime+" roomNo.: "+roomNo+" powerConsumption: "+powerConsumption;
    }
    public int getAssociateSubjectDrawable(String status){

        switch(status){
            case MainActivity.STATE_ON:
                return R.drawable.bulb_on;
            case MainActivity.STATE_OFF:
                return R.drawable.bulb_off;

        }
        return R.mipmap.bulb;

    }


}

