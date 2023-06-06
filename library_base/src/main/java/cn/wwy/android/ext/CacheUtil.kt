package cn.wwy.android.ext

import android.content.Context
import android.os.Environment.MEDIA_MOUNTED
import android.os.Environment.getExternalStorageState
import java.io.File
import java.math.BigDecimal
import java.math.BigDecimal.ROUND_HALF_UP
import java.math.RoundingMode

/**
 * 获取缓存大小，包含内部内部缓存和外部缓存
 */
fun Context.getCacheSize(): String {
    var cacheSize = getFolderSize(this.cacheDir)
    if (getExternalStorageState() == MEDIA_MOUNTED) {
        cacheSize += getFolderSize(this.externalCacheDir)
    }
    return getFormatSize(cacheSize)
}

fun getFormatSize(cacheSize: Long): String {
    val kb = cacheSize / 1024.0
    if (kb < 1) {
        return "${BigDecimal(cacheSize).setScale(2, RoundingMode.HALF_UP).toPlainString()}B"
    }
    val mb = kb / 1024.0
    if (mb < 1) {
        return "${BigDecimal(kb).setScale(2, RoundingMode.HALF_UP).toPlainString()}KB"
    }
    val gb = mb / 1024.0
    if (gb < 1) {
        return "${BigDecimal(mb).setScale(2, RoundingMode.HALF_UP).toPlainString()}MB"
    }
    val tb = gb / 1024.0
    if (tb < 1) {
        return "${BigDecimal(gb).setScale(2, RoundingMode.HALF_UP).toPlainString()}GB"
    }
    return "${BigDecimal(tb).setScale(2,RoundingMode.HALF_UP).toPlainString()}TB"
}

private fun getFolderSize(file: File?): Long {
    var size = 0L
    file?.listFiles()?.forEach {
        size += if (it.isDirectory) getFolderSize(it) else it.length()
    }
    return size
}

/**
 * 清除缓存
 */
fun Context.clearCache() {
    deleteDir(this.cacheDir)
    if (getExternalStorageState() == MEDIA_MOUNTED) {
        deleteDir(this.externalCacheDir)
    }
}

private fun deleteDir(file: File?): Boolean {
    if (file == null) return false
    if (file.isDirectory) {
        file.list()?.forEach {
            val success = deleteDir(File(file, it))
            if (!success) return false
        }
    }
    return file.delete()
}
