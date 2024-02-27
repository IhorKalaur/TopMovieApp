package ihor.kalaur.topmovieapp.navigation

import MovieGridScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import ihor.kalaur.topmovieapp.ui.screens.moviedetail.MovieDetailsScreen


@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.MovieGrid.route
    ) {
        composable(
            route = Screen.MovieGrid.route){
            MovieGridScreen(navController = navController)
        }
        composable(
            route = "${Screen.MovieDetails.route}/{movieId}",
            arguments = listOf(navArgument("movieId") { type = NavType.StringType})
            ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getString("movieId")!!
            MovieDetailsScreen(movieId = movieId)
        }
    }
}