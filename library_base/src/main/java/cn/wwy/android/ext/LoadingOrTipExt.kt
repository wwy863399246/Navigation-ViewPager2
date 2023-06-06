@file:Suppress("INVISIBLE_REFERENCE", "INVISIBLE_MEMBER")

package cn.wwy.android.ext

import androidx.annotation.StringRes
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import cn.wwy.android.R
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.core.BasePopupView
import com.lxj.xpopup.impl.LoadingPopupView

/**
 *@创建者   wwy
 *@创建时间 2021/9/29 11:15
 *@描述
 */
var loadingView: BasePopupView? = null

@kotlin.internal.InlineOnly
inline fun FragmentActivity.showLoadingExt(
    lifecycle: Lifecycle,
    @StringRes message: Int = R.string.please_later,
    cancelable: Boolean = false,
    borderRadius: Float = 4f
) {
    if (!this.isFinishing) {
        if (loadingView != null) {
            loadingView?.dismiss()
            loadingView = null
        }
        loadingView =
            XPopup.Builder(this)
                .isDestroyOnDismiss(true)
                .borderRadius(borderRadius)
                .hasNavigationBar(cancelable)
                .dismissOnBackPressed(cancelable)
                .dismissOnTouchOutside(cancelable)
                .customHostLifecycle(lifecycle)
                .asLoading(
                    text(message),
                    R.layout.popup_loading_view,
                    LoadingPopupView.Style.ProgressBar
                )
        loadingView?.show()
    }
}

/**
 * 关闭等待框
 */
@kotlin.internal.InlineOnly
inline fun FragmentActivity.dismissLoadingExt() {
    if (!this.isFinishing) {
        loadingView?.dismiss()
        loadingView = null
    }
}

//@kotlin.internal.InlineOnly
//inline fun FragmentActivity.showConfirmDialogExt(
//    title: String = "",
//    content: String = "",
//    crossinline action: () -> Unit = {},
//    crossinline nullAction: () -> Unit = {},
//    cancelable: Boolean = false,
//    cancelBtnText: String = text(R.string.cancel),
//    confirmBtnText: String = text(R.string.confirm),
//    isHideCancel: Boolean = false,
//    @DrawableRes drawableRes: Int = 0,//顶部图片
//    @ColorRes cancelResColor: Int = R.color.text_color_333333,//取消按钮文字颜色
//    @ColorRes confirmResColor: Int = R.color.app_primary_color,//确定按钮文字颜色
//    @ColorRes contentResColor: Int = R.color.text_color_999999,//文本文字颜色
//) {
//    if (!this.isFinishing) {
//        val customPopup: MyConfirmPopupView = MyConfirmPopupView(this).apply {
//            setDrawableRes(drawableRes)
//            setTitle(title)
//            setContent(content)
//            setCancelText(cancelBtnText)
//            setConfirmText(confirmBtnText)
//            setIsHideCancel(isHideCancel)
//            setCancelResColor(cancelResColor)
//            setConfirmResColor(confirmResColor)
//            setContentResColor(contentResColor)
//            setListener({
//                action()
//            }, {
//                nullAction()
//            })
//        }
//        XPopup.Builder(this)
//            .isViewMode(true)
//            .isDestroyOnDismiss(true)
//            .dismissOnBackPressed(cancelable)
//            .dismissOnTouchOutside(cancelable)
//            .asCustom(customPopup)
//            .show()
//    }
//}

