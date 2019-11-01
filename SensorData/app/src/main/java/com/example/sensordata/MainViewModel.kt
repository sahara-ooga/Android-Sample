package com.example.sensordata

import android.content.Context
import androidx.lifecycle.ViewModel

class MainViewModel(context: Context) : ViewModel() {
    val sensorLiveData = SensorLiveData(context)
}