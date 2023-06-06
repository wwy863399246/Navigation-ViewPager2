package com.example.myapplication.ui.main

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.os.Bundle
import android.util.TypedValue
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.widget.ViewPager2
import cn.wwy.android.R.*
import cn.wwy.android.ext.ViewPagerAdapter
import cn.wwy.android.ext.color
import cn.wwy.android.ext.resourceId
import com.angcyo.tablayout.delegate2.ViewPager2Delegate
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentMainViewpageBinding
import com.example.myapplication.ui.base.BaseDBFragment
import com.example.myapplication.ui.navhost.CourseNavHostFragment
import com.example.myapplication.ui.navhost.HomeNavHostFragment
import com.example.myapplication.ui.navhost.MineNavHostFragment
import dagger.hilt.android.AndroidEntryPoint


/**
 *@创建者   wwy
 *@创建时间 2022/6/29 16:06
 *@描述
 */
@AndroidEntryPoint
class MainViewPageFragment : BaseDBFragment<FragmentMainViewpageBinding>() {
    private val mViewModel: MainActivityViewModel by activityViewModels()
    override fun layoutId(): Int = R.layout.fragment_main_viewpage
    override fun initView(savedInstanceState: Bundle?) {
        mDatabind.apply {
            mViewModel.isMainState.observe(viewLifecycleOwner) {
                mainTabLayout.isVisible = it
            }
            ViewPager2Delegate(mainViewPage, dslTabLayout, false)
            mainViewPage.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    setTabSelect(position)
                }
            })
            mainViewPage.isUserInputEnabled = false
            mainViewPage.offscreenPageLimit = 3
            mainViewPage.adapter =
                ViewPagerAdapter(childFragmentManager, viewLifecycleOwner.lifecycle).apply {
                    add {
                        HomeNavHostFragment()
                    }
                    add {
                        CourseNavHostFragment()
                    }
                    add {
                        MineNavHostFragment()
                    }
                }
        }
    }

    @SuppressLint("UseCompatTextViewDrawableApis")
    private fun setTabSelect(position: Int) {
        val colorPrimary = TypedValue().resourceId(R.attr.colorPrimary, requireActivity().theme)
        mDatabind.mainTvHome.compoundDrawableTintList =
            ColorStateList.valueOf(color(if (position == 0) colorPrimary else color.hint_text))
        mDatabind.mainTvCourse.compoundDrawableTintList =
            ColorStateList.valueOf(color(if (position == 1) colorPrimary else color.hint_text))
        mDatabind.mainTvMine.compoundDrawableTintList =
            ColorStateList.valueOf(color(if (position == 2) colorPrimary else color.hint_text))
    }

}