package com.example.counter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

public class NextActivity extends AppCompatActivity {
    TextView number;
    int counter = 0;
    int maxLimit = 100;
    int minLimit = -100;
    Vibrator vibrator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        number = findViewById(R.id.number);

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        Sensor sensorShake = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        SensorEventListener sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                if (sensorEvent != null){
                    float x_eks = sensorEvent.values[0];
                    float y_eks = sensorEvent.values[1];
                    float z_eks = sensorEvent.values[2];

                    if((x_eks > 2) || (x_eks < -2) || (y_eks > 12) || (y_eks < -12) || (z_eks > 2) || (z_eks < -2)){
                        counter = 0;
                        String num = String.valueOf(counter);
                        number.setText(num);
                    }

                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };

        sensorManager.registerListener(sensorEventListener, sensorShake,SensorManager.SENSOR_DELAY_NORMAL);


    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event){
        int action, keycode;

        action = event.getAction();
        keycode = event.getKeyCode();

        switch (keycode){
            case KeyEvent.KEYCODE_VOLUME_UP:
                if (KeyEvent.ACTION_UP == action){
                    counter +=5;
                    String num = String.valueOf(counter);
                    number.setText(num);
                }
                break;
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                if (KeyEvent.ACTION_DOWN == action){
                    counter -=5;
                    String num = String.valueOf(counter);
                    number.setText(num);
                }
                break;
        }
        return super.dispatchKeyEvent(event);
    }

    public void btnClick_eksi(View view){
        counter--;
        String num = String.valueOf(counter);
        number.setText(num);
    }
    public void btnClick_arti(View view){
        counter++;
        String num = String.valueOf(counter);
        number.setText(num);
    }
}