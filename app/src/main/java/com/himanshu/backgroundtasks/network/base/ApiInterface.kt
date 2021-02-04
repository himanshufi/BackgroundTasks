package com.himanshu.backgroundtasks.network.base

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiInterface {

    private const val baseUrl = "https://jsonplaceholder.typicode.com/"

    private fun authClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder().addInterceptor(interceptor).addInterceptor {
            val request = it.request()

            /**
             * If you have any Authorization needed for your API, then you add this here.
             */

            val finalRequest = request.newBuilder().build()
            it.proceed(request = finalRequest)
        }
            .connectTimeout(30, TimeUnit.MINUTES)
            .writeTimeout(30, TimeUnit.MINUTES)
            .readTimeout(30, TimeUnit.MINUTES)
            .build()

    }

    /**
     * This is our [Retrofit] builder.
     */
    fun getApiService(): Service {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(authClient())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build().create(Service::class.java)
    }

}