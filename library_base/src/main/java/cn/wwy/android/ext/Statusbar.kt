/*
 * Copyright (C) 2018 Drake, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
@file:Suppress("INVISIBLE_REFERENCE", "INVISIBLE_MEMBER")

package cn.wwy.android.ext

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings
import com.dylanc.longan.*

/**
 * 如果当前设备存在导航栏返回导航栏高度, 否则0
 */
inline val Context?.navigationBarHeight: Int
    @SuppressLint("InternalInsetResource", "DiscouragedApi")
    get() {
        this ?: return 0
        val rid: Int =
            resources.getIdentifier("config_showNavigationBar", "bool", "android")
        var height = 0
        if (rid != 0) {
            val resourceId: Int =
                resources.getIdentifier("navigation_bar_height", "dimen", "android")
            if (resourceId > 0) {
                height = resources.getDimensionPixelSize(resourceId)
            }
        }
        return height
    }

inline val Context.paddingBottomHeight: Int
    get() {
        return if (isNavBarHide(this) == 0 || isNavBarHide(this) == -1) {
            navigationBarHeight
        } else {
            0
        }
    }

/**
 * 判断设备是否显示NavigationBar
 *
 * @return 其他值 不显示 0显示 -1 未知
 */
fun isNavBarHide(context: Context): Int {
    // 有虚拟键，判断是否显示
    if (isVivoRom) {
        return vivoNavigationEnabled(context)
    }
    if (isOppoRom) {
        return oppoNavigationEnabled(context)
    }
    if (isXiaomiRom) {
        return xiaomiNavigationEnabled(context)
    }
    if (isHuaweiRom or isHonorRom) {
        return huaWeiNavigationEnabled(context)
    }
    if (isOnePlusRom) {
        return oneplusNavigationEnabled(context)
    }
    if (isSamsungRom) {
        return samsungNavigationEnabled(context)
    }
    if (isSmartisanRom) {
        return smartisanNavigationEnabled(context)
    }
    // navigation_mode 三种模式均有导航栏，只是高度不同。
    return if (isGoogleRom) {
        0
    } else -1
}

inline val isHonorRom: Boolean get() = isRomOf("honor")

/**
 * 判断当前系统是使用导航键还是手势导航操作
 *
 * @param context
 * @return 0 表示使用的是虚拟导航键，1 表示使用的是手势导航，默认是0
 */
fun vivoNavigationEnabled(context: Context): Int {
    return Settings.Secure.getInt(context.contentResolver, "navigation_gesture_on", 0)
}

fun oppoNavigationEnabled(context: Context): Int {
    return Settings.Secure.getInt(context.contentResolver, "hide_navigationbar_enable", 0)
}

fun xiaomiNavigationEnabled(context: Context): Int {
    return Settings.Global.getInt(context.contentResolver, "force_fsg_nav_bar", 0)
}

private fun huaWeiNavigationEnabled(context: Context): Int {
    return Settings.Global.getInt(context.contentResolver, "navigationbar_is_min", 0)
}

/**
 * @param context
 * @return 0虚拟导航键  2为手势导航
 */
private fun oneplusNavigationEnabled(context: Context): Int {
    val result = Settings.Secure.getInt(context.contentResolver, "navigation_mode", 0)
    if (result == 2) {
        // 两种手势 0有按钮， 1没有按钮
        if (Settings.System.getInt(
                context.contentResolver,
                "buttons_show_on_screen_navkeys",
                0
            ) != 0
        ) {
            return 0
        }
    }
    return result
}

fun samsungNavigationEnabled(context: Context): Int {
    return Settings.Global.getInt(context.contentResolver, "navigationbar_hide_bar_enabled", 0)
}

fun smartisanNavigationEnabled(context: Context): Int {
    return Settings.Global.getInt(context.contentResolver, "navigationbar_trigger_mode", 0)
}

fun nokiaNavigationEnabled(context: Context): Int {
    val enabled =
        Settings.Secure.getInt(context.contentResolver, "swipe_up_to_switch_apps_enabled", 0) != 0
    val hide =
        Settings.Secure.getInt(context.contentResolver, "navigation_bar_can_hiden", 0) != 0
    return if (enabled || hide) {
        1
    } else {
        0
    }
}








