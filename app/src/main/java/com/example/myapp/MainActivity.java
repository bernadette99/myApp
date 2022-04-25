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
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;

import android.view.View.OnTouchListener;
import android.view.View.OnClickListener;
import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import java.lang.Object;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

//import BroadcastReceiver;


public class MainActivity  extends AppCompatActivity  {

    static String hote = String.valueOf(R.id.hôte);
    static String userName = String.valueOf(R.id.NomUtilisateur);
    static String passWord = String.valueOf(R.id.MotDePasse);
    static String IDClient = valueOf(R.id.IdClient);
    static String DeviceID = valueOf(R.id.deviceID);
    static String TrackerID = String.valueOf(R.id.trackerID);
    static int port = (R.id.port);
    String topic = String.valueOf(R.id.topic);
    MqttAndroidClient client;
    private final Button b = null;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // On récupère le bouton par son identifiant
        // b = (Button) findViewById(R.id.button);
        // Puis on lui indique que cette classe sera son listener pour l'évènement Touch

        // b.setOnTouchListener(this);

        // permet de générer l'id du client pour établier la connexion
        String clientID = MqttClient.generateClientId();
        client = new MqttAndroidClient(this.getApplicationContext(), hote, clientID);
        MqttConnectOptions options = new MqttConnectOptions();
        options.setUserName(userName);
        options.setPassword(passWord.toCharArray());

        //MqttAndroidClient client = new MqttAndroidClient(this.getApplicationContext(), hote, clientID);

        try {
            IMqttToken token = client.connect(options);
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    //we are connected
                    Toast.makeText(getBaseContext(), "Connecté", Toast.LENGTH_LONG).show();
                }


                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    //somethin went wrong e.g. connection timeout or firewell problems
                    Toast.makeText(getBaseContext(), "connexion non établie", Toast.LENGTH_LONG).show();
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }

    }
    // Fonction qui sera lancée à chaque fois qu'un toucher est détecté sur le bouton rattaché



    public void Test1(View v){
        String topic1 = topic;
        String message = "L1AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
        try {
            client.publish(topic1, message.getBytes(), 0, false);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}