package com.example.myapplication.ui.navhost

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentNavHostHomeBinding
import com.example.myapplication.ui.base.BaseDBFragment
import com.example.myapplication.ui.main.MainActivityViewModel

/**
 *@创建者   wwy
 *@创建时间 2022/6/30 8:52
 *@描述
 */
class HomeNavHostFragment : BaseDBFragment<FragmentNavHostHomeBinding>(),
    NavController.OnDestinationChangedListener {
    private val mViewModel: MainActivityViewModel by activityViewModels()
    private var navController: NavController? = null
    private val nestedNavHostFragmentId = R.id.nested_nav_host_fragment_home
    override fun layoutId(): Int = R.layout.fragment_nav_host_home

    override fun initView(savedInstanceState: Bundle?) {
        val nestedNavHostFragment =
            childFragmentManager.findFragmentById(nestedNavHostFragmentId) as? NavHostFragment
        navController = nestedNavHostFragment?.navController
        listenOnBackPressed()
    }

    private fun listenOnBackPressed() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }

    override fun onResume() {
        super.onResume()
        navController?.addOnDestinationChangedListener(this)
        callback.isEnabled = true
    }

    override fun onPause() {
        super.onPause()
        navController?.removeOnDestinationChangedListener(this)
        callback.isEnabled = false
    }

    private val callback = object : OnBackPressedCallback(false) {
        override fun handleOnBackPressed() {
            if (navController?.currentDestination?.id == navController?.graph?.startDestinationId) {
                isEnabled = false
                requireActivity().onBackPressed()
                isEnabled = true
            } else {
                navController?.navigateUp()
            }
        }
    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        mViewModel.setIsMainPage(navController?.currentDestination?.id == navController?.graph?.startDestinationId)
    }
}