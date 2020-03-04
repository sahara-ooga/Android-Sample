# Lifecycle

## 概要 - abstraction

ライフサイクルをLiveData経由で監視します。

## 関連クラス - Related Class
LifecycleOwner, LifecycleRegistry, LiveData

## 実装の概要

### build.gradle

```groovy
apply plugin: "kotlin-kapt"

...

dependencies {
...
    implementation "android.arch.lifecycle:extensions:1.1.1"
    kapt "android.arch.lifecycle:compiler:1.1.1"
}
```

### LifecycleObserver アプリの状態を掴む

LifecycleObserverを実装します。アプリの状態を監視します。ここにイベント発生時の処理を書くこともできます。

```kotlin : AppLifecycleObserver.kt
/**
 * アプリのライフサイクルイベントを受け取るためのクラス
 *
 * 起動時：onCreate(初回起動時のみ）、onStart, onPause
 * バックグラウンド移行時：onPause, onStop
 * バックグラウンドからの復帰時：onStart, onResume
 * */
class AppLifecycleObserver : LifecycleObserver {

    companion object {
        private val TAG = AppLifecycleObserver::class.java.simpleName
    }

    private val _state: MutableLiveData<State> = MutableLiveData()
    val state: LiveData<State> = _state

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        // 最初の1回のみ
        Log.d(TAG, "ON_CREATE")
        _state.postValue(State.Created)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        // アプリ開始時 or バックグラウンドからの復帰時
        Log.d(TAG, "ON_START")
        _state.postValue(State.Started)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        // アプリ開始時 or バックグラウンドからの復帰時
        Log.d(TAG, "ON_RESUME")
        _state.postValue(State.Resumed)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        // アプリ終了時 or バックグラウンド移行時
        Log.d(TAG, "ON_PAUSE")
        _state.postValue(State.Paused)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        // アプリ終了時 or バックグラウンド移行時
        Log.d(TAG, "ON_STOP")
        _state.postValue(State.Stopped)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        // 呼ばれない
        Log.d(TAG, "ON_DESTROY")
        _state.postValue(State.Destroyed)
    }

    sealed class State {
        object Created : State()
        object Started : State()
        object Resumed : State()
        object Paused : State()
        object Stopped : State()
        object Destroyed : State()
    }
}
```

### LiveData・LifecycleOwner アプリの状態に応じた情報の監視

LifecycleOwnerを実装します。
ここでは、アプリのプロセスが存在している間はLiveDataの監視を行うように設定します。

```kotlin: MyApp.kt
class MyApp : Application(), LifecycleOwner {
    //region LifecycleRegistry
    private val lifecycleRegistry = LifecycleRegistry(this)

    /**
     * Returns the Lifecycle of the provider.
     *
     * @return The lifecycle of the provider.
     */
    override fun getLifecycle() = lifecycleRegistry
    //endregion

    override fun onCreate() {
        super.onCreate()
        //LiveDataの更新開始
        lifecycleRegistry.currentState = Lifecycle.State.STARTED
    }

    override fun onTerminate() {
        super.onTerminate()

        //LiveDataの更新終了
        lifecycleRegistry.currentState = Lifecycle.State.DESTROYED
    }

}
```

起動時にMyAppがインスタンス化されるように、AndroidManifest.xmlに追記します。

```xml
    <application
        android:name=".MyApp"
        android:theme="@style/AppTheme">
        ...
    </application>

```

LiveDataのObserverを実装します。実際にアプリの状態が変化した際に行う処理をActivityなどで記述することができます。

```kotlin:MainActivity.kt
//MainActivity内

		//アプリのライフサイクルを監視開始
        val appLifecycleObserver = AppLifecycleObserver()

        //AppLifecycleの監視自体はアプリのライフサイクルに準ずる
        ProcessLifecycleOwner.get().lifecycle.addObserver(appLifecycleObserver)

        //LiveData<AppLifecycle.State>の監視はApplicationサブクラスのライフサイクルに準じて行われる
        appLifecycleObserver.state.observe(application as MyApp, Observer { state ->
            Log.d(TAG, state.toString())
        })
```

## 開発環境 - Develop Environment

|category | Version|
|---|---|
| Kotlin | 1.3.61 |
| Android Studio | 3.6.1 |

## 参考 - Reference

[LifecycleOwnerを実装して、LiveDataのActive状態をコントロールする - Kenji Abe - Medium](https://medium.com/@star_zero/lifecycleowner%E3%82%92%E5%AE%9F%E8%A3%85%E3%81%97%E3%81%A6-livedata%E3%81%AEactive%E7%8A%B6%E6%85%8B%E3%82%92%E3%82%B3%E3%83%B3%E3%83%88%E3%83%AD%E3%83%BC%E3%83%AB%E3%81%99%E3%82%8B-2aed692f4666)
