package com.satoritech.movieapp.data.remote

import com.satoritech.movieapp.utils.ApiConstants
import javax.inject.Inject

class MovieRemoteDataSource @Inject constructor(
    private val movieService: MovieService
): BaseDataSource() {

    suspend fun getMovies() = getResult { movieService.getAllMovies(ApiConstants.API_KEY) }

    suspend fun getMovie(id: Int) = getResult { movieService.getMovie(id) }
}