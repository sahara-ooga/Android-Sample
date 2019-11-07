package com.example.applicationcontext

import android.app.Application
import android.os.Build
import android.util.Log
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)//Robolectricのものと自動で切り替わる
@Config(sdk = [Build.VERSION_CODES.O]) // 動作対象バージョンの指定ができます
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun aaa() {
//        val application = ApplicationProvider.getApplicationContext<Application>()
        val applicationContext = MyContext.applicationContext
        Log.d("Context", applicationContext.toString())
    }
}
