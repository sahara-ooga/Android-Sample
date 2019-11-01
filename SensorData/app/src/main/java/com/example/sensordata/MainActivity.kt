package com.example.sensordata

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //ViewModelの初期化時に渡す引数は、ViewModelProvider.Factoryのサブクラスを使って指定する
        val viewModelFactory = MainViewModelFactory(this.applicationContext)
        this.viewModel = ViewModelProviders.of(this, viewModelFactory)[MainViewModel::class.java]

        // If You need to observe sensor data, user LiveData observe method
        this.viewModel.sensorLiveData.observe(
            this,
            Observer { value ->
                if (value == null) return@Observer

                println("sensor data updated!")

                val accuracy = this.viewModel.sensorLiveData.value?.accelerometer?.accuracy
                val timestamp = this.viewModel.sensorLiveData.value?.accelerometer?.timestamp
                val values = this.viewModel.sensorLiveData.value?.accelerometer?.values

                println("accuracy is ${accuracy}, timestamp is $timestamp")
                println("values")
                println(values?.get(0))
                println(values?.get(1))
                println(values?.get(2))

            })
    }

    override fun onPause() {
        super.onPause()

        this.viewModel.sensorLiveData.value?.let { sensorData ->
            // Do something with sensor data
            val accelerometer = sensorData.accelerometer
            println("accelerometer")
            println(accelerometer?.values?.get(0))
            println(accelerometer?.values?.get(0))
            println(accelerometer?.values?.get(0))
            println(accelerometer?.accuracy)
        }
    }
}
