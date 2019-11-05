package com.example.sensordata

import android.hardware.Sensor

class SensorUtil {
    val sensorNumbers = arrayOf(
        Sensor.TYPE_ACCELEROMETER,
        Sensor.TYPE_ACCELEROMETER_UNCALIBRATED,//this require minimal api 26
//        Sensor.TYPE_ALL,
        Sensor.TYPE_AMBIENT_TEMPERATURE,
        Sensor.TYPE_DEVICE_PRIVATE_BASE,
        Sensor.TYPE_GAME_ROTATION_VECTOR,
        Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR,
        Sensor.TYPE_GRAVITY,
        Sensor.TYPE_GYROSCOPE,
        Sensor.TYPE_GYROSCOPE_UNCALIBRATED,
        Sensor.TYPE_HEART_BEAT,
        Sensor.TYPE_HEART_RATE,
        Sensor.TYPE_LIGHT,
        Sensor.TYPE_LINEAR_ACCELERATION,
        Sensor.TYPE_LOW_LATENCY_OFFBODY_DETECT,
        Sensor.TYPE_MAGNETIC_FIELD,
        Sensor.TYPE_MAGNETIC_FIELD_UNCALIBRATED,
        Sensor.TYPE_MOTION_DETECT,
        //Sensor.TYPE_ORIENTATION, duplicated. use SensorManager.getOrientation() instead.
        Sensor.TYPE_POSE_6DOF,
        Sensor.TYPE_PRESSURE,
        Sensor.TYPE_PROXIMITY,
        Sensor.TYPE_RELATIVE_HUMIDITY,
        Sensor.TYPE_ROTATION_VECTOR,
        Sensor.TYPE_SIGNIFICANT_MOTION,
        Sensor.TYPE_STATIONARY_DETECT,
        Sensor.TYPE_STEP_COUNTER,
        Sensor.TYPE_STEP_DETECTOR
///        Sensor.TYPE_AMBIENT_TEMPERATURE
    )
}