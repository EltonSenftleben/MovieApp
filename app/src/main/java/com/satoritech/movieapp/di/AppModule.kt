package com.satoritech.movieapp.di

import android.content.Context
import com.satoritech.movieapp.data.local.AppDatabase
import com.satoritech.movieapp.data.local.MovieDao
import com.satoritech.movieapp.data.remote.MovieRemoteDataSource
import com.satoritech.movieapp.data.remote.MovieService
import com.satoritech.movieapp.data.repository.MovieRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.satoritech.movieapp.utils.ApiConstants
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
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson) : Retrofit = Retrofit.Builder()
        .baseUrl(ApiConstants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun provideMovieService(retrofit: Retrofit): MovieService = retrofit.create(MovieService::class.java)

    @Singleton
    @Provides
    fun provideMovieRemoteDataSource(movieService: MovieService) = MovieRemoteDataSource(movieService)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideMovieDao(db: AppDatabase) = db.movieDao()

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: MovieRemoteDataSource,
                          localDataSource: MovieDao) =
        MovieRepository(remoteDataSource, localDataSource)
}