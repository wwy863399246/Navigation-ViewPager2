package com.example.myapplication.ui

import android.os.Bundle
import androidx.navigation.fragment.findNavController
import cn.wwy.android.ext.nav
import com.dylanc.longan.doOnClick
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentCourseBinding
import com.example.myapplication.ui.base.BaseDBFragment
import com.example.myapplication.ui.main.MainViewPageFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

/**
 *@创建者   wwy
 *@创建时间 2022/6/30 8:52
 *@描述
 */
@AndroidEntryPoint
class CourseFragment : BaseDBFragment<FragmentCourseBinding>() {
    override fun layoutId(): Int = R.layout.fragment_course

    override fun initView(savedInstanceState: Bundle?) {
        mDatabind.apply {
            tvCourse.doOnClick {
                //主模块
                nav(R.id.courseFragment, MainViewPageFragmentDirections.actionMemberFragment())
            }
            tvMember.doOnClick {
                //当前模块
                findNavController().navigate(R.id.action_memberFragment)
            }
        }
    }
}