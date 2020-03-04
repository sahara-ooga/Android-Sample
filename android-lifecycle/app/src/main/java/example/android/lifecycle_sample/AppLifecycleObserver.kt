package example.android.lifecycle_sample

import android.util.Log
import androidx.lifecycle.*

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
