package com.example.sensordata

/*
* センサーの情報。
* 端末の環境の状態は変化するにつれ、各センサーの値は変化していく。
* 適宜必要に応じて取捨選択する。
* */
class SensorData {
    var accelerometer: SensorEventObject? = null
    var magneticField: SensorEventObject? = null
    var gyroscope: SensorEventObject? = null
    var gyroscopeUncalibrated: SensorEventObject? = null
    var ambientTemprature: SensorEventObject? = null
    var gameRotationVector: SensorEventObject? = null
    var geomagneticRotationVector: SensorEventObject? = null
    var gravity: SensorEventObject? = null
    var heartBeat: SensorEventObject? = null
    var heartRate: SensorEventObject? = null
    var light: SensorEventObject? = null
    var linearAcceleration: SensorEventObject? = null
    var magneticFieldUncalibrated: SensorEventObject? = null
    var motionDetect: SensorEventObject? = null
    var pose6DOF: SensorEventObject? = null
    var pressure: SensorEventObject? = null
    var proximity: SensorEventObject? = null
    var relativeHumidity: SensorEventObject? = null
    var rotationVector: SensorEventObject? = null
    var significantMotion: SensorEventObject? = null
    var stepCounter: SensorEventObject? = null
    var stepDetector: SensorEventObject? = null
}