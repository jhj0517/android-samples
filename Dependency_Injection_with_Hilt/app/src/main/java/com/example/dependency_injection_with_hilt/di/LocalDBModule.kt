package com.example.dependency_injection_with_hilt.di

import android.content.Context
import com.example.dependency_injection_with_hilt.localdb.AppDatabase
import com.example.dependency_injection_with_hilt.localdb.DataDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDBModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Provides
    @Singleton
    fun provideDataDao(appDatabase: AppDatabase): DataDao {
        return appDatabase.dataDao()
    }
}