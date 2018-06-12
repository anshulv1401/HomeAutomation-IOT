package com.example.anshulvanawat.onmytips;


/**
 * Created by anshul vanawat on 1/24/2017.
 */
import android.os.StrictMode;
import android.util.Log;

import com.example.anshulvanawat.onmytips.main_activities.Device;
import com.example.anshulvanawat.onmytips.main_activities.MainActivity;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class WebConnector {

    public static String url = "http://192.168.43.242/onmytip/";//do not give any space in th url
//


    public HttpResponse sendData(List<NameValuePair> list, String url) throws Exception {
        try {


            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(url);
            httppost.setEntity(new UrlEncodedFormEntity(list));
            HttpResponse response = httpclient.execute(httppost);//getting org.apache.http.conn.HttpHostConnectException here

            return response;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("something went wrong");

        }
        throw new Exception();
    }


    public String getDataFromResponse(HttpResponse response) throws Exception {
        try {


            InputStream is = response.getEntity().getContent();


            if (is != null) {
                BufferedReader br = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");
                }
                is.close();
                return sb.toString();
            }
        } catch (ClientProtocolException e) {
            System.out.println("fail Client failure");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("fail IO failure");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Something went wrong");
        }
        throw new Exception();

    }

    public Boolean checkConnection() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        String url = this.url + "checkConnection.php";

        List<NameValuePair> list = new ArrayList<>();
        //list.add(new BasicNameValuePair(MainActivity.CHECK, "status"));


        try {

            HttpResponse response = sendData(list, url);
            String result = getDataFromResponse(response);
            Log.i("testing",result+"");
            if (result.contains("connected")) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            Log.i("testing","not here");
            e.printStackTrace();
            return false;
        }

    }

    public ArrayList<Device> getDevicesList(String room_no) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        String url = this.url + "getDevices.php";

        List<NameValuePair> list = new ArrayList<NameValuePair>();
        list.add(new BasicNameValuePair(MainActivity.ROOM_NO, room_no));

        ArrayList<Device> sarray = new ArrayList<>();

        try {
            HttpResponse response = sendData(list, url);

            String result = getDataFromResponse(response);
            JSONObject jobj = new JSONObject(result);
            JSONArray jarray = jobj.getJSONArray("devices");

            for (int i = 0; i < jarray.length(); i++) {

                Device device=new Device(jarray.getJSONObject(i).get(MainActivity.DEVICE_ID).toString(),jarray.getJSONObject(i).get(MainActivity.DEVICE_NAME).toString(),
                        jarray.getJSONObject(i).get(MainActivity.STATUS).toString(),jarray.getJSONObject(i).get(MainActivity.ON_TIME).toString()
                        ,jarray.getJSONObject(i).get(MainActivity.ROOM_NO).toString(),jarray.getJSONObject(i).get(MainActivity.POWER_CONSUPTION).toString());
                sarray.add(device);
            }
            return sarray;
        } catch (Exception e) {
            Device device=new Device("","ERROR","","0","","");//show error
            sarray.add(device);
            e.printStackTrace();
            return sarray;
        }


    }

    public boolean switchStatus(String status,String deviceId){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        String url = this.url + "switchStatus.php";

        List<NameValuePair> list = new ArrayList<NameValuePair>();
        list.add(new BasicNameValuePair(MainActivity.STATUS, status));
        list.add(new BasicNameValuePair(MainActivity.DEVICE_ID,deviceId));
        try {
            HttpResponse response = sendData(list, url);
            String result = getDataFromResponse(response);
            Log.i("result",result);
            if(result.contains("success")){
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


    public String checkUser(String email, String password) throws Exception {


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        String result = "";


        List<NameValuePair> list = new ArrayList<NameValuePair>();

        list.add(new BasicNameValuePair(MainActivity.EMAIL, email));
        list.add(new BasicNameValuePair(MainActivity.PASSWORD, password));

        String url = this.url + "login.php";


        try {
            HttpResponse response = sendData(list, url);
            result = getDataFromResponse(response);

        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            String userInfoArray=getUserInfo(result);
            return userInfoArray;
        } catch (Exception e) {
            e.printStackTrace();
        }

        throw new Exception();
    }


    public String getUserInfo(String result) throws Exception {

        try {
            Log.i("result",result);
            JSONObject jobj=new JSONObject(result);
            JSONArray jarray=jobj.getJSONArray("userdetails");
            String sarray;
            sarray=jarray.getJSONObject(0).get(MainActivity.USER_ID).toString()
                    +"~"+jarray.getJSONObject(0).get(MainActivity.FIRST_NAME).toString()
                    +"~"+jarray.getJSONObject(0).get(MainActivity.LAST_NAME).toString()
                    +"~"+jarray.getJSONObject(0).get(MainActivity.EMAIL).toString()
                    +"~"+jarray.getJSONObject(0).get(MainActivity.PASSWORD).toString()
                    +"~"+jarray.getJSONObject(0).get(MainActivity.PHONE_NO).toString()
                    +"~"+jarray.getJSONObject(0).get(MainActivity.ADDRESS).toString();

            return sarray;
        } catch (JSONException e) {
            e.printStackTrace();

        }catch (Exception e){
            e.printStackTrace();
        }
        throw new Exception();
    }
}