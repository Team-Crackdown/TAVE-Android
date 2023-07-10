package com.example.tave.di.module

import com.example.data.api.TaveAPIService
import com.example.data.repositoryImpl.TaveAPIRepositoryImpl
import com.example.domain.repository.TaveAPIRepository
import com.example.tave.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TaveAPIModule {

    @Provides
    @Singleton
    fun provideRetrofitClient(httpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.TAVE_API_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideAPIService(retrofit: Retrofit): TaveAPIService =
        retrofit.create(TaveAPIService::class.java)

    @Provides
    @Singleton
    fun provideTaveApiRepoImpl(taveAPIService: TaveAPIService): TaveAPIRepository =
        TaveAPIRepositoryImpl(taveAPIService)
}