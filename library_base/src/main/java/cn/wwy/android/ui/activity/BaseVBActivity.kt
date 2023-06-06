package cn.wwy.android.ui.activity

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import cn.wwy.android.windowpreferences.WindowPreferencesManager

/**
 *@创建者   wwy
 *@创建时间 2021/9/15 10:11
 *@描述
 */
abstract class BaseVBActivity<VB : ViewBinding> : AppCompatActivity() {
    lateinit var binding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowPreferencesManager(this).applyEdgeToEdgePreference(window)
        binding = initBinding(layoutInflater)
        setContentView(binding.root)
        initView(savedInstanceState)
        initViewModel()
    }

    /**
     * 布局加载
     */
    abstract fun initView(savedInstanceState: Bundle?)
    abstract fun initViewModel()
    abstract fun initBinding(layoutInflater: LayoutInflater): VB
}