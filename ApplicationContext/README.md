# ApplicationContextを好きな場所から呼び出す

## TL; DR

`getApplicationContext`で呼び出す`Context`オブジェクトをどこからでも取得できるようになります。

        val applicationContext = MyContext.applicationContext


## Applicationクラス

`Application`クラスのサブクラスを定義する。

```kotlin
class MyApplication : Application() {
    //AndroidManifestで設定していれば、こちらのonCreateが呼ばれる
    override fun onCreate() {
        super.onCreate()
        MyContext.onCreateApplication(applicationContext)
    }
}
```

`AndroidManifest.xml`に、`MyApplication`クラスの情報を追記する。

```xml
    <application
        android:allowBackup="true"
        ...
        android:name=".MyApplication">  //ここに追加
        <activity android:name=".MainActivity" >
            ...
        </activity>
    </application>
```

## MyContextクラス

```kotlin
class MyContext private constructor(private val context: Context) {
    companion object {
        private lateinit var myContext: MyContext

        fun onCreateApplication(context: Context) {
            this.myContext = MyContext(context)
        }

        val applicationContext: Context
            get() = this.myContext.context
    }
}
```

## 検証

```kotlin
//Robolectric使用の単体テストコード
@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.O])
class ExampleUnitTest {
    @Test
    fun test_context() {
        val applicationContext = MyContext.applicationContext
        // Logcatに表示される：
        // D/Context: com.example.applicationcontext.MyApplication@c4fb85e
    }
}
```
