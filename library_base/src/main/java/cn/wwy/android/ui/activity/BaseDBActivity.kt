package cn.wwy.android.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding


/**
 *@创建者wwy
 *@创建时间 2019/10/9 11:08
 *@描述
 */
abstract class BaseDBActivity<DB : ViewDataBinding> : AppCompatActivity() {

    lateinit var mDatabind: DB
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mDatabind = DataBindingUtil.setContentView(this, layoutId())
        mDatabind.lifecycleOwner = this
        initView(savedInstanceState)
        initViewModel()
    }

    abstract fun layoutId(): Int
    /**
     * 布局加载
     */
    abstract fun initView(savedInstanceState: Bundle?)
    abstract fun initViewModel()
}