package com.example.sensordata

import android.hardware.TriggerEvent
import android.hardware.TriggerEventListener


internal class TriggerListener : TriggerEventListener() {
    override fun onTrigger(event: TriggerEvent) {
        // Do Work.

        // As it is a one shot sensor, it will be canceled automatically.
        // SensorManager.requestTriggerSensor(this, mSigMotion); needs to
        // be called again, if needed.

        println("onTriggerが呼ばれたよ")
    }
}