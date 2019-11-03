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
    val timestamp: Long = event.timestamp
    val values: FloatArray = event.values

    /*
    * Sensor Accuracy Object
    * see also https://developer.android.com/reference/android/hardware/SensorManager.html
    * */
    enum class Accuracy {
        LOW, MEDIUM, HIGH, UNKNOWN
    }
}
