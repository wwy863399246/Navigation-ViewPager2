package com.example.myapplication.ui

import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.dylanc.longan.doOnClick
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.ui.base.BaseDBFragment
import dagger.hilt.android.AndroidEntryPoint

/**
 *@创建者   wwy
 *@创建时间 2022/6/30 8:52
 *@描述
 */
@AndroidEntryPoint
class HomeFragment : BaseDBFragment<FragmentHomeBinding>() {
    private val mViewModel: HomeViewModel by viewModels()
    override fun layoutId(): Int = R.layout.fragment_home
    private var num :String =""

    override fun initView(savedInstanceState: Bundle?) {
        mDatabind.apply {
            tvHome.doOnClick {
                findNavController().navigate(R.id.action_themeFragment)
            }
            etHome.addTextChangedListener {
                num = it.toString()
            }
        }
    }

    override fun onPause() {
        super.onPause()
        mViewModel.setNum(num)
    }

    override fun initViewModel() {
        mViewModel.numState.observe(viewLifecycleOwner) {
            mDatabind.etHome.setText(it)
        }
    }
}