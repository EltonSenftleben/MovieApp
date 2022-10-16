package com.satoritech.movieapp.data.remote

import com.satoritech.movieapp.utils.ApiConstants
import javax.inject.Inject

class MovieRemoteDataSource @Inject constructor(
    private val movieService: MovieService
): BaseDataSource() {

    suspend fun getMovies() = getResult { movieService.getAllMovies("3fe0d67705c46ef0b5e8b8087933b8b6") }

    suspend fun getMovie(id: Int) = getResult { movieService.getMovie(id) }
}