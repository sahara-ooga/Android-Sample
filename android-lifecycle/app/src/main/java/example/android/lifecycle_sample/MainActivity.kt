package example.android.lifecycle_sample

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ProcessLifecycleOwner
import example.android.lifecycle_sample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //View Binding
        setContentView(binding.root)
        binding.textView.text = "test"

        //アプリのライフサイクルを監視開始
        val appLifecycleObserver = AppLifecycleObserver()

        //AppLifecycleの監視自体はアプリのライフサイクルに準ずる
        ProcessLifecycleOwner.get().lifecycle.addObserver(appLifecycleObserver)

        //LiveData<AppLifecycle.State>の監視はApplicationサブクラスのライフサイクルに準じて行われる
        appLifecycleObserver.state.observe(application as MyApp, Observer { state ->
            Log.d(TAG, state.toString())
        })
    }
}
