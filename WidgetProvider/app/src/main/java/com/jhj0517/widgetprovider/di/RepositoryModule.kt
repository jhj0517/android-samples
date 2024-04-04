package com.jhj0517.widgetprovider.di

import com.jhj0517.widgetprovider.repositories.FruitsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

const val fruitUpdateInterval = 1000L // 2 secs

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {
    @Singleton
    @Provides
    fun provideFruitsRepository(): FruitsRepository {
        return FruitsRepository(updateInterval = fruitUpdateInterval)
    }
}