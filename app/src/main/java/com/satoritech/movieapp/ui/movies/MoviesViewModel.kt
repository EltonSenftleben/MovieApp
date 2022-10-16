package com.satoritech.movieapp.ui.movies

import androidx.lifecycle.ViewModel
import com.satoritech.movieapp.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel(){

    val movies = repository.getMovies()
}