package com.satoritech.movieapp.ui.movies

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import androidx.recyclerview.widget.RecyclerView
import com.satoritech.movieapp.data.entities.Movie
import com.satoritech.movieapp.databinding.ItemMovieBinding
import com.satoritech.movieapp.utils.ApiConstants

class MoviesAdapter(private val listener : MovieItemListener) :RecyclerView.Adapter<MovieViewHolder>() {

    interface MovieItemListener{
        fun onClickedMovie(movieId: Int)
    }

    private val items = ArrayList<Movie>()

    fun setItems(items: ArrayList<Movie>){
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding: ItemMovieBinding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) = holder.bind(items[position])
}

class MovieViewHolder(private val itemBinding: ItemMovieBinding, private val listener: MoviesAdapter.MovieItemListener) : RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener {

    private lateinit var movie: Movie

    init {
        itemBinding.root.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    fun bind(item: Movie) {
        this.movie = item
        Glide.with(itemBinding.root)
            .load(ApiConstants.IMAGE_API_PREFIX+movie.image)
            .into(itemBinding.itemImage)
    }

    override fun onClick(v: View?) {
        listener.onClickedMovie(movie.id)
    }
}