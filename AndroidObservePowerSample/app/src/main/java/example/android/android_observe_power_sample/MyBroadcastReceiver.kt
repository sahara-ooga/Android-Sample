package example.android.android_observe_power_sample

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log


class MyBroadcastReceiver : BroadcastReceiver() {
    private val OPEN_APP_THRESHOLD: Long = 5
    var lastOpenDate = System.currentTimeMillis()

    /**
     * 電源供給の変化を検出したら、画面を開く
     *
     * @param context The Context in which the receiver is running.
     * @param intent The Intent being received.
     */
    override fun onReceive(context: Context?, intent: Intent?) {
        intent?.let {
            //Check the Intent action and perform the required operation
            if (it.action.equals(Intent.ACTION_POWER_DISCONNECTED)) {
                Log.d("MyBroadcastReceiver", "ACTION_POWER_DISCONNECTED")
                startActivity("ACTION_POWER_DISCONNECTED", context)
            } else if (it.action.equals(Intent.ACTION_POWER_CONNECTED)) {
                Log.d("MyBroadcastReceiver", "ACTION_POWER_CONNECTED")
                startActivity("ACTION_POWER_CONNECTED", context)
            }
        }
    }

    private fun startActivity(message: String, context: Context?) {
        /**
         * 前回の起動から一定時間を経過していればまた起動する
         * */
        val now = System.currentTimeMillis()
        val difsec = (now - lastOpenDate) / 1000L
        if (difsec < OPEN_APP_THRESHOLD) {
            return
        }
        lastOpenDate = now

        val startIntent = Intent(context, MainActivity::class.java).apply {
            putExtra("example.android.android_observe_power_sample", message)
        }

        //フラグをセットしないとアプリが落ちてしまう(ContextがApplication由来でActivityと一致しないため）
        startIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context?.startActivity(startIntent)
    }
}
