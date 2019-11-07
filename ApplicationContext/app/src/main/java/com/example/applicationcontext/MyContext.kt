package com.example.applicationcontext

import android.content.Context

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
