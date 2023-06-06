package com.example.myapplication.ui.member

import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentMemberBinding
import com.example.myapplication.ui.base.BaseDBFragment
import dagger.hilt.android.AndroidEntryPoint

/**
 *@创建者   wwy
 *@创建时间 2022/6/30 8:52
 *@描述
 */
@AndroidEntryPoint
class MemberFragment : BaseDBFragment<FragmentMemberBinding>() {
    override fun layoutId(): Int = R.layout.fragment_member

    override fun initView(savedInstanceState: Bundle?) {
        mDatabind.apply {

        }
    }
}