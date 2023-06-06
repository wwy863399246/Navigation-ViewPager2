package com.example.myapplication.ui

import android.os.Bundle
import cn.wwy.android.ext.color
import cn.wwy.android.ui.activity.BaseDBActivity
import cn.wwy.android.windowpreferences.WindowPreferencesManager
import com.dylanc.longan.activityList
import com.dylanc.longan.doOnClick
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentThemeBinding
import com.example.myapplication.ext.myTheme
import com.example.myapplication.ui.main.MainActivity
import com.example.myapplication.ui.rippleAnimation.RippleAnimation
import dagger.hilt.android.AndroidEntryPoint

/**
 *@创建者   wwy
 *@创建时间 2022/6/30 8:52
 *@描述
 */
@AndroidEntryPoint
class ThemeFragment : BaseDBActivity<FragmentThemeBinding>() {
    override fun layoutId(): Int = R.layout.fragment_theme

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(myTheme)
        super.onCreate(savedInstanceState)
        WindowPreferencesManager(this).applyEdgeToEdgePreference(window)
    }

    override fun initView(savedInstanceState: Bundle?) {
        mDatabind.apply {
            materialToolbar.setNavigationOnClickListener {
                finish()
            }
            view1.doOnClick {
                RippleAnimation.create(view1).setDuration(1000).start()
                myAppBar.setBackgroundColor(color(R.color.accent_red))
                myTheme = R.style.AppTheme_Red
                reCreate()
            }
            view2.doOnClick {
                RippleAnimation.create(view2).setDuration(1000).start()
                myAppBar.setBackgroundColor(color(R.color.accent_pink))
                myTheme = R.style.AppTheme_Pink
                reCreate()
            }
            view3.doOnClick {
                RippleAnimation.create(view3).setDuration(1000).start()
                myAppBar.setBackgroundColor(color(R.color.accent_orange))
                myTheme = R.style.AppTheme_Orange
                reCreate()
            }
            view4.doOnClick {
                RippleAnimation.create(view4).setDuration(1000).start()
                myAppBar.setBackgroundColor(color(R.color.accent_pale_blue))
                myTheme = R.style.AppTheme_PaleBlue
                reCreate()
            }
            view5.doOnClick {
                RippleAnimation.create(view5).setDuration(1000).start()
                myAppBar.setBackgroundColor(color(R.color.accent_green))
                myTheme = R.style.AppTheme_Orange
                reCreate()
            }
            view6.doOnClick {
                RippleAnimation.create(view6).setDuration(1000).start()
                myAppBar.setBackgroundColor(color(R.color.accent_cyan))
                myTheme = R.style.AppTheme_Cyan
                reCreate()
            }
            view7.doOnClick {
                RippleAnimation.create(view7).setDuration(1000).start()
                myAppBar.setBackgroundColor(color(R.color.accent_indigo))
                myTheme = R.style.AppTheme_Indigo
                reCreate()
            }
            view8.doOnClick {
                RippleAnimation.create(view8).setDuration(1000).start()
                myAppBar.setBackgroundColor(color(R.color.accent_purple))
                myTheme = R.style.AppTheme_Purple
                reCreate()
            }
            view9.doOnClick {
                RippleAnimation.create(view9).setDuration(1000).start()
                myAppBar.setBackgroundColor(color(R.color.accent_brown))
                myTheme = R.style.AppTheme_Brown
                reCreate()
            }

        }
    }


    private fun reCreate() {
        activityList.forEach {
            if (it is MainActivity) {
                it.recreate()
            }
        }
    }

    override fun initViewModel() {
    }
}