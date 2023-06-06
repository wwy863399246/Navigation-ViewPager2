package com.example.myapplication.ui.base

import android.app.Application
import android.content.Context
import cn.wwy.android.ext.DataStoreUtils
import com.airbnb.mvrx.Mavericks
import dagger.hilt.android.HiltAndroidApp
import kotlin.properties.Delegates


/**
 *@创建者   wwy
 *@创建时间 2021/9/15 11:42
 *@描述
 */

@HiltAndroidApp
class MyApplication : Application() {
    companion object {
        var CONTEXT: Context by Delegates.notNull()

    }

    override fun onCreate() {
        CONTEXT = applicationContext
        super.onCreate()
        DataStoreUtils.init(CONTEXT)
        Mavericks.initialize(this)
    }

}