package com.example.movienighthelper.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.movienighthelper.base.BaseFragment
import com.example.movienighthelper.databinding.FragmentPopularMovieBinding
import com.example.movienighthelper.ui.adapter.PopularMovieAdapter
import com.example.movienighthelper.ui.model.PopularMovieUi
import com.example.movienighthelper.ui.viewmodels.PopularMovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PopularMovieFragment :
    BaseFragment<FragmentPopularMovieBinding>(FragmentPopularMovieBinding::inflate) {
    override val _viewModel: PopularMovieViewModel by activityViewModels()
    private lateinit var popularMovieAdapter: PopularMovieAdapter
    override fun onReigsterClick() {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
    }

    override fun onResume() {
        super.onResume()

    }

    private fun observeViewModel() {
        _viewModel.popularMovie.observe(viewLifecycleOwner) { result ->
            when {
                result?.isLoading == true -> {
                    Log.d(TAG, "observeViewModel:isLoading ")
                    binding.progressBar.visibility = View.VISIBLE
                }

                !result?.error.isNullOrEmpty() -> {
                    Log.d(TAG, "observeViewModel:Error ${result?.error} ")
                    binding.progressBar.visibility = View.GONE
                }

                else -> {
                    result?.PopularScreenState?.let {
                        Log.d(TAG, "observeViewModel:success ${result?.PopularScreenState} ")
                        binding.progressBar.visibility = View.GONE
                        setupSocialMediaAdapter(it)
                    }
                }


            }
        }

        _viewModel.insertMovieWatchLater.observe(viewLifecycleOwner) { result ->
            when {
                !result?.error.isNullOrEmpty() -> {
                    Toast.makeText(requireContext(), "Something Went wrong when insert in watchLater", Toast.LENGTH_SHORT).show()
                }

                else -> {
                    result?.isWatchLater?.let {
                        if(result.isWatchLater){
                            Toast.makeText(requireContext(), "ADD!!!", Toast.LENGTH_SHORT).show()
                        }else{
                            Toast.makeText(requireContext(), "Remove!!!", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }

    private fun setupSocialMediaAdapter(popularMovieUi: PopularMovieUi) {
        popularMovieAdapter =
            PopularMovieAdapter(popularMovieUi.resultPopularMovies, onSelectedItem = {

            }, onWatchListClick = { data,isWatch->
                _viewModel.insertWatchLater(data.id,isWatch)
            })
        binding.rvPopularMovie.adapter = popularMovieAdapter
    }

    companion object {
        val TAG = "POPULAR_MOVIE_FRAMGNET_LOG"
    }
}