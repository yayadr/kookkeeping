package com.gs.bookkeeping.base

import android.app.Application
import android.content.Context

/**
 * author : gengsai
 * date : 2020/10/13
 * description : 
 */
class MyApplication :Application() {

    lateinit var context:Context

    override fun onCreate() {
        super.onCreate()
        context = this
    }

}