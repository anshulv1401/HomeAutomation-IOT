package com.example.anshulvanawat.onmytips.main_activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;

import com.example.anshulvanawat.onmytips.R;
import com.example.anshulvanawat.onmytips.WebConnector;
import com.example.anshulvanawat.onmytips.main_activities.DeviceAdapter.customButtonListener;

import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivityFragment extends ListFragment implements customButtonListener {

    public MainActivityFragment() {

    }



    private ArrayList<Device> devices;
    private DeviceAdapter deviceAdapter;
    private String room_no;

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);


        Bundle bundle=this.getArguments();
        if(bundle!=null){
            room_no=bundle.getString(MainActivity.ROOM_NO);
        }else{
            room_no="0";//for getting all the devices
        }
        WebConnector wc=new WebConnector();
        try {
            devices=wc.getDevicesList(room_no);
        } catch (Exception e) {
            devices=new ArrayList<>();
            devices.add(new Device("","Not Connected","","","",""));
        }

        deviceAdapter=new DeviceAdapter(getActivity(), devices);
        deviceAdapter.setCustomButtonListener(MainActivityFragment.this);
        setListAdapter(deviceAdapter);

        registerForContextMenu(getListView());

    }


    @Override
    public void onListItemClick(ListView l, View v, int position,long id){
        super.onListItemClick(l,v,position,id);
        launchDeviceDetailActivity(position);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){

        super.onCreateContextMenu(menu,v,menuInfo);
       System.out.print("onCreatecontextMenu");

        MenuInflater menuInflater=getActivity().getMenuInflater();
       menuInflater.inflate(R.menu.long_press_menu,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item){
        System.out.println("onContextItemSelected");
         AdapterView.AdapterContextMenuInfo info=(AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int rowPosition=info.position;
        switch(item.getItemId()) {
            case R.id.open:
                launchDeviceDetailActivity(rowPosition);
                Toast.makeText(getActivity(),"Detail Opened",Toast.LENGTH_SHORT).show();
                return true;

            case R.id.report:
                Toast.makeText(getActivity(),"Device is Reported",Toast.LENGTH_LONG).show();
                return true ;
        }

        return super.onContextItemSelected(item);
}


    private void launchDeviceDetailActivity(int position){

        Device device=(Device) getListAdapter().getItem(position);

        Intent intent = new Intent(getActivity(), DeviceDetail.class);
        intent.putExtra(MainActivity.DEVICE_ID,device.getDeviceId());
        intent.putExtra(MainActivity.DEVICE_NAME,device.getDeviceName());
        intent.putExtra(MainActivity.STATUS, device.getStatus());
        intent.putExtra(MainActivity.ON_TIME,device.getOnTime());
        intent.putExtra(MainActivity.ROOM_NO,device.getRoomNo());
        intent.putExtra(MainActivity.POWER_CONSUPTION,device.getPowerConsumption());
        startActivity(intent);
    }

    @Override
    public void onButtonClickListener(int position, Device device) {

        WebConnector wc=new WebConnector();
        String status=device.getStatus();
        boolean debug=wc.switchStatus(status,device.getDeviceId());//if every thing is right
        if(status.equalsIgnoreCase(MainActivity.STATE_ON)){//device is in on state, hence switch it off
            device.setStatus(MainActivity.STATE_OFF);

            Toast.makeText(getActivity(), device.getDeviceName()+" switched off",
                    Toast.LENGTH_SHORT).show();
        }else {
            device.setStatus(MainActivity.STATE_ON);
            Toast.makeText(getActivity(), device.getDeviceName()+" switched on",
                    Toast.LENGTH_SHORT).show();
        }
        deviceAdapter.notifyDataSetChanged();
    }
}
