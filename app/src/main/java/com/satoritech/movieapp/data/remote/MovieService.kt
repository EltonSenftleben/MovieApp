package com.satoritech.movieapp.data.remote

import com.satoritech.movieapp.data.entities.Movie
import com.satoritech.movieapp.data.entities.MovieList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {
    @GET("movie/popular")
    suspend fun getAllMovies(@Query("api_key") key : String) : Response<MovieList>

    @GET("movie/{movie_id}")
    suspend fun getMovie(@Path("movie_id") id: Int): Response<Movie>
}