package cn.wwy.android.ui

data class StateModel<T>(
    val successData: T? = null,
    val successListData: List<T>? = null,
    val showLoading: Boolean = false,
    val showError: String? = null,
    val needState: Boolean = false,
    val showEnd: Boolean = false
)