package com.example.moviemvvm.presentation.di

import com.example.moviemvvm.data.api.TMBDService
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class NetModule(private val base_url: String) {
    var gson = GsonBuilder()
        .setLenient()
        .create()
    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).client(okHttpClient)
            .baseUrl(base_url).build()
    }
    @Singleton
    @Provides
    fun provideHttpOk(httpInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(httpInterceptor).build()
    }
    @Singleton
    @Provides
    fun provideLogInterpceptor():HttpLoggingInterceptor{
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }
    @Singleton
    @Provides
    fun provideTMDBService(retrofit: Retrofit):TMBDService{
        return retrofit.create(TMBDService::class.java)
    }
}