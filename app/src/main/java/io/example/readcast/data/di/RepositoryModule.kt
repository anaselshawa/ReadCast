package io.example.readcast.data.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.example.readcast.data.paging.SectionsPagingSource
import io.example.readcast.data.remote.HomeApiService
import io.example.readcast.domain.repository.SearchRepository
import io.example.readcast.data.repository.SearchRepositoryImpl
import io.example.readcast.domain.repository.SectionsRepository
import io.example.readcast.data.repository.SectionsRepositoryImpl


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Module
    @InstallIn(SingletonComponent::class)
    interface RepositoryModule {
        @Binds
        fun bindSearchRepo(impl: SearchRepositoryImpl): SearchRepository
    }

    @Provides
    fun provideSectionsRepository(
        api: HomeApiService,
    ): SectionsRepository = SectionsRepositoryImpl(
        pagingSourceFactory = { SectionsPagingSource(api) }
    )
}
