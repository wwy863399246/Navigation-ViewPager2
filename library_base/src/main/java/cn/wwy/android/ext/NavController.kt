@file:Suppress("INVISIBLE_REFERENCE", "INVISIBLE_MEMBER")

package cn.wwy.android.ext

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController

/**
 * Navigate from the fragment with [id] using the given [directions].
 * If the id doesn't match the current destination, an error is recorded.
 */
@kotlin.internal.InlineOnly
inline fun NavController.nav(
    @IdRes id: Int?,
    directions: NavDirections,
    navOptions: NavOptions? = null
) {
    if (id == null || this.currentDestination?.id == id) {
        this.navigate(directions, navOptions)
    }
}

@kotlin.internal.InlineOnly
inline fun NavController.nav(@IdRes id: Int?, directions: NavDirections, extras: Navigator.Extras) {
    if (id == null || this.currentDestination?.id == id) {
        this.navigate(directions, extras)
    }
}

@kotlin.internal.InlineOnly
inline fun NavController.nav(
    @IdRes id: Int?,
    directions: NavDirections,
    navOptions: NavOptions? = null,
    extras: Navigator.Extras? = null
) = nav(id, directions.actionId, directions.arguments, navOptions, extras)

@kotlin.internal.InlineOnly
inline fun NavController.nav(
    @IdRes id: Int?,
    directions: NavDirections,
    args: Bundle,
    navOptions: NavOptions? = null,
    extras: Navigator.Extras? = null
) = nav(id, directions.actionId, args, navOptions, extras)

@kotlin.internal.InlineOnly
inline fun NavController.nav(
    @IdRes id: Int?,
    @IdRes destId: Int,
    args: Bundle?,
    navOptions: NavOptions?,
    extras: Navigator.Extras?
) {
    if (id == null || this.currentDestination?.id == id) {
        this.navigate(destId, args, navOptions, extras)
    }
}

@kotlin.internal.InlineOnly
inline fun NavController.alreadyOnDestination(@IdRes destId: Int?): Boolean {
    return destId?.let { currentDestination?.id == it || popBackStack(it, false) } ?: false
}

@kotlin.internal.InlineOnly
inline fun Fragment.nav(@IdRes id: Int?, directions: NavDirections,args: Bundle) {
    findNavController(this).nav(id, directions,args)
}

@kotlin.internal.InlineOnly
inline fun Fragment.nav(@IdRes id: Int?, directions: NavDirections) {
    findNavController(this).nav(id, directions)
}

@kotlin.internal.InlineOnly
inline fun Fragment.nav(@IdRes id: Int?, directions: NavDirections, extras: Navigator.Extras) {
    findNavController(this).nav(id, directions, extras)
}

@kotlin.internal.InlineOnly
inline fun Fragment.nav(@IdRes id: Int?, directions: NavDirections, options: NavOptions? = null) {
    findNavController(this).nav(id, directions, options)
}
