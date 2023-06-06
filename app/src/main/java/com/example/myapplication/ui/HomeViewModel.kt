package com.example.myapplication.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import cn.wwy.android.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 *@创建者   wwy
 *@创建时间 2023/3/24 15:24
 *@描述
 */
@HiltViewModel
class HomeViewModel @Inject constructor(private val savedStateHandle: SavedStateHandle) :
    BaseViewModel() {
    private val number = "number"
    fun setNum(num: String) = savedStateHandle.set(number, num)
    var numState: LiveData<String> = savedStateHandle.getLiveData(number)
}