package cn.wwy.android.ext

import android.content.Context
import androidx.fragment.app.Fragment
import com.hjq.permissions.OnPermissionCallback
import com.hjq.permissions.XXPermissions


/**
 *@创建者   wwy
 *@创建时间 2021/9/14 15:52
 *@描述
 */

inline fun Context.getSinglePermission(
    permission: String,
    crossinline action: () -> Unit,
    crossinline permissionsAction: (permissions: MutableList<String>) -> Unit = {},
    crossinline nullAction: () -> Unit = {}
) {
    XXPermissions.with(this).permission(permission).request(object : OnPermissionCallback {
        override fun onGranted(permissions: MutableList<String>, all: Boolean) {
            if (all) {
                action()
            } else {
                nullAction()
            }
        }

        override fun onDenied(permissions: MutableList<String>, never: Boolean) {
            if (never) {
                permissionsAction(permissions)
            } else {
                nullAction()
            }
        }
    })
}

inline fun Context.getSomePermission(
    permissions: Array<String>,
    crossinline action: () -> Unit,
    crossinline permissionsAction: (permissions: MutableList<String>) -> Unit = {},
    crossinline nullAction: () -> Unit = {}
) {
    XXPermissions.with(this).permission(permissions).request(object : OnPermissionCallback {
        override fun onGranted(permissions: MutableList<String>, all: Boolean) {
            if (all) {
                action()
            } else {
                nullAction()
            }
        }

        override fun onDenied(permissions: MutableList<String>, never: Boolean) {
            if (never) {
                permissionsAction(permissions)
            } else {
                nullAction()
            }
        }
    })
}

inline fun Fragment.getSinglePermission(
    permission: String,
    crossinline action: () -> Unit,
    crossinline permissionsAction: (permissions: MutableList<String>) -> Unit = {},
    crossinline nullAction: () -> Unit = {}
) {
    XXPermissions.with(requireActivity()).permission(permission).request(object : OnPermissionCallback {
        override fun onGranted(permissions: MutableList<String>, all: Boolean) {
            if (all) {
                action()
            } else {
                nullAction()
            }
        }

        override fun onDenied(permissions: MutableList<String>, never: Boolean) {
            if (never) {
                permissionsAction(permissions)
            } else {
                nullAction()
            }
        }
    })
}

inline fun Fragment.getSomePermission(
    permissions: Array<String>,
    crossinline action: () -> Unit,
    crossinline permissionsAction: (permissions: MutableList<String>) -> Unit = {},
    crossinline nullAction: () -> Unit = {}
) {
    XXPermissions.with(requireActivity()).permission(permissions)
        .request(object : OnPermissionCallback {
            override fun onGranted(permissions: MutableList<String>, all: Boolean) {
                if (all) {
                    action()
                } else {
                    nullAction()
                }
            }

            override fun onDenied(permissions: MutableList<String>, never: Boolean) {
                if (never) {
                    permissionsAction(permissions)
                } else {
                    nullAction()
                }
            }
        })
}