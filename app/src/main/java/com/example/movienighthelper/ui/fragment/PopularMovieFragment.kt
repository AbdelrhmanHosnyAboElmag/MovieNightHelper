package com.example.movienighthelper.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import com.example.movienighthelper.base.BaseFragment
import com.example.movienighthelper.databinding.FragmentPopularMovieBinding
import com.example.movienighthelper.ui.viewmodels.PopularMovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PopularMovieFragment: BaseFragment<FragmentPopularMovieBinding>(FragmentPopularMovieBinding::inflate) {
    override val _viewModel: PopularMovieViewModel by activityViewModels()
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
                }

                !result?.error.isNullOrEmpty() -> {
                    Log.d(TAG, "observeViewModel:Error ${result?.error} ")
                }

                else -> {
                    result?.PopularScreenState?.let {
                        Log.d(TAG, "observeViewModel:${it}")
                    }
                }


            }
        }
    }
    companion object {
        val TAG ="POPULAR_MOVIE_FRAMGNET_LOG"
    }
}