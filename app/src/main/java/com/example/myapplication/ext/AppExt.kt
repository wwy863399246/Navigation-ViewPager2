package com.example.myapplication.ext

import cn.wwy.android.ext.DataStoreUtils
import com.example.myapplication.R

/**
 *@创建者   wwy
 *@创建时间 2023/4/6 16:47
 *@描述
 */

var myTheme
    set(value) = DataStoreUtils.putSyncData("THEME", value)
    get() = DataStoreUtils.getSyncData("THEME", R.style.AppTheme)