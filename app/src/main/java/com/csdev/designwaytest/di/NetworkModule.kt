package com.csdev.designwaytest.di

import com.csdev.designwaytest.data.remote.DogBreedsApi
import com.csdev.designwaytest.utils.ApiConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    // for http interceptors, a separate file can contain all auth code and retrofit client
    @Provides
    @Singleton
    fun provideRetrofitClient(): Retrofit = Retrofit.Builder()
        .baseUrl(ApiConstants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideDogBreedsApi(client: Retrofit): DogBreedsApi {
        return client.create(DogBreedsApi::class.java)
    }

}