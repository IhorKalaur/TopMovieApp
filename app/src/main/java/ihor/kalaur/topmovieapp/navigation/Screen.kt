package ihor.kalaur.topmovieapp.navigation

sealed class Screen(val route: String) {
    internal object MovieList: Screen("movie_list_screen")

    object MovieDetails: Screen("movie_details_screen")
}
