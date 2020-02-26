# 端末が外部電源に接続したのを検出する

## TL; DR

端末を外部電源に接続するor外すタイミングでアプリを起動できるようにします。

## BroadcastReceiverクラス

`BroadcastReceiver `クラスのサブクラスを定義する。

```kotlin
class MyBroadcastReceiver : BroadcastReceiver() {
    /**
     * 電源供給の変化を検出したら、〇〇する
     *
     * @param context The Context in which the receiver is running.
     * @param intent The Intent being received.
     */
    override fun onReceive(context: Context?, intent: Intent?) {
        intent?.let {
            //Check the Intent action and perform the required operation
            if (it.action.equals(Intent.ACTION_POWER_DISCONNECTED)) {
            	//外部電源を外したとき
                Log.d("MyBroadcastReceiver", "ACTION_POWER_DISCONNECTED")

            } else if (it.action.equals(Intent.ACTION_POWER_CONNECTED)) {
            	//外部電源を接続したとき
                Log.d("MyBroadcastReceiver", "ACTION_POWER_CONNECTED")
                startActivity("ACTION_POWER_CONNECTED", context)
            }
        }
    }
}
```

サンプルアプリでは、外部電源の供給が開始・終了したタイミングでアプリを起動している。

## BroadcastReceiverを登録する

システムイベントを受信するには、定義したBroadcastReceiverを登録する必要がある。

AndroidManifestでの静的な登録は、一部を除いて無効化された。

参考：[暗黙的なブロードキャストの例外  |  Android デベロッパー  |  Android Developers](https://developer.android.com/guide/components/broadcast-exceptions)

ここでは、アプリ起動後に動的にBroadcastReceiverを登録する方法を紹介する。

```kotlin
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        registerBroadcastReceivers()
    }
    
    /**
    * BroadcastReceiverを登録する
    */
    private fun registerBroadcastReceivers() {
        val intentFilter = IntentFilter().apply {
            addAction(Intent.ACTION_POWER_DISCONNECTED)
            addAction(Intent.ACTION_POWER_CONNECTED)
        }
        application.registerReceiver(MyBroadcastReceiver(), intentFilter)
    }
}
```

# Reference

[7.3: Broadcasts · GitBook (google-developer-training.github.io)](https://google-developer-training.github.io/android-developer-fundamentals-course-concepts-v2/unit-3-working-in-the-background/lesson-7-background-tasks/7-3-c-broadcasts/7-3-c-broadcasts.html)
