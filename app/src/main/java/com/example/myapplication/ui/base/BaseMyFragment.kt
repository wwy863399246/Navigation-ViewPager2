package com.example.myapplication.ui.base

import android.os.Bundle
import androidx.annotation.StringRes
import cn.wwy.android.ext.dismissLoadingExt
import cn.wwy.android.ext.showLoadingExt
import cn.wwy.android.ui.fragment.BaseFragment


abstract class BaseMyFragment : BaseFragment() {

    abstract override fun layoutId(): Int

    abstract override fun initView(savedInstanceState: Bundle?)

    override fun lazyLoadData() {}

    override fun initViewModel() {}

    override fun lazyLoadTime(): Long = 300

    fun showLoading(
        @StringRes message: Int = cn.wwy.android.R.string.please_later,
        cancelable: Boolean = false
    ) {
        mActivity.showLoadingExt(viewLifecycleOwner.lifecycle, message, cancelable)
    }

    fun dismissLoading() {
        mActivity.dismissLoadingExt()
    }

}