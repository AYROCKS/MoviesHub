package com.example.moiveshub.my_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.example.moiveshub.databinding.FragmentHomeBinding
import com.example.moiveshub.my_models.Result
import com.example.moiveshub.my_mvvm.MovieViewModel
import com.example.moiveshub.ym_ui.MovieAdapter


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: MovieViewModel
    private var isScrolling = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[MovieViewModel::class.java]

        val linearLayoutManager = GridLayoutManager(context, 1, GridLayoutManager.HORIZONTAL, false)
        binding.homeRecycler.layoutManager = linearLayoutManager

        val topManager = GridLayoutManager(context, 1, GridLayoutManager.HORIZONTAL, false)
        binding.recyclerView2.layoutManager = topManager

        val popularManager = GridLayoutManager(context, 1, GridLayoutManager.HORIZONTAL, false)
        binding.recyclerView.layoutManager = popularManager

        val adapter = MovieAdapter(viewModel)
        val latestAdapter = MovieAdapter(viewModel)
        val popularAdapter = MovieAdapter(viewModel)
        binding.homeRecycler.adapter = adapter
        binding.recyclerView2.adapter = latestAdapter
        binding.recyclerView.adapter = popularAdapter


        fetchData(
            viewModel.movies,
            adapter,
            binding.progressBar,
            binding.homeRecycler,
            linearLayoutManager,
            viewModel::loadMoreMovies
        )

        fetchData(
            viewModel.latestMovies,
            latestAdapter,
            binding.progressBar2,
            binding.recyclerView2,
            topManager,
            viewModel::latestMovies
        )

        fetchData(
            viewModel.popularMovies,
            popularAdapter,
            binding.progressBar3,
            binding.recyclerView,
            popularManager,
            viewModel::popularMovies
        )

    }


    private fun fetchData(
        liveData: LiveData<List<Result>>,
        adapter: MovieAdapter,
        progressBar: ProgressBar,
        recyclerView: RecyclerView,
        layoutManager: GridLayoutManager,
        unit: () -> Unit
    ) {

        unit()

        liveData.observe(viewLifecycleOwner) {
            adapter.differ.submitList(it)
            progressBar.visibility = View.GONE
        }


        recyclerView.addOnScrollListener(object : OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    isScrolling = true
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val currentItems = layoutManager.childCount
                val totalItems = layoutManager.itemCount
                val scrolledItems = layoutManager.findFirstVisibleItemPosition()

                if (isScrolling && currentItems + scrolledItems == totalItems-4) {
                    unit()
                    isScrolling = false
                    progressBar.visibility = View.VISIBLE
                }

            }
        })


    }

}
