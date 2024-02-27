package ihor.kalaur.topmovieapp.ui.screens.moviedetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ihor.kalaur.topmovieapp.data.repository.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    val movieDetailState = MutableStateFlow<MovieDetailState>(MovieDetailState.Loading)

    fun fetchMovieDetail(movieId: String) {
        viewModelScope.launch {
            movieDetailState.value = MovieDetailState.Loading
            try {
                val movieDetail = repository.getMovieDetail(movieId)
                movieDetailState.value = MovieDetailState.Success(movieDetail)
            } catch (e: Exception) {
                movieDetailState.value = MovieDetailState.Error(e.message ?: "Unknown Error")
            }
        }
    }

}