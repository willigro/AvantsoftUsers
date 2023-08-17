package com.avantsoft.datasource.network.di

import android.app.Application
import com.avantsoft.datasource.network.api.UserApi
import com.avantsoft.datasource.network.config.BaseRestApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        return gsonBuilder.serializeNulls().create()
    }

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, application: Application): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl("https://run.mocky.io/v3/")
            .client(provideOkhttpClient(application))
            .build()
    }

    @Provides
    @Singleton
    fun provideOkhttpClient(application: Application): OkHttpClient {
        return BaseRestApi.getOkHttpClient(application)
    }

    @Provides
    @Singleton
    fun provideUserApi(
        retrofit: Retrofit
    ): UserApi {
        return retrofit.create(UserApi::class.java)
    }
}