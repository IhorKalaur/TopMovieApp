package ihor.kalaur.topmovieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ihor.kalaur.topmovieapp.navigation.SetupNavGraph
import ihor.kalaur.topmovieapp.ui.theme.TopMovieAppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TopMovieAppTheme {

                val navController = rememberNavController()
                SetupNavGraph(navController = navController)

            }
        }
    }


}
