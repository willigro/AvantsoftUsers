package com.avantsoft.datasource.network.config

import android.content.Context
import java.io.IOException
import java.net.SocketTimeoutException
import java.util.concurrent.TimeUnit
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor


object BaseRestApi {
    const val CACHE_SIZE: Long = 5 * 1024 * 1024 // 5MB

    fun getOkHttpClient(context: Context): OkHttpClient {
        try {
            val builder = OkHttpClient.Builder()

            val requestTimeout = 5L
            val myCache = Cache(context.cacheDir, CACHE_SIZE)

            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

            builder
                .cache(myCache)
                .readTimeout(requestTimeout, TimeUnit.SECONDS)
                .writeTimeout(requestTimeout, TimeUnit.SECONDS)
                .connectTimeout(requestTimeout, TimeUnit.SECONDS)

            return builder
                .addInterceptor(interceptor)
                .addInterceptor(OkHttpExceptionInterceptor())
                .build()
        } catch (e: Exception) {
            e.printStackTrace()
            throw RuntimeException(e)
        }
    }

    private class OkHttpExceptionInterceptor : Interceptor {

        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            try {
                return chain.proceed(chain.request())
            } catch (e: Throwable) {
                throw IOException()
            }
        }
    }

    @Throws(IOException::class)
    private  fun onOnIntercept(chain: Interceptor.Chain): Response {
        try {
            val response: Response = chain.proceed(chain.request())
            return response.newBuilder()
                .build()
        } catch (exception: SocketTimeoutException) {
            exception.printStackTrace()
        }
        return chain.proceed(chain.request())
    }
}

const val CONTENT_TYPE_JSON = "Content-Type:application/json"