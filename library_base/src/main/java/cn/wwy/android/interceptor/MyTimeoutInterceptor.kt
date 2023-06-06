package cn.wwy.android.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import java.util.concurrent.TimeUnit

/**
 *@创建者   wwy
 *@创建时间 2021/8/12 15:34
 *@描述
 */
const val CONNECT_TIMEOUT = "CONNECT_TIMEOUT"
const val READ_TIMEOUT = "READ_TIMEOUT"
const val WRITE_TIMEOUT = "WRITE_TIMEOUT"

class MyTimeoutInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var connectTimeout = chain.connectTimeoutMillis()
        var readTimeout = chain.readTimeoutMillis()
        var writeTimeout = chain.writeTimeoutMillis()

        val request = chain.request().apply {
            header(CONNECT_TIMEOUT)?.let {
                connectTimeout = Integer.parseInt(it)
            }
            header(READ_TIMEOUT)?.let {
                readTimeout = Integer.parseInt(it)
            }
            header(WRITE_TIMEOUT)?.let {
                writeTimeout = Integer.parseInt(it)
            }
        }
        return chain
            .withConnectTimeout(connectTimeout, TimeUnit.MILLISECONDS)
            .withReadTimeout(readTimeout, TimeUnit.MILLISECONDS)
            .withWriteTimeout(writeTimeout, TimeUnit.MILLISECONDS)
            .proceed(request)
    }
}