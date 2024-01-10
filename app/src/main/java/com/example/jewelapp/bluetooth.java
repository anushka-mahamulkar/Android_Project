package com.example.jewelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class bluetooth extends AppCompatActivity {
    CheckBox enable,visible;
    ImageView search_bt;
    TextView bt_name;
    BluetoothAdapter bluetoothAdapter;
    Set<BluetoothDevice> pairedDevices;// Set do not allow duplicates


    ListView lv;

    @SuppressLint({"MissingInflatedId", "MissingPermission"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_bluetooth);
        enable=findViewById(R.id.enable);
        visible=findViewById(R.id.visible);
        search_bt=findViewById(R.id.search_bt);
        bt_name=findViewById(R.id.bt_name);
        lv=findViewById(R.id.lv);

        BluetoothAdapter bluetoothAdapter=BluetoothAdapter.getDefaultAdapter();
        if(bluetoothAdapter==null){
            Toast.makeText(this, "Bluetooh not supported", Toast.LENGTH_SHORT).show();
            finish();
        }






        if(bluetoothAdapter.isEnabled()){
            enable.setChecked(true);
        }


        enable.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    // Switch button is checked, enable Bluetooth
                    if (!bluetoothAdapter.isEnabled()) {
                        Intent btEnable = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                        startActivityForResult(btEnable, 0);
                        Toast.makeText(getApplicationContext(), "Bluetooth Enabled", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Switch button is unchecked, disable Bluetooth
                    if (bluetoothAdapter.isEnabled()) {
                        bluetoothAdapter.disable();
                        Toast.makeText(getApplicationContext(), "Bluetooth Disabled", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        visible.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Intent bt_visible=new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                startActivityForResult(bt_visible,0);
                Toast.makeText(bluetooth.this, "Bluetooth discoverable", Toast.LENGTH_SHORT).show();
            }
        });
        search_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pairedDevices=bluetoothAdapter.getBondedDevices();//shows paired devices
                ArrayList devices=new ArrayList();


                for (BluetoothDevice bt:pairedDevices){
                    devices.add(bt.getName());
                }
                ArrayAdapter arrayAdapter= new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,devices);
                lv.setAdapter(arrayAdapter);
                bt_name.setText(bluetoothAdapter.getName());
            }
        });

    }
}
