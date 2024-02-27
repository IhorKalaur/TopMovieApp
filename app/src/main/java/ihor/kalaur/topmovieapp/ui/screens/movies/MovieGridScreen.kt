import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import ihor.kalaur.topmovieapp.R
import ihor.kalaur.topmovieapp.model.Movie
import ihor.kalaur.topmovieapp.navigation.Screen
import ihor.kalaur.topmovieapp.ui.screens.movies.MovieGridViewModel
import ihor.kalaur.topmovieapp.util.Constants


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieGridScreen(
    viewModel: MovieGridViewModel = hiltViewModel(),
    navController: NavHostController
) {
    Column {
        TopAppBar(
            title = {
                Text(
                    text = stringResource(id = R.string.app_name),
                    color = Color.Blue
                )
            }
        )
        val movies = viewModel.moviePagingFlow.collectAsLazyPagingItems()
        MovieGridComponent(movies = movies, navController)
    }
}

@Composable
fun MovieGridComponent(
    movies: LazyPagingItems<Movie>,
    navController: NavHostController
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(8.dp),

        ) {
        items(movies.itemCount) { index ->
            val movie = movies[index]

            MovieItem(movie = movie!!, navController)
        }

        movies.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item { CircularProgressIndicator() }
                }

                loadState.append is LoadState.Loading -> {
                    item { CircularProgressIndicator() }
                }

                loadState.refresh is LoadState.Error -> {
                    item { Text(text = stringResource(R.string.refresh_error_message)) }
                }

                loadState.append is LoadState.Error -> {
                    item { Text(text = stringResource(R.string.download_data_error_message)) }
                }
            }
        }
    }
}

@Composable
fun MovieItem(
    movie: Movie,
    navController: NavHostController
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(4.dp)
            .clickable { navController.navigate("${Screen.MovieDetails.route}/${movie.movieId}") }
    ) {
        val painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .data(Constants.BASE_IMAGE_URL_SMALL_QUALITY + movie.partOfPosterUrl)
                .error(R.drawable.placeholder)
                .build()
        )

        Image(
            painter = painter,
            contentDescription = movie.movieName,
            modifier = Modifier
                .size(100.dp, 150.dp),
            contentScale = ContentScale.Crop
        )
        if (painter.state is AsyncImagePainter.State.Loading) {
            CircularProgressIndicator()
        }
        Text(
            text = movie.movieName,
            textAlign = TextAlign.Center,
            fontSize = 14.sp,
            modifier = Modifier.padding(2.dp)
        )
        Text(
            text = movie.yearOfProduction,
            textAlign = TextAlign.Center,
            fontSize = 12.sp,
            modifier = Modifier.padding(2.dp)
        )
    }
}


