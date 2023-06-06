package cn.wwy.android.ui.fragment

import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BaseBottomSheetDialogFragment : BottomSheetDialogFragment() {

    override fun onStart() {
        super.onStart()
        val dialog = dialog as BottomSheetDialog
        dialog.setCancelable(cancelable())
        val bottomSheet =
            dialog.delegate.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
        bottomSheet?.layoutParams?.height = dialogHeight()  //自定义高度
        val view = view
        view?.post {
            val parent = view.parent as View
            val params = parent.layoutParams as CoordinatorLayout.LayoutParams
            val behavior = params.behavior
            val bottomSheetBehavior = behavior as BottomSheetBehavior<*>?
            bottomSheetBehavior?.setState(BottomSheetBehavior.STATE_EXPANDED)
        }
        initView()
    }

    /**
     * 子类重写修改弹窗高度
     */
    protected open fun dialogHeight(): Int = ViewGroup.LayoutParams.WRAP_CONTENT

    /**
     * 子类重写，修改是否点击外部区域关闭弹窗
     */
    protected open fun cancelable(): Boolean = true
    protected open fun fullScreen(): Boolean = false
    protected abstract fun initView()

}