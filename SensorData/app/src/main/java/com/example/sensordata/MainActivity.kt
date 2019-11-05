package com.example.sensordata

import android.icu.text.SimpleDateFormat
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView.text = "sensor data updated!"

        //ViewModelの初期化時に渡す引数は、ViewModelProvider.Factoryのサブクラスを使って指定する
        val viewModelFactory = MainViewModelFactory(this.applicationContext)
        this.viewModel = ViewModelProviders.of(this, viewModelFactory)[MainViewModel::class.java]

        // If You need to observe sensor data, user LiveData observe method
        this.viewModel.sensorLiveData.observe(
            this,
            Observer { value ->
//                println("changed!")
//                value.accelerometer?.let {// this accelerometer is null, so this block does not run.😅
//                    SensorUtil.log(it)
//                }
            }
        )
    }

    override fun onPause() {
        super.onPause()

        this.viewModel.sensorLiveData.value?.let { sensorData ->
            // Do something with sensor data
            sensorData.accelerometer?.let { SensorUtil.log(it) }
        }
    }
}