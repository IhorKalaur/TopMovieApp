package ihor.kalaur.topmovieapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController


@Composable
fun SetupNavGraph(navController: NavHostController) {
    /*NavHost(
        navController = navController,
        startDestination = Screen.MovieList.route
    ) {
        composable(route = Screen.MovieList.route){
            MovieListScreen(navController = navController)
        }
        composable(route = "${Screen.MovieDetails.route}/{movieId}",
            arguments = listOf(navArgument("movieId") { type = NavType.IntType})
            ){ backStackEntry ->
            val movieId = backStackEntry.arguments?.getString("movieId")!!.toInt()
            MovieDetailsScreen(movieId = movieId)
        }
    }*/
}