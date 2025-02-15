package com.example.ex_13_tempsen;

import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

//import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    EditText e1;
    SensorManager sm;
    Sensor tm;
    Boolean tempAvailable;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1 = (EditText)findViewById(R.id.editTextTextPersonName);
        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if(sm.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE)!=null)

        {
            tm = sm.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
            tempAvailable = true;
        }
        else
        {
            e1.setText("Temperature sensor is not available");
            tempAvailable = false;
        }


    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        e1.setText(sensorEvent.values[0]+"°C");

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onPause() {
        super.onPause();
        if(tempAvailable){
            sm.unregisterListener(this);
        }
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        if(tempAvailable){
            sm.registerListener(this,tm,SensorManager.SENSOR_DELAY_NORMAL);
        }
    }
}
