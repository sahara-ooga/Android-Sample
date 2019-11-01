package com.example.sensordata

import android.hardware.SensorEvent

class SensorEventObject(
    val accuracy: Int,
    val timestamp: Long,
    val values: FloatArray
) {
    constructor(event: SensorEvent) : this(event.accuracy, event.timestamp, event.values)
}