package com.example.androidmotiondetect

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.lifecycle.LiveData

enum class Motion {
    MOVING, STILL
}

class MotionLiveData(
    context: Context,
    private val sensorDelay: Int = SensorManager.SENSOR_DELAY_UI
) : LiveData<Motion>() {
    private val sensorManager: SensorManager =
        context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    private val motionSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MOTION_DETECT)

    private val motionSensorListener =
        object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent?) {
                event?.let { event ->
                    if (event.sensor == motionSensor) {
                        val motionReading = FloatArray(1)
                        System.arraycopy(
                            event.values, 0,
                            motionReading, 0, motionReading.size
                        )
                        val motionFlag = motionReading[0]
                        value = when (motionFlag) {
                            1.0f -> Motion.MOVING
                            else -> Motion.STILL
                        }
                    }
                }
            }

            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
        }

    override fun onActive() {
        super.onActive()
        sensorManager.registerListener(
            motionSensorListener,
            motionSensor,
            SensorManager.SENSOR_DELAY_NORMAL,
            sensorDelay
        )
    }

    override fun onInactive() {
        super.onInactive()
        sensorManager.unregisterListener(motionSensorListener)
    }
}