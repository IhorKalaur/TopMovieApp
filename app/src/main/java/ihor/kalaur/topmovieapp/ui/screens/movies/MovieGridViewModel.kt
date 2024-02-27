package ihor.kalaur.topmovieapp.ui.screens.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import ihor.kalaur.topmovieapp.data.repository.MovieRepository
import javax.inject.Inject

@HiltViewModel
class MovieGridViewModel @Inject constructor(
    repository: MovieRepository
) : ViewModel() {

    val moviePagingFlow = repository.getMoviesPagingFlow().cachedIn(viewModelScope)

}