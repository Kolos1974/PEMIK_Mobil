package com.example.l_feladat_8;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity implements SensorEventListener {

    TextView textView;

    private SensorManager sensorManager;
    private Sensor lightsensor; // gyorsulásmérő szenzor
    boolean isSensor = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        textView = findViewById(R.id.textView);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager != null) {
            lightsensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        }

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_LIGHT) {
            // Fényerősség
            float lightvalue = sensorEvent.values[0];
            textView.setText(lightvalue + " lx");
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }



    protected void onResume() {
        super.onResume();

        if (lightsensor != null) {
            sensorManager.registerListener(this, lightsensor, SensorManager.SENSOR_DELAY_NORMAL);
            isSensor = true;
        } else {
            // Ha nincs fényszenzor
        }
    }

    protected void onPause(){
        super.onPause();
        if (isSensor){
            sensorManager.unregisterListener(this);
        }
    }
}