package com.example.myapplication.ui.main

import cn.wwy.android.livedata.UnPeekLiveData
import cn.wwy.android.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 *@创建者   wwy
 *@创建时间 2023/3/24 15:24
 *@描述
 */
@HiltViewModel
class ApplicationViewModel @Inject constructor() : BaseViewModel() {
    var recreateEvent = UnPeekLiveData<Int>()
}