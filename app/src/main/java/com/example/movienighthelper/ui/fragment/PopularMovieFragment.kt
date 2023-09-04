package com.example.movienighthelper.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import com.example.movienighthelper.base.BaseFragment
import com.example.movienighthelper.databinding.FragmentPopularMovieBinding
import com.example.movienighthelper.ui.adapter.PopularMovieAdapter
import com.example.movienighthelper.ui.model.PopularMovieUi
import com.example.movienighthelper.ui.viewmodels.PopularMovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PopularMovieFragment :
    BaseFragment<FragmentPopularMovieBinding>(FragmentPopularMovieBinding::inflate) {
    override val _viewModel: PopularMovieViewModel by viewModels()
    private lateinit var popularMovieAdapter: PopularMovieAdapter
    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    private val searchDelayMillis = 1000L // 1 second delay

    override fun onReigsterClick() {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        performSearch()
    }

    override fun onResume() {
        super.onResume()

    }

    private fun observeViewModel() {
        _viewModel.popularMovie.observe(viewLifecycleOwner) { result ->
            when {
                result?.isLoading == true -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                !result?.error.isNullOrEmpty() -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), "Error:${result?.error}", Toast.LENGTH_SHORT).show()
                }

                else -> {
                    result?.PopularScreenState?.let {
                        binding.progressBar.visibility = View.GONE
                        setupSocialMediaAdapter(it)
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
                        if (result.isWatchLater) {
                            Toast.makeText(requireContext(), "ADD!!!", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(requireContext(), "Remove!!!", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }

    private fun setupSocialMediaAdapter(popularMovieUi: PopularMovieUi) {
        popularMovieAdapter =
            PopularMovieAdapter(popularMovieUi.resultPopularMovies, onSelectedItem = { data, isWatch->
                _viewModel.navigateTo(PopularMovieFragmentDirections.actionPopularMovieFragmentToDetailMovieFragment(data.id,isWatch))
            }, onWatchListClick = { data, isWatch ->
                _viewModel.insertWatchLater(data.id, isWatch)
            })
        binding.rvPopularMovie.adapter = popularMovieAdapter
    }

    private fun performSearch() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    if (it.isNullOrEmpty()) {
                        _viewModel.loadWatchLater()
                        Log.d("ersdfads", ":2")
                    } else {
                        _viewModel.loadWatchLater()
                    }
                }

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                coroutineScope.coroutineContext.cancelChildren()
                coroutineScope.launch {
                    delay(searchDelayMillis)
                    newText?.let {
                        if (it.isNullOrEmpty()) {
                            _viewModel.loadWatchLater()
                        } else {
                            _viewModel.loadWatchLater()
                        }
                    }
                }
                return true
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineScope.coroutineContext.cancelChildren() // Cancel any ongoing jobs when the activity is destroyed
    }

    companion object {
        val TAG = "POPULAR_MOVIE_FRAMGNET_LOG"
    }
}