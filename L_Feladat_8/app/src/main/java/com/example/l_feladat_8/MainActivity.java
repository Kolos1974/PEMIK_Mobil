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

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    TextView textView;

    private SensorManager sensorManager;
    private Sensor accelometer; // gyorsulásmérő szenzor
    int stepCounter = 0;
    boolean isSensor = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        textView = findViewById(R.id.textView);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager != null) {
            accelometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        }

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        // Magnitudó kiszámolása
        float[] values = sensorEvent.values;
        float x = values[0];
        float y = values[1];
        float z = values[2];

        double magnitudo = Math.sqrt(x*x + y*y + z*z);

        // Lépésnak számít-e?
        if (magnitudo > 15) {
            stepCounter++;
            textView.setText(Integer.toString(stepCounter));
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    protected void onResume() {
        super.onResume();

        if (accelometer != null) {
            sensorManager.registerListener(this, accelometer, SensorManager.SENSOR_DELAY_NORMAL);
            isSensor = true;
        } else {
            // Ha nincs gyorsulásmérő szenzor
        }
    }

    protected void onPause(){
        super.onPause();
        if (isSensor){
            sensorManager.unregisterListener(this);
        }
    }


}