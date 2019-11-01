package com.example.sensordata

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.lifecycle.LiveData

class SensorLiveData(
    context: Context,
    //監視したいセンサーの番号を指定する
    private val sensorNumbers: Array<Int> = SensorUtil().sensorNumbers,
    private val sensorDelay: Int = SensorManager.SENSOR_DELAY_UI
) : LiveData<SensorData>() {

    private val sensorManager: SensorManager =
        context.getSystemService(Context.SENSOR_SERVICE) as SensorManager

    // センサーの値の変化を受け取る
    // センサーの値を受け取ったら、valueのプロパティを更新する
    private val sensorListener =
        object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent?) {
                event?.let { sensorEvent ->
                    val sensorEventObject = SensorEventObject(sensorEvent)
                    when (sensorEvent.sensor.type) {
                        Sensor.TYPE_ACCELEROMETER ->
                            value?.accelerometer = sensorEventObject

                        Sensor.TYPE_GYROSCOPE ->
                            value?.gyroscope = SensorEventObject(sensorEvent)

                        Sensor.TYPE_GYROSCOPE_UNCALIBRATED ->
                            value?.gyroscopeUncalibrated = SensorEventObject(sensorEvent)

                        Sensor.TYPE_AMBIENT_TEMPERATURE ->
                            value?.ambientTemprature = SensorEventObject(sensorEvent)

                        Sensor.TYPE_GAME_ROTATION_VECTOR ->
                            value?.gameRotationVector = SensorEventObject(sensorEvent)

                        Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR ->
                            value?.geomagneticRotationVector = SensorEventObject(sensorEvent)

                        Sensor.TYPE_GRAVITY ->
                            value?.gravity = SensorEventObject(sensorEvent)

                        Sensor.TYPE_HEART_BEAT ->
                            value?.heartBeat = SensorEventObject(sensorEvent)

                        Sensor.TYPE_HEART_RATE ->
                            value?.heartRate = SensorEventObject(sensorEvent)

                        Sensor.TYPE_LIGHT ->
                            value?.light = SensorEventObject(sensorEvent)

                        Sensor.TYPE_LINEAR_ACCELERATION ->
                            value?.linearAcceleration = SensorEventObject(sensorEvent)

                        Sensor.TYPE_MAGNETIC_FIELD ->
                            value?.magneticField = SensorEventObject(sensorEvent)

                        Sensor.TYPE_MAGNETIC_FIELD_UNCALIBRATED ->
                            value?.magneticFieldUncalibrated = SensorEventObject(sensorEvent)

                        Sensor.TYPE_MOTION_DETECT ->
                            value?.motionDetect = SensorEventObject(sensorEvent)

                        Sensor.TYPE_POSE_6DOF ->
                            value?.pose6DOF = SensorEventObject(sensorEvent)

                        Sensor.TYPE_PRESSURE ->
                            value?.pressure = SensorEventObject(sensorEvent)

                        Sensor.TYPE_PROXIMITY ->
                            value?.proximity = SensorEventObject(sensorEvent)

                        Sensor.TYPE_RELATIVE_HUMIDITY ->
                            value?.relativeHumidity = SensorEventObject(sensorEvent)

                        Sensor.TYPE_ROTATION_VECTOR ->
                            value?.rotationVector = SensorEventObject(sensorEvent)

                        Sensor.TYPE_SIGNIFICANT_MOTION ->
                            value?.significantMotion = SensorEventObject(sensorEvent)

                        Sensor.TYPE_STEP_COUNTER ->
                            value?.stepCounter = SensorEventObject(sensorEvent)

                        Sensor.TYPE_STEP_DETECTOR ->
                            value?.stepDetector = SensorEventObject(sensorEvent)

                        else ->
                            println("undefined sensor")
                    }
                }
            }

            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
        }

    // MARK: TriggerEvent
    private val haveTriggerEvent: Boolean
        get() = sensorNumbers.contains(Sensor.TYPE_SIGNIFICANT_MOTION)

    private var triggerSensor: Sensor? = null
    private val triggerListener by lazy { TriggerListener() }

    // MARK: Lifecycle
    override fun onActive() {
        super.onActive()
        registerListener(sensorNumbers)
        this.value = SensorData()//これをしないとvalueがnullのまま
    }

    override fun onInactive() {
        super.onInactive()

        sensorManager.unregisterListener(sensorListener)

        if (haveTriggerEvent) {
            sensorManager.cancelTriggerSensor(this.triggerListener, this.triggerSensor)
        }
    }

    // MARK: private function
    /*
    * 指定されたセンサーを監視する
    * */
    private fun registerListener(sensorNumbers: Array<Int>) {
        for (sensorNo in sensorNumbers) {
            val wrappedSensor = sensorManager.getDefaultSensor(sensorNo)

            wrappedSensor?.let { sensor ->
                if (sensorNo == Sensor.TYPE_SIGNIFICANT_MOTION) {
                    //cancelTriggerSensor時に必要になるため、Sensorインスタンスを保持しておく
                    this.triggerSensor = sensor
                    sensorManager.requestTriggerSensor(
                        triggerListener,
                        sensor
                    )

                } else {

                    sensorManager.registerListener(
                        sensorListener,
                        sensor,
                        SensorManager.SENSOR_DELAY_NORMAL,
                        sensorDelay
                    )

                }
            }
        }
    }
}