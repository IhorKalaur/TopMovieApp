package ihor.kalaur.topmovieapp.ui.screens.moviedetail

import ihor.kalaur.topmovieapp.model.MovieDetail

sealed class MovieDetailState {
    object Loading : MovieDetailState()
    data class Success(val movieDetail: MovieDetail) : MovieDetailState()
    data class Error(val message: String) : MovieDetailState()
}