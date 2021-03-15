package com.example.newsmvvmapp.presentation.di

import com.example.newsmvvmapp.BuildConfig
import com.example.newsmvvmapp.data.model.APIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
class NetModule {
    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient):Retrofit {
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .baseUrl(BuildConfig.API_URL).build()
    }

    @Singleton
    @Provides
    fun provideNewsApiService(retrofit: Retrofit):APIService{
        return retrofit.create(APIService::class.java)
    }

    @Singleton
    @Provides
    fun provideHttpOk(httpInterceptor: HttpLoggingInterceptor):OkHttpClient{
        return OkHttpClient.Builder().addInterceptor(httpInterceptor).build()
    }
    @Singleton
    @Provides
    fun provideLogInterpceptor():HttpLoggingInterceptor{
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }
}