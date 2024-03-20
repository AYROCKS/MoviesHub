package com.example.moiveshub.my_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moiveshub.databinding.FragmentSavedBinding
import com.example.moiveshub.my_mvvm.MovieViewModel
import com.example.moiveshub.ym_ui.MovieAdapter

class SavedFragment : Fragment() {


    private lateinit var binding: FragmentSavedBinding
    private lateinit var viewModel: MovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentSavedBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[MovieViewModel::class.java]

        val layoutManager = GridLayoutManager(context,2, GridLayoutManager.VERTICAL, false)
        binding.savedRecyclerview.layoutManager = layoutManager

        val adapter = MovieAdapter()
        binding.savedRecyclerview.adapter = adapter

        viewModel.getData().observe(viewLifecycleOwner) {
            adapter.differ.submitList(it)
        }

    }

}