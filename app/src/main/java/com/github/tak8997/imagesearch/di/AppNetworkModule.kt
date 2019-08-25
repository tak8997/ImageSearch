package com.github.tak8997.imagesearch.di

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.github.tak8997.imagesearch.data.ApiService
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module(includes = [AppNetworkModule.ProvideModule::class])
interface AppNetworkModule {

    companion object {
        const val BASE_URL = ""
    }

    @Module
    class ProvideModule {

        @Singleton
        @Provides
        fun provideOkHttpClient(): OkHttpClient {
            val loggingIntercepter = HttpLoggingInterceptor()
            loggingIntercepter.level = HttpLoggingInterceptor.Level.BODY

            val headerInterceptor = object : Interceptor {
                override fun intercept(chain: Interceptor.Chain): Response {
                    val original = chain.request()

                    val request = original.newBuilder()
                        .header("Authorization", "KakaoAK 0729f061b07720f3f10eb1dcb2c05902")
                        .method(original.method(), original.body())
                        .build()

                    return chain.proceed(request)
                }
            }

            return OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .addInterceptor(headerInterceptor)
                .addInterceptor(loggingIntercepter)
                .addNetworkInterceptor(StethoInterceptor())
                .build()
        }

        @Singleton
        @Provides
        fun retrofit(okHttpClient: OkHttpClient): Retrofit
                = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        @Singleton
        @Provides
        fun provideAppService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)
    }
}