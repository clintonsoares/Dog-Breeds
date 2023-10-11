package com.csdev.designwaytest.di

import android.app.Application
import androidx.room.Room
import com.csdev.designwaytest.data.local.database.UserDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideUserDatabase(app: Application): UserDatabase {
        return Room
            .databaseBuilder(
                app,
                UserDatabase::class.java,
                "user_db"
            )
            .build()
    }

}