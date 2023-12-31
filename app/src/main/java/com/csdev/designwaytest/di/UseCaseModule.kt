package com.csdev.designwaytest.di

import com.csdev.designwaytest.domain.repository.DogBreedsRepository
import com.csdev.designwaytest.domain.repository.UserRepository
import com.csdev.designwaytest.domain.useCase.dogbreeds.GetDogBreedDetailsUseCase
import com.csdev.designwaytest.domain.useCase.dogbreeds.GetDogBreedsUseCase
import com.csdev.designwaytest.domain.useCase.users.LoginUserUseCase
import com.csdev.designwaytest.domain.useCase.users.RegisterUserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    @ViewModelScoped
    fun provideLoginUserUseCase(
        repository: UserRepository
    ): LoginUserUseCase = LoginUserUseCase(repository)

    @Provides
    @ViewModelScoped
    fun provideRegisterUserUseCase(
        repository: UserRepository
    ): RegisterUserUseCase = RegisterUserUseCase(repository)

    @Provides
    @ViewModelScoped
    fun provideGetDogBreedsUseCase(
        repository: DogBreedsRepository
    ): GetDogBreedsUseCase = GetDogBreedsUseCase(repository)

    @Provides
    @ViewModelScoped
    fun provideGetDogBreedDetailsUseCase(
        repository: DogBreedsRepository
    ): GetDogBreedDetailsUseCase = GetDogBreedDetailsUseCase(repository)

}