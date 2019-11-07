package com.example.applicationcontext

import android.app.Application

//Not Object class. Error happen.
class MyApplication : Application() {
    //AndroidManifestで設定していれば、こちらのonCreateが呼ばれる
    override fun onCreate() {
        super.onCreate()
        MyContext.onCreateApplication(applicationContext)
    }
}