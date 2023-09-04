package com.example.movienighthelper.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.movienighthelper.R
import com.example.movienighthelper.base.BaseFragment
import com.example.movienighthelper.data.api.response.Cast
import com.example.movienighthelper.data.api.response.Crew
import com.example.movienighthelper.databinding.FragmentDetailBinding
import com.example.movienighthelper.ui.adapter.ActorCastAdapter
import com.example.movienighthelper.ui.adapter.DirectorCastAdapter
import com.example.movienighthelper.ui.adapter.PopularMovieAdapter
import com.example.movienighthelper.ui.adapter.SimilarMovieAdapter
import com.example.movienighthelper.ui.model.MovieDetailUi
import com.example.movienighthelper.ui.model.MovieSimilarUi
import com.example.movienighthelper.ui.viewmodels.MovieDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailMovieFragment : BaseFragment<FragmentDetailBinding>(FragmentDetailBinding::inflate) {
    override val _viewModel: MovieDetailViewModel by viewModels()
    private val args: DetailMovieFragmentArgs by navArgs()
    private lateinit var similarMovieAdapter: SimilarMovieAdapter
    private lateinit var actorAdapter: ActorCastAdapter
    private lateinit var directorAdapter: DirectorCastAdapter


    override fun onReigsterClick() {
        binding.addToWatchListButton.setOnClickListener {
            if (binding.addToWatchListButton.text.equals("Add To Watchlist")) {
                _viewModel.insertWatchLater(args.movieId, true)
            }else{
                _viewModel.insertWatchLater(args.movieId, false)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        callApi()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
    }

    private fun observeViewModel() {
        _viewModel.detailMovie.observe(viewLifecycleOwner) { result ->
            when {
                result?.isLoading == true -> {
                    binding.progressBarDetail.visibility = View.VISIBLE
                }

                !result?.error.isNullOrEmpty() -> {
                    binding.progressBarDetail.visibility = View.GONE
                    Toast.makeText(requireContext(), "Error:${result?.error}", Toast.LENGTH_SHORT)
                        .show()
                }

                else -> {
                    result?.MovieDetailState?.let {
                        binding.progressBarDetail.visibility = View.GONE
                        setupView(it)
                    }
                }

            }
        }

        _viewModel.similarMovie.observe(viewLifecycleOwner) { result ->
            when {
                result?.isLoading == true -> {
                    binding.progressBarDetail.visibility = View.VISIBLE
                }

                !result?.error.isNullOrEmpty() -> {
                    binding.progressBarDetail.visibility = View.GONE
                    Toast.makeText(requireContext(), "Error:${result?.error}", Toast.LENGTH_SHORT)
                        .show()
                }

                else -> {
                    result?.MovieSimilarState?.let {
                        binding.progressBarDetail.visibility = View.GONE
                        setupSimilarMovieAdapter(it)
                    }
                }
            }
        }

        _viewModel.actorAndDirector.observe(viewLifecycleOwner) { result ->
            when {
                result?.isLoading == true -> {
                    binding.progressBarDetail.visibility = View.VISIBLE
                }

                !result?.error.isNullOrEmpty() -> {
                    binding.progressBarDetail.visibility = View.GONE
                    Toast.makeText(requireContext(), "Error:${result?.error}", Toast.LENGTH_SHORT)
                        .show()
                }

                else -> {
                    result?.castActorAndDirector?.let {
                        binding.progressBarDetail.visibility = View.GONE
                        setupActorAdapter(result.castActorAndDirector.first)
                        setupDirectorAdapter(result.castActorAndDirector.second)
                    }
                }
            }
        }

        _viewModel.insertMovieWatchLater.observe(viewLifecycleOwner) { result ->
            when {
                !result?.error.isNullOrEmpty() -> {
                    Toast.makeText(
                        requireContext(),
                        "Something Went wrong when insert in watchLater",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                else -> {
                    result?.isWatchLater?.let {
                        if (!result.isWatchLater) {
                            binding.addToWatchListButton.text = "Add To Watchlist"
                        } else {
                            binding.addToWatchListButton.text = "Remove From Watchlist"
                        }
                    }
                }
            }
        }
    }

    private fun callApi() {
        _viewModel.loadMoviesDetail(args.movieId)
        _viewModel.loadSimilarMovies(args.movieId)
        _viewModel.loadActorAndDirectorMovies(args.movieId)
    }

    private fun setupView(movieDetailUiItem: MovieDetailUi) {
        Glide.with(requireContext())
            .load("https://image.tmdb.org/t/p/w500/${movieDetailUiItem.poster_path}")
            .centerCrop()
            .placeholder(R.drawable.image_place_holder)
            .into(binding.movieImageView)
        binding.titleTextView.text = movieDetailUiItem.title
        binding.overviewTextView.text = movieDetailUiItem.overview
        binding.taglineTextView.text = "Tagline: ${movieDetailUiItem.tagline}"
        binding.revenueTextView.text = "Revenue: $${movieDetailUiItem.revenue}"
        binding.releaseDateTextView.text = "Release Date: ${movieDetailUiItem.release_date}"
        binding.statusTextView.text = "Status: ${movieDetailUiItem.status}"
        if (args.isWatch) {
            binding.addToWatchListButton.text = "Remove From Watchlist"
        } else {
            binding.addToWatchListButton.text = "Add To Watchlist"
        }
    }

    private fun setupSimilarMovieAdapter(movieSimilarUi: MovieSimilarUi) {
        similarMovieAdapter = SimilarMovieAdapter(movieSimilarUi.results.take(5))
        binding.similarMoviesRecyclerView.adapter = similarMovieAdapter
    }

    private fun setupActorAdapter(actors: List<Cast>) {
        actorAdapter = ActorCastAdapter(actors)
        binding.castActorMoviesRecyclerView.adapter = actorAdapter
    }

    private fun setupDirectorAdapter(directors: List<Crew>) {
        directorAdapter = DirectorCastAdapter(directors)
        binding.castDirectorRecyclerView.adapter = directorAdapter
    }

}