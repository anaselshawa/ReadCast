package io.example.readcast.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.example.readcast.data.remote.HomeApiService
import io.example.readcast.data.remote.SearchApiService
import kotlinx.serialization.ExperimentalSerializationApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MainApi

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class SearchApi


@Module
@InstallIn(SingletonComponent::class)
@OptIn(ExperimentalSerializationApi::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @MainApi
    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {

        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://api-v2-b2sit6oh3a-uc.a.run.app/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideApiService(@MainApi retrofit: Retrofit): HomeApiService {
        return retrofit.create(HomeApiService::class.java)
    }



    @SearchApi
    @Singleton
    @Provides
    fun provideSearchRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {

        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://mock.apidog.com/m1/735111-711675-default/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    @Provides
    fun provideSearchApiService(@SearchApi retrofit: Retrofit): SearchApiService {
        return retrofit.create(SearchApiService::class.java)
    }
}
