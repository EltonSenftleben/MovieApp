package com.satoritech.movieapp.ui.moviedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.satoritech.movieapp.data.entities.Movie
import com.satoritech.movieapp.databinding.FragmentMovieDetailBinding
import com.satoritech.movieapp.utils.ApiConstants
import com.satoritech.movieapp.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment : Fragment() {

    private var _binding : FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel : MovieDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getInt("id")?.let { viewModel.start(it) }
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.movie.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    bindMovie(it.data!!)
                    binding.progressBar.visibility = View.GONE
                    binding.movieCl.visibility = View.VISIBLE
                }

                Resource.Status.ERROR ->
                    Toast.makeText(activity, it.message, Toast.LENGTH_SHORT)

                Resource.Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.movieCl.visibility = View.GONE
                }
            }
        })
    }

    private fun bindMovie(movie: Movie) {
        binding.textTitle.text = movie.title
        binding.textPopularity.text = " "+movie.popularity.toString()
        binding.textVote.text = " "+movie.vote_average.toString()
        Glide.with(binding.root)
            .load(ApiConstants.IMAGE_API_PREFIX + movie.image)
            .into(binding.image)
    }
}