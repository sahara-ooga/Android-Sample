package example.android.lifecycle_sample

import android.app.Application
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry

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