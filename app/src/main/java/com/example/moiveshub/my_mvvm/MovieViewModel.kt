package com.example.moiveshub.my_mvvm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moiveshub.my_models.MovieModel
import com.example.moiveshub.my_models.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class MovieViewModel(private val repository: MovieRepository) : ViewModel() {


    fun getData() : LiveData<List<Result>> = repository.getData()
    fun deleteData(result: Result)  = repository.deleteData(result)
    fun addData(result: Result) = repository.addData(result)



    private val _movies = MutableLiveData<List<Result>>()
    val movies: LiveData<List<Result>> get() = _movies
    private var page = 1

    private val _Smovies = MutableLiveData<List<Result>>()
    val searchMovies: LiveData<List<Result>> get() = _Smovies

    private val _Lmovies = MutableLiveData<List<Result>>()
    val latestMovies: LiveData<List<Result>> get() = _Lmovies
    private var Lpage = 1

    private val _Pmovies = MutableLiveData<List<Result>>()
    val popularMovies: LiveData<List<Result>> get() = _Pmovies
    private var Ppage = 1

    private fun fetchData(
        fetchFunction: suspend (Int) -> Response<MovieModel>,
        page: Int,
        liveData: MutableLiveData<List<Result>>,
        errorTag: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = fetchFunction(page)
                if (response.isSuccessful) {
                    val currentList = liveData.value.orEmpty().toMutableList()
                    currentList.addAll(response.body()?.results ?: emptyList())
                    liveData.postValue(currentList)

                } else {
                    Log.e("AY", "Error loading $errorTag: ${response.message()}")
                }
            } catch (e: Exception) {
                Log.e("AY", "Network exception: ${e.message}")
            }
        }
    }

    fun loadMoreMovies() {
        fetchData(repository::getMovies, page++, _movies, "movies")
    }

    fun latestMovies() {
        fetchData(repository::getLatestMovies, Lpage++, _Lmovies, "latest movies")
    }

    fun popularMovies() {
        fetchData(repository::getPopularMovies, Ppage++, _Pmovies, "popular movies")
    }


    fun searchMovies(search: String) {

        viewModelScope.launch(Dispatchers.IO) {

            try {
                val response = repository.searchMovies(search)

                if(response.isSuccessful){
                    _Smovies.postValue(response.body()?.results ?: emptyList())
                }

            } catch (e: Exception) {
                Log.e("AY", "Bro")

            }
        }
    }




}






