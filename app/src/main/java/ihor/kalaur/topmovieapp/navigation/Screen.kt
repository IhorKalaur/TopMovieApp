package ihor.kalaur.topmovieapp.navigation

sealed class Screen(val route: String) {
    object MovieGrid: Screen("movieGridScreen")

    object MovieDetails: Screen("movieDetailScreen/{movieId}")
}
