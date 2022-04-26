package com.example.myapp;



import static java.lang.String.*;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.tabs.TabLayout;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.DisconnectedBufferOptions;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import android.view.View.OnTouchListener;
import android.view.View.OnClickListener;
import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import java.io.UnsupportedEncodingException;
import java.lang.Object;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;


//import androidx.recyclerview.widget.RecyclerView;

//import BroadcastReceiver;


public class MainActivity  extends AppCompatActivity {

    static String hote =String.valueOf(R.id.hôte);
    static String userName = String.valueOf(R.id.NomUtilisateur);
    static String passWord = String.valueOf(R.id.MotDePasse);
    // static String IDClient = "BIBI";
    String IDClient=MqttClient.generateClientId();

    static String DeviceID = valueOf(R.id.deviceID);
    static String TrackerID = String.valueOf(R.id.trackerID);
    static int port = (R.id.port);
    String topic = String.valueOf(R.id.topic);
    MqttAndroidClient client;
    String publishMessage = "Hello World!";

    private Button btn1;
    IMqttActionListener cbconnect;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // On récupère le bouton par son identifiant
        btn1 = (Button) findViewById(R.id.button);


        MqttConnectOptions options = new MqttConnectOptions();
        options.setMqttVersion(MqttConnectOptions.MQTT_VERSION_3_1_1);
        options.setAutomaticReconnect(true);
        options.setUserName(userName);
        options.setPassword(passWord.toCharArray());
        client = new MqttAndroidClient(getApplicationContext(), hote, IDClient);


        try {
            Toast.makeText(MainActivity.this, " username:" + userName, Toast.LENGTH_LONG).show();

           // IMqttToken token = client.connect(options);
            //token.setActionCallback(new IMqttActionListener() {
                client.connect(options, null, new IMqttActionListener() {

                @Override
                public void onSuccess(IMqttToken asyncActionToken) {

                    Toast.makeText(MainActivity.this, " connected:" + hote, Toast.LENGTH_LONG).show();

                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    // ("Failed to connect to: " + serverUri);
                    Toast.makeText(getBaseContext(), "Failed to connect to:" + hote, Toast.LENGTH_LONG).show();

                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }




        btn1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String payload = "the payload";
                byte[] encodedPayload = new byte[0];
                try {
                    encodedPayload = payload.getBytes("UTF-8");
                    MqttMessage message = new MqttMessage(encodedPayload);
                    client.publish(topic, message);
                } catch (UnsupportedEncodingException | MqttException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
