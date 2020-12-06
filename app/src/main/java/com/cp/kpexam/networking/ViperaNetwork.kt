package com.cp.kpexam.networking

import com.cp.kpexam.networking.api.ApiService
import com.squareup.moshi.Moshi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object ViperaNetwork {

//    val moshi = Moshi.Builder()
//        .add(Wrapped.ADAPTER_FACTORY)
//        .add(HeaderJsonAdapter())
//        .add(BaseViperaRequestJsonAdapter())
//        .build()

    fun createNetworkClient(endPoint: String) = retrofitClient(httpClient(), endPoint)

    private fun httpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
        val clientBuilder = OkHttpClient.Builder()
        clientBuilder.addInterceptor(object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val original = chain.request()
                // Add default headers.
                val request = original.newBuilder()
                    .header("Accept", "application/json")
                    .build()
                return chain.proceed(request)
            }
        })
        clientBuilder.connectTimeout(60, TimeUnit.SECONDS)
        clientBuilder.readTimeout(60, TimeUnit.SECONDS)
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
        clientBuilder.addInterceptor(httpLoggingInterceptor)
        return clientBuilder.build()
    }

    private fun retrofitClient(httpClient: OkHttpClient, endPoint: String): ApiService =
        Retrofit.Builder()
            .baseUrl(endPoint)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiService::class.java)
}