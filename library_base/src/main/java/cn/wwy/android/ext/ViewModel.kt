package cn.wwy.android.ext

import androidx.activity.ComponentActivity
import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelLazy
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.viewmodel.CreationExtras

/**
 *@创建者   wwy
 *@创建时间 2023/5/18 14:56
 *@描述
 */
val applicationViewModelStore by lazy { ViewModelStore() }

@MainThread
inline fun <reified VM : ViewModel> ComponentActivity.applicationViewModels(
    noinline factoryProducer: () -> ViewModelProvider.Factory = { defaultViewModelProviderFactory },
    noinline extrasProducer: (() -> CreationExtras) = { defaultViewModelCreationExtras },
): Lazy<VM> =
    createApplicationViewModelLazy(extrasProducer, factoryProducer)

@MainThread
inline fun <reified VM : ViewModel> Fragment.applicationViewModels(
    noinline factoryProducer: () -> ViewModelProvider.Factory = { defaultViewModelProviderFactory },
    noinline extrasProducer: (() -> CreationExtras) = { defaultViewModelCreationExtras },
): Lazy<VM> =
    createApplicationViewModelLazy(extrasProducer, factoryProducer)

@MainThread
inline fun <reified VM : ViewModel> createApplicationViewModelLazy(
    noinline extrasProducer: () -> CreationExtras,
    noinline factoryProducer: () -> ViewModelProvider.Factory
) = ViewModelLazy(VM::class, { applicationViewModelStore }, factoryProducer, extrasProducer)

