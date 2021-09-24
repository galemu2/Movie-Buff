package com.ctrlaccess.moviebuff.di

import com.ctrlaccess.moviebuff.data.remote.MoviesApi
import com.ctrlaccess.moviebuff.repo.MoviesRepo
import com.ctrlaccess.moviebuff.repo.MoviesRepoInterface
import com.ctrlaccess.moviebuff.util.UtilObjects.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideMoviesApi(retrofit: Retrofit) = retrofit.create(MoviesApi::class.java)

    @Provides
    @Singleton
    fun provideMoviesRepositoryInterface(
        api:MoviesApi
    )  = MoviesRepo(api) as MoviesRepoInterface
}