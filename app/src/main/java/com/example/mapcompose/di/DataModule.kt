package com.example.mapcompose.di

import com.example.mapcompose.data.repository.StationsRepositoryImpl
import com.example.mapcompose.data.service.StationsApi
import com.example.mapcompose.domain.repository.StationsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by Berk Ç. on 20.04.2022.
 */

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    internal fun provideStationsApi(retrofit: Retrofit): StationsApi = retrofit.create(StationsApi::class.java)

    @Provides
    @Singleton
    internal fun provideStationsRepository(stationsApi: StationsApi): StationsRepository = StationsRepositoryImpl(stationsApi)
}