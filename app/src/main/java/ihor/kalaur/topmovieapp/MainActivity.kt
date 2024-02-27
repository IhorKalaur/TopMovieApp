package ihor.kalaur.topmovieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import ihor.kalaur.topmovieapp.ui.screens.movies.MovieGridScreen
import ihor.kalaur.topmovieapp.ui.theme.TopMovieAppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TopMovieAppTheme {
                // A surface container using the 'background' color from the theme

                MovieGridScreen()

            }
        }
    }
}
