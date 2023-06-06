package com.example.myapplication.ui

import android.os.Bundle
import cn.wwy.android.ext.nav
import com.dylanc.longan.doOnClick
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentMineBinding
import com.example.myapplication.ui.base.BaseDBFragment
import dagger.hilt.android.AndroidEntryPoint

/**
 *@创建者   wwy
 *@创建时间 2022/6/30 8:52
 *@描述
 */
@AndroidEntryPoint
class MineFragment : BaseDBFragment<FragmentMineBinding>() {
    override fun layoutId(): Int = R.layout.fragment_mine

    override fun initView(savedInstanceState: Bundle?) {
        mDatabind.apply {
            tvMine.doOnClick {
                nav(R.id.mineFragment,MineFragmentDirections.actionSettingFragment())
            }
        }
    }
}