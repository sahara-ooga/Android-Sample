package com.example.sensordata

import android.hardware.SensorEvent
import android.hardware.SensorManager.*

class SensorEventObject(event: SensorEvent) {
    val accuracy: Accuracy = when (event.accuracy) {
        SENSOR_STATUS_ACCURACY_LOW -> Accuracy.LOW
        SENSOR_STATUS_ACCURACY_MEDIUM -> Accuracy.MEDIUM
        SENSOR_STATUS_ACCURACY_HIGH -> Accuracy.HIGH
        else -> Accuracy.UNKNOWN
    }

    //FIXME: 両方null
    val timestamp: Long =
        System.currentTimeMillis() + (event.timestamp - System.nanoTime()) //experimental. see https://stackoverflow.com/questions/5500765/accelerometer-sensorevent-timestamp
    val values: FloatArray = event.values.copyOf()

    /*
    * Sensor Accuracy Object
    * see also https://developer.android.com/reference/android/hardware/SensorManager.html
    * */
    enum class Accuracy {
        LOW, MEDIUM, HIGH, UNKNOWN
    }
}
