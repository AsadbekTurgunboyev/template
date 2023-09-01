package com.example.taxopark.di

import com.example.taxopark.data.source.ApiService
import com.example.taxopark.utils.constant.BASE_URL
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


private const val TIME_OUT = 30L

val NetworkModule = module {

    single { createService(get()) }

    single { createRetrofit(get(), BASE_URL) }

    single { createOkHttpClient() }
//    single { UserPreferenceManager(androidContext()) }


}

fun createOkHttpClient(): OkHttpClient {


    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.NONE

    return OkHttpClient.Builder()
        .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
        .readTimeout(TIME_OUT, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor)

        /** this is add later, if needed. ( it is header settings)
         **/
//        .addInterceptor { chain ->
//            val originalRequest = chain.request()
//            val token = pref.getToken()
//            val language = pref.getLanguage().code
//
//            val request = originalRequest.newBuilder()
//                .apply {
//                    header("Accept-Language", language)
//                    token?.let { header("Authorization", "Bearer $it") }
//                }
//                .build()
//
//            chain.proceed(request)
//        }
        .build()
}

fun createRetrofit(okHttpClient: OkHttpClient, url: String): Retrofit {
    val gson: Gson = GsonBuilder().create()

    return Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson)).build()
}


fun createService(retrofit: Retrofit): ApiService {
    return retrofit.create(ApiService::class.java)

}

