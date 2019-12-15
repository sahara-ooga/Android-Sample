package com.example.flow

import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.conflate

fun TextView.textChangeAsFlow(): Flow<String?> =
    channelFlow<String?> {
        channel.offer(text.toString())
        val textWatcher = addTextChangedListener {
            channel.offer(it?.toString())
        }
        awaitClose {
            removeTextChangedListener(textWatcher)
        }
    }.conflate()
