package com.ctrlaccess.moviebuff.di

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.ctrlaccess.moviebuff.R
import com.ctrlaccess.moviebuff.data.remote.MoviesApi
import com.ctrlaccess.moviebuff.repo.MoviesRepo
import com.ctrlaccess.moviebuff.repo.RepositoryInterface
import com.ctrlaccess.moviebuff.util.UtilObjects.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun provideMoviesApi(retrofit: Retrofit): MoviesApi = retrofit.create(MoviesApi::class.java)

    @Provides
    @Singleton
    fun provideMoviesRepositoryInterface(
        api: MoviesApi
    ) = MoviesRepo(api) as RepositoryInterface

    @Provides
    @Singleton
    fun provideGlideInstance(
        @ApplicationContext context: Context
    ) = Glide.with(context)
        .setDefaultRequestOptions(
            RequestOptions()
                .placeholder(R.drawable.movie_place_holder)
                .error(R.drawable.movie_place_holder)
        )
}