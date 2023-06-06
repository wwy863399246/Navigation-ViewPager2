package com.example.myapplication.ui

import android.os.Bundle
import com.dylanc.longan.doOnClick
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentSettingBinding
import com.example.myapplication.ext.myTheme
import com.example.myapplication.ui.base.BaseDBFragment
import dagger.hilt.android.AndroidEntryPoint

/**
 *@创建者   wwy
 *@创建时间 2022/6/30 8:52
 *@描述
 */
@AndroidEntryPoint
class SettingFragment : BaseDBFragment<FragmentSettingBinding>() {
    override fun layoutId(): Int = R.layout.fragment_setting

    override fun initView(savedInstanceState: Bundle?) {
        mDatabind.apply {
            tvSetting.doOnClick {
                myTheme = R.style.AppTheme_PaleBlue
                requireActivity().recreate()
            }
        }
    }
}