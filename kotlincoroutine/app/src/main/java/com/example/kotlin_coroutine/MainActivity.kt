package com.example.kotlin_coroutine

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.eclipsesource.json.Json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    val URL =
        "http://weather.livedoor.com/forecast/webservice/json/v1?city=400040" //ライブドアお天気webサービス
    var result = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val getButton = findViewById<Button>(R.id.button)
        getButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                onParallelGetButtonClick()
            }
        })
    }

    // TODO: スコープを狭めたい
    // TODO: `Dispatchers`をサブスレッド対応にする
    private fun onParallelGetButtonClick() = GlobalScope.launch(Dispatchers.Main) {
        val http = HttpUtil()
        async(Dispatchers.Default) { http.httpGET1(URL) }.await().let {
            val result = Json.parse(it).asObject()
            val textView = findViewById<TextView>(R.id.textView)
            textView.setText(result.get("description").asObject().get("text").asString())
        }
    }
}
