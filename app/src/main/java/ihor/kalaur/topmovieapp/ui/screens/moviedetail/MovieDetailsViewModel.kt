package ihor.kalaur.topmovieapp.ui.screens.moviedetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ihor.kalaur.topmovieapp.data.repository.MovieRepositoryImpl
import ihor.kalaur.topmovieapp.model.MovieDetail
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val repository: MovieRepositoryImpl
) : ViewModel() {


    private val _movieDetail = MutableStateFlow<MovieDetail?>(null)
    val movieDetail: StateFlow<MovieDetail?> = _movieDetail.asStateFlow()

    fun fetchMovieDetail(movieId: Int) {
        viewModelScope.launch {
            _movieDetail.value = repository.getMovieDetail(movieId)
        }
    }


}