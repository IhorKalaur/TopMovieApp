package ihor.kalaur.topmovieapp.ui.screens.movies


import android.nfc.tech.MifareUltralight.PAGE_SIZE
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ihor.kalaur.topmovieapp.data.repository.MovieRepositoryImpl
import ihor.kalaur.topmovieapp.model.Movie
import ihor.kalaur.topmovieapp.util.Constants.INITIAL_PAGE
import ihor.kalaur.topmovieapp.util.PaginationState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import javax.inject.Inject

@HiltViewModel
class MovieGridViewModel @Inject constructor(
    private val repository: MovieRepositoryImpl
) : ViewModel() {

    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies: StateFlow<List<Movie>> = _movies.asStateFlow()

    private val _pagingState =
        MutableStateFlow<PaginationState>(PaginationState.LOADING)
    val pagingState: StateFlow<PaginationState>
        get() = _pagingState.asStateFlow()

    private var page = 0
    var canPaginate by mutableStateOf(false)

    init {
        collectTopRatedMovies()
    }

    fun getMovies(movieId : Int) {
        if (page == 0 || (canPaginate) && _pagingState.value == PaginationState.REQUEST_INACTIVE) {
            _pagingState.update { if (page == 0) PaginationState.LOADING else PaginationState.PAGINATING }
        }
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = repository.getTopRatedMovies(page)
                    //.getPagingNotesByDate(subjectID, PAGE_SIZE, page * PAGE_SIZE)

                canPaginate = result.size == PAGE_SIZE

                if (page == INITIAL_PAGE) {
                    if (result.isEmpty()) {
                        _pagingState.update { PaginationState.EMPTY }
                        return@launch
                    }
                    _movies.value.clear()
                    _movies.value.addAll(result)
                } else {
                    _notesList.value.addAll(result)
                }

                _pagingState.update { PaginationState.REQUEST_INACTIVE }

                if (canPaginate) {
                    page++
                }

                if (!canPaginate) {
                    _pagingState.update { PaginationState.PAGINATION_EXHAUST }
                }
            } catch (e: Exception) {
                _pagingState.update { if (page == INITIAL_PAGE) PaginationState.ERROR else PaginationState.PAGINATION_EXHAUST }
            }
        }
    }


    private fun collectTopRatedMovies() {
        viewModelScope.launch {
            repository.getTopRatedMovies(1).collect { moviesList ->
                _movies.value = moviesList
            }
        }
    }


}