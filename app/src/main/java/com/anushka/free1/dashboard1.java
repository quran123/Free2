package com.anushka.free1;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.util.Log;
import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.nio.channels.DatagramChannel;

public class dashboard1 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG ="asd" ;
     DatagramSocket socket=new DatagramSocket();
    private static final DatagramChannel IO =null;
    TextView t1;
        ImageView img;
        SocketAddress r=new InetSocketAddress("35.166.15.82",8081);
    InetAddress ipAdress        = InetAddress.getLocalHost();
    com.fundroidenterprise.mqtttutorial.Mqtt.MqttHelper mqttHelper;


    public dashboard1() throws SocketException, UnknownHostException {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard1);
        ////////////////////////////////////////////////////


        startMqtt();
        try {
            socket.connect(r);
        } catch (SocketException e) {
            e.printStackTrace();
        }

        /////////////////////////////////////////////////////
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        user user =new user(getIntent().getStringExtra("email"),getIntent().getStringExtra("password"));
        View header = navigationView.getHeaderView(0);
        t1 = (TextView) header.findViewById(R.id.t2);
        img=(ImageView)header.findViewById(R.id.imageView);
        t1.setText(user.getemail());

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dashboard1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    Intent i;
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_dashboard) {
            i=new Intent(getApplicationContext(),dashboard.class);
            startActivity(i);
            // Handle the camera action
        } else if (id == R.id.nav_run_out_status) {
            i=new Intent(getApplicationContext(),runoutstatus.class);
            startActivity(i);

        } else if (id == R.id.nav_stump) {
            i=new Intent(getApplicationContext(),stumpoutstatus.class);
            startActivity(i);

        } else if (id == R.id.nav_camera) {
            i = getPackageManager().getLaunchIntentForPackage("com.ipc360");
            startActivity(i);

        } else if (id == R.id.nav_setting) {
            i=new Intent(getApplicationContext(),setting.class);
            startActivity(i);

        } else if (id == R.id.nav_about) {
            i=new Intent(getApplicationContext(), aboutus.class);
            startActivity(i);

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void startMqtt() {

        mqttHelper = new com.fundroidenterprise.mqtttutorial.Mqtt.MqttHelper(getApplicationContext());

        mqttHelper.mqttAndroidClient.setCallback(new MqttCallbackExtended() {
            @Override
            public void connectComplete(boolean b, String s) {
                Log.w("Debug", "Connected");
                System.out.println("subscriptionTopicSpeed=======5555=====>>>" + s);
                System.out.println("subscriptionTopicSpeed=======6666=====>>>" + b);
            }

            @Override
            public void connectionLost(Throwable throwable) {
                // progressDialog.dismiss();
            }

            @Override
            public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {

                // progressDialog.dismiss();
                System.out.println("mainsmsmsm");
                System.out.println("Topic=============>>>" + topic + "Message=======>>>>" + mqttMessage.toString());

                System.out.println("subscriptionTopicSpeed=======7777=====>>>" + topic);
                System.out.println("subscriptionTopicSpeed=======8888=====>>>" + mqttMessage.toString());

                Log.w("Debug", mqttMessage.toString());
                Log.d(TAG, "Topic==" + topic + " " + "Message==" + mqttMessage.toString());

                String Mqtt_Result = mqttMessage.toString();

                if (Mqtt_Result.equals("0")) {
                    // mqtt_txt.setText("Not Out  "+Mqtt_Result);		System.out.println("Not Out “+Mqtt_Result);
                } else if (Mqtt_Result.equals("1")) {
//                mqtt_txt.setText("Out  "+Mqtt_Result);
                    System.out.println("Out" + Mqtt_Result);
                } else {
                    // mqtt_txt.setText("Result "+Mqtt_Result);
                    System.out.println("Result" + Mqtt_Result);

                }

            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
                //  progressDialog.dismiss();
            }
        });
    }

    {
        socket = IO.socket();
    }
}
