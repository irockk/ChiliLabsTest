package com.example.chililabstest.di

import com.example.chililabstest.core.Constants.BASE_GIPHY_URL
import com.example.chililabstest.features.giphy.data.GiphyPagingSource
import com.example.chililabstest.features.giphy.data.GiphyService
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_GIPHY_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().build())
            .build()
    }

    fun provideNetworkApi(retrofit: Retrofit) = retrofit.create(GiphyService::class.java)

    factory { GiphyPagingSource(giphyService = provideNetworkApi(provideRetrofit())) }
}