package cn.wwy.android.view.roundview

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout

/** 用于需要圆角矩形框背景的FrameLayout的情况,减少直接使用FrameLayout时引入的shape资源文件  */
class RoundFrameLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    FrameLayout(context, attrs, defStyleAttr) {
    val delegate: RoundViewDelegate = RoundViewDelegate(this, context, attrs)
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        if (delegate.isWidthHeightEqual && width > 0 && height > 0) {
            val max = width.coerceAtLeast(height)
            val measureSpec = MeasureSpec.makeMeasureSpec(max, MeasureSpec.EXACTLY)
            super.onMeasure(measureSpec, measureSpec)
            return
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        if (delegate.isRadiusHalfHeight) {
            delegate.cornerRadius = height / 2
        } else {
            delegate.setBgSelector()
        }
    }

}