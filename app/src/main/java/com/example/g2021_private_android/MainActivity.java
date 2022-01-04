package com.example.g2021_private_android;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.AudioDeviceInfo;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.g2021_private_android.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private AudioManager myAudioManager;
    private TextView myTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myAudioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        myTextView = (TextView)findViewById(R.id.textView0);
    }

    @Override
    protected void onResume() {
        super.onResume();
        String temp = myShowAudioDeviceInfo();
        myTextView.setText(temp);
    }

    private String myShowAudioDeviceInfo() {
        @SuppressLint("WrongConstant") AudioDeviceInfo[] audioDevices = myAudioManager.getDevices(AudioManager.GET_DEVICES_ALL);

        StringBuffer myDeviceInfo = new StringBuffer("搭載デバイス一覧");

        for (AudioDeviceInfo audio_device : audioDevices) {
            myDeviceInfo.append("\n------------------------------------------------------");

            myDeviceInfo.append("\n getChannelCounts () -> ");
            myDeviceInfo.append(Arrays.toString(audio_device.getChannelCounts()));

            myDeviceInfo.append("\n getChannelIndexMasks () -> ");
            myDeviceInfo.append(Arrays.toString(audio_device.getChannelIndexMasks()));

            myDeviceInfo.append("\n getChannelMasks () -> ");
            for(int temp : audio_device.getChannelMasks()){
                myDeviceInfo.append("\n  " + temp +" : ");
                myDeviceInfo.append(myChannelInfoToReadableString(temp));
            }

            myDeviceInfo.append("\n getEncodings () -> ");
            for(int temp : audio_device.getEncodings()){
                myDeviceInfo.append("\n  " + temp +" : ");
                myDeviceInfo.append(myEncodingInfoToReadableString(temp));
            }

            myDeviceInfo.append("\n getId () -> ");
            myDeviceInfo.append(audio_device.getId());

            myDeviceInfo.append("\n getProductName () -> ");
            myDeviceInfo.append(audio_device.getProductName());

            myDeviceInfo.append("\n getSampleRates () -> ");
            myDeviceInfo.append(Arrays.toString(audio_device.getSampleRates()));

            myDeviceInfo.append("\n getType () -> ");
            int temp = audio_device.getType();
            myDeviceInfo.append("\n  " + temp +" : ");
            myDeviceInfo.append(myAudioTypeToReadableString(temp));

            myDeviceInfo.append("\n isSink () -> ");
            myDeviceInfo.append(audio_device.isSink());

            myDeviceInfo.append("\n isSource () -> ");
            myDeviceInfo.append(audio_device.isSource());
        }
        return myDeviceInfo.toString();
    }

    private String myChannelInfoToReadableString(int format){
        //https://developer.android.com/reference/android/media/AudioFormat.html
        switch (format){
            case 0:
                return "CHANNEL_INVALID";
            case 1:
                return "CHANNEL_IN_DEFAULT / CHANNEL_OUT_DEFAULT";
            //～省略～
            case 12:
                return"CHANNEL_IN_STEREO / CHANNEL_OUT_STEREO";
            //～省略～
            case 16:
                return "CHANNEL_IN_FRONT / CHANNEL_IN_MONO/CHANNEL_OUT_FRONT_CENTER";
            //～省略～
            default:
                return "規定なし(" + format +")";
        }
    }

    private String myEncodingInfoToReadableString(int format){
        //https://developer.android.com/reference/android/media/AudioFormat.html
        switch (format){
            case 0:
                return "ENCODING_INVALID";
            case 1:
                return "ENCODING_DEFAULT";
            case 2:
                return "ENCODING_PCM_16BIT";
            //～省略～
            default:
                return "規定なし(" + format +")";
        }
    }


    private String myAudioTypeToReadableString(int type){
        //https://developer.android.com/reference/android/media/AudioDeviceInfo.html
        switch(type){
            case 1:
                return "TYPE_BUILTIN_EARPIECE";
            case 2:
                return "TYPE_BUILTIN_SPEAKER";
            case 3:
                return "TYPE_WIRED_HEADSET";
            case 4:
                return "TYPE_WIRED_HEADPHONES";
            case 5:
                return "TYPE_LINE_ANALOG";
            case 6:
                return "TYPE_LINE_DIGITAL";
            case 7:
                return "TYPE_BLUETOOTH_SCO";
            case 8:
                return "TYPE_BLUETOOTH_A2DP";
            //～省略～
            case 15:
                return "TYPE_BUILTIN_MIC";
            case 16:
                return "TYPE_FM_TUNER";
            case 17:
                return "TYPE_TV_TUNER";
            case 18:
                return "TYPE_TELEPHONY";
            //～省略～
            default:    //0
                return "TYPE_UNKNOWN";
        }
    }
}