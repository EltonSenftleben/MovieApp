package com.satoritech.movieapp.data.repository

import com.satoritech.movieapp.data.local.MovieDao
import com.satoritech.movieapp.data.remote.MovieRemoteDataSource
import com.satoritech.movieapp.utils.performGetOperation
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val remoteDataSource: MovieRemoteDataSource,
    private val localDataSource: MovieDao
) {
    fun getMovie(id: Int) = performGetOperation(
        databaseQuery = { localDataSource.getMovie(id)},
        networkCall = { remoteDataSource.getMovie(id)},
        saveCallResult = { localDataSource.insert(it)}
    )

    fun getMovies() = performGetOperation(
        databaseQuery = { localDataSource.getAllMovies() },
        networkCall = { remoteDataSource.getMovies() },
        saveCallResult = { localDataSource.insertAll(it.results) }
    )
}