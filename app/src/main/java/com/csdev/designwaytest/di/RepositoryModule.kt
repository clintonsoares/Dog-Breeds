package com.csdev.designwaytest.di

import com.csdev.designwaytest.data.local.database.UserDatabase
import com.csdev.designwaytest.data.remote.DogBreedsApi
import com.csdev.designwaytest.data.repository.DogBreedsRepositoryImpl
import com.csdev.designwaytest.data.repository.UserRepositoryImpl
import com.csdev.designwaytest.domain.repository.DogBreedsRepository
import com.csdev.designwaytest.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideUserRepository(userDb: UserDatabase): UserRepository = UserRepositoryImpl(userDb.dao)

    @Provides
    @ViewModelScoped
    fun provideDogBreedsRepository(api: DogBreedsApi): DogBreedsRepository = DogBreedsRepositoryImpl(api)

}