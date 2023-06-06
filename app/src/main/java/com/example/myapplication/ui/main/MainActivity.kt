package com.example.myapplication.ui.main

import android.os.Build
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.updatePadding
import androidx.navigation.fragment.NavHostFragment
import cn.wwy.android.ext.applicationViewModels
import cn.wwy.android.ext.paddingBottomHeight
import cn.wwy.android.ui.activity.BaseDBActivity
import cn.wwy.android.windowpreferences.WindowPreferencesManager
import com.dylanc.longan.finishAllActivities
import com.dylanc.longan.logError
import com.dylanc.longan.toast
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.ext.myTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseDBActivity<ActivityMainBinding>() {
    private var isMainFragment = false
    private var isMainState = false
    private var appReady = false
    private val mViewModel: MainActivityViewModel by viewModels()
    private val applicationViewModel: ApplicationViewModel by applicationViewModels()
    val navHost by lazy {
        supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().apply { setKeepOnScreenCondition { !appReady } }
        setTheme(myTheme)
        super.onCreate(savedInstanceState)
        WindowPreferencesManager(this).applyEdgeToEdgePreference(window)
    }

    override fun layoutId(): Int = R.layout.activity_main


    override fun initView(savedInstanceState: Bundle?) {
        appReady = true
        navHost.navController.addOnDestinationChangedListener { _, destination, _ ->
            isMainFragment = when (destination.id) {
                R.id.start_dest -> {
                    true
                }

                else -> {
                    false
                }
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val int = WindowPreferencesManager.getRealNavHeight(this)
            logError(int)
        }
        mDatabind.navHostFragment.setOnApplyWindowInsetsListener { v, insets ->
            v.updatePadding(
                bottom = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    WindowPreferencesManager.getRealNavHeight(this)
                } else paddingBottomHeight
            )
            insets
        }
    }


    override fun initViewModel() {
        applicationViewModel.recreateEvent.observe(this) {
            toast(it.toString())
            recreate()
        }
    }

    private var lastBackTime: Long = 0
    override fun onBackPressed() {
        mViewModel.isMainState.observe(this) {
            isMainState = it
        }
        if (isMainFragment && isMainState) {
            val currentTime = System.currentTimeMillis()
            if (currentTime - lastBackTime > 2000) {
                toast("再次点击退出应用")
                lastBackTime = currentTime
            } else {
                finishAllActivities()
            }
        } else {
            super.onBackPressed()
        }
    }
}