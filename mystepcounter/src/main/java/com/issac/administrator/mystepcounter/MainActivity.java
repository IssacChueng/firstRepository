package com.issac.administrator.mystepcounter;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener{

    private TextView tv;
    private SensorManager sensorManager;
    private Sensor linearSensor;
    StringBuilder sbuilder=new StringBuilder();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        tv= (TextView) findViewById(R.id.sensor);
        linearSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        sensorManager.registerListener(this,linearSensor,SensorManager.SENSOR_DELAY_UI);

    }

    @Override
    protected void onDestroy() {
        sensorManager.unregisterListener(this);
        super.onDestroy();
    }

    //传感器的值发生改变回调
    @Override
    public void onSensorChanged(SensorEvent event) {
        float[] values = event.values;
        sbuilder.append("X:"+values[0]);
        sbuilder.append("Y:"+values[1]);
        sbuilder.append("Z:"+values[2]);
        tv.setText(sbuilder.toString());
    }

    //传感器精度发生改变时回调
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
