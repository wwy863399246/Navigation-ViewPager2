@file:Suppress("INVISIBLE_REFERENCE", "INVISIBLE_MEMBER")

package cn.wwy.android.ext

import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment


/**
 *@创建者wwy
 *@创建时间 2019/11/14 10:03
 *@描述
 */

//获取颜色
@kotlin.internal.InlineOnly
@ColorInt
inline fun Context.color(@ColorRes id: Int) = ContextCompat.getColor(this, id)

@kotlin.internal.InlineOnly
@ColorInt
inline fun Fragment.color(@ColorRes id: Int) = requireContext().color(id)

@kotlin.internal.InlineOnly
@ColorInt
inline fun View.color(@ColorRes id: Int) = context.color(id)

@kotlin.internal.InlineOnly
@ColorInt
@Deprecated("Use `String.parseColor()` instead.", ReplaceWith("colorString.parseColor()"))
inline fun parseColor(colorString: String): Int = Color.parseColor(colorString)

//设置drawable
@kotlin.internal.InlineOnly
inline fun Context.drawable(@DrawableRes id: Int) = ResourcesCompat.getDrawable(resources, id, null)

@kotlin.internal.InlineOnly
inline fun Fragment.drawable(@DrawableRes id: Int) = requireContext().drawable(id)

@kotlin.internal.InlineOnly
inline fun View.drawable(@DrawableRes id: Int) = context.drawable(id)

//设置文字
@kotlin.internal.InlineOnly
inline fun Context.text(@StringRes id: Int) = this.resources.getString(id)

@kotlin.internal.InlineOnly
inline fun Fragment.text(@StringRes id: Int) = requireContext().getString(id)

@kotlin.internal.InlineOnly
inline fun View.text(@StringRes id: Int) = context.text(id)

//设置html文字
@kotlin.internal.InlineOnly
inline fun String?.htmlToSpanned() =
    if (this.isNullOrEmpty()) "" else HtmlCompat.fromHtml(this, HtmlCompat.FROM_HTML_MODE_LEGACY)

//设置textview图片位置
enum class DrawableState {
    Start, Top, End, Bottom
}

@kotlin.internal.InlineOnly
inline fun TextView.compoundDrawables(drawableRes: Int, drawableState: DrawableState) {
    val drawable = this.drawable(drawableRes)?.apply {
        setBounds(0, 0, minimumWidth, minimumHeight)
    }
    when (drawableState) {
        DrawableState.Start -> {
            this.setCompoundDrawables(drawable, null, null, null)
        }
        DrawableState.Top -> {
            this.setCompoundDrawables(null, drawable, null, null)
        }
        DrawableState.End -> {
            this.setCompoundDrawables(null, null, drawable, null)
        }
        DrawableState.Bottom -> {
            this.setCompoundDrawables(null, null, null, drawable)
        }
    }
}

//dp转px
@kotlin.internal.InlineOnly
inline fun Fragment.px(dpValue: Float): Int =
    (dpValue * this.resources.displayMetrics.density + 0.5f).toInt()

@kotlin.internal.InlineOnly
inline fun Context.px(dpValue: Float): Int =
    (dpValue * this.resources.displayMetrics.density + 0.5f).toInt()

//获取主题属性id
@kotlin.internal.InlineOnly
inline fun TypedValue.resourceId(resId: Int, theme: Resources.Theme): Int {
    theme.resolveAttribute(resId, this, true)
    return this.resourceId
}

//加载子布局
@kotlin.internal.InlineOnly
inline fun ViewGroup.inflate(@LayoutRes layoutId: Int, attachToRoot: Boolean = true): View {
    if (layoutId == -1) {
        return this
    }
    return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}

/**
 * 判断是否为空 并传入相关操作
 */
@kotlin.internal.InlineOnly
inline fun <reified T> T?.notNull(notNullAction: (T) -> Unit, nullAction: () -> Unit = {}) {
    if (this != null) {
        notNullAction.invoke(this)
    } else {
        nullAction.invoke()
    }
}

/**
 * 判断是否为空 并传入相关操作
 */
@kotlin.internal.InlineOnly
inline fun <reified T : List<*>> T?.listNotNull(
    notNullAction: (T) -> Unit,
    nullAction: () -> Unit = {}
) {
    if (this != null && (this as List<*>).isNotEmpty()) {
        notNullAction.invoke(this)
    } else {
        nullAction.invoke()
    }
}



