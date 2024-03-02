package com.example.moiveshub.ym_ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.moiveshub.Api.MoviesApi
import com.example.moiveshub.Api.Services
import com.example.moiveshub.my_fragments.HomeFragment
import com.example.moiveshub.my_fragments.SearchFragment
import com.example.moiveshub.my_mvvm.MovieRepository
import com.example.moiveshub.my_mvvm.MovieViewModel
import com.example.moiveshub.my_mvvm.MovieViewModelFactory
import com.example.moiveshub.R
import com.example.moiveshub.databinding.ActivityMainBinding
import com.example.moiveshub.my_fragments.SavedFragment
import com.example.moiveshub.my_models.MoviesDatabase


class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val retrofit = Services.retrofit().create(MoviesApi::class.java)

        val dao = MoviesDatabase.createDb(this).getDao()
        val repository = MovieRepository(retrofit, dao)
        val movieViewModelFactory = MovieViewModelFactory(repository)
        viewModel = ViewModelProvider(this, movieViewModelFactory)[MovieViewModel::class.java]


        loadFragment(HomeFragment())

        binding.bottomNavigationView.setOnItemSelectedListener { item ->

            when (item.itemId) {

                R.id.NSaved -> loadFragment(SavedFragment())
                R.id.NSearch -> loadFragment(SearchFragment())

                else  -> loadFragment(HomeFragment())

            }
            true
        }

    }

    private fun loadFragment(fragment: Fragment) {
        val support = supportFragmentManager
        val transaction = support.beginTransaction()
        transaction.replace(R.id.frameLayout, fragment)
        transaction.commit()
    }

}

