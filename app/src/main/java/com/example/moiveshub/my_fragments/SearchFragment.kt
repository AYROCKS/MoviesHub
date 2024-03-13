package com.example.moiveshub.my_fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.example.moiveshub.my_mvvm.MovieViewModel
import com.example.moiveshub.ym_ui.MovieAdapter
import com.example.moiveshub.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var viewModel: MovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[MovieViewModel::class.java]

        val linearLayoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
        binding.searchRecyclerView.layoutManager = linearLayoutManager

        val adapter = MovieAdapter()
        binding.searchRecyclerView.adapter = adapter


        viewModel.searchMovies.observe(viewLifecycleOwner) {
            adapter.differ.submitList(it)
        }

        binding.searchText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {

                Handler(Looper.getMainLooper()).postDelayed({
                    viewModel.searchMovies(s.toString())

                }, 300)

            }
        })



    }
}