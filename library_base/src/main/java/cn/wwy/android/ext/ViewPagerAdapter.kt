package cn.wwy.android.ext

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2

/**
 *@创建者   wwy
 *@创建时间 2022/10/25 14:48
 *@描述
 */
typealias HandleFragment = () -> Fragment

open class ViewPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    isLazyLoading: Boolean = true
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    val mFragmentList = mutableListOf<HandleFragment>()

    var mIsLazyLoading = isLazyLoading

    override fun getItemCount(): Int {
        return mFragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return mFragmentList[position].invoke()
    }

    fun add(fragment: HandleFragment): ViewPagerAdapter {
        mFragmentList.add(fragment)
        return this
    }

    fun add(fragmentList: List<HandleFragment>): ViewPagerAdapter {
        mFragmentList.addAll(fragmentList)
        return this
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        if (mIsLazyLoading) recyclerView.setItemViewCacheSize(mFragmentList.size)
    }
}

@Suppress("UNCHECKED_CAST")
fun <T : Fragment> ViewPager2.findFragment(fragmentManager: FragmentManager, position: Int) =
    fragmentManager.findFragmentByTag("f$position") as T?

