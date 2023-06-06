package cn.wwy.android.ui.fragment

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.viewbinding.ViewBinding
import cn.wwy.android.ext.px

abstract class BaseDialogFragment<VB : ViewBinding> : DialogFragment() {
    private var _binding: VB? = null
    protected val binding get() = _binding!!
    private var isStart: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))//dialog 背景透明
        _binding = initBinding(inflater, container)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        if (!isStart) {
            isStart = true
            val dialog = dialog ?: return
            val window = dialog.window
            dialog.setCancelable(cancelable())
            if (window != null) {
                window.setGravity(gravity())
                window.setLayout(dialogWidth(), dialogHeight())
            }
            if (dialogBackground() < 1.0f) {
                window?.setDimAmount(dialogBackground())
            }
            initialize()
        }
    }

    protected open fun initialize() {
        initView()
    }

    protected open fun dialogBackground(): Float = 1.0f

    /**
     * 子类重写，修改弹窗位置
     */
    protected open fun gravity(): Int = Gravity.CENTER

    /**
     * 子类重写修改弹窗宽度
     */
    protected open fun dialogWidth(): Int = px(328f)


    /**
     * 子类重写修改弹窗高度
     */
    protected open fun dialogHeight(): Int = ViewGroup.LayoutParams.WRAP_CONTENT

    /**
     * 子类重写，修改是否点击外部区域关闭弹窗
     */
    protected open fun cancelable(): Boolean = true


    protected abstract fun initView()

    abstract fun initBinding(inflater: LayoutInflater, viewGroup: ViewGroup?): VB

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        isStart = false
    }
}

