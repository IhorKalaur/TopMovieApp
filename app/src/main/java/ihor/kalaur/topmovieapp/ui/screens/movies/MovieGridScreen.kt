package ihor.kalaur.topmovieapp.ui.screens.movies

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import ihor.kalaur.topmovieapp.R
import ihor.kalaur.topmovieapp.model.Movie
import ihor.kalaur.topmovieapp.util.Constants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieGridScreen(viewModel: MovieGridViewModel = hiltViewModel()) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Назва Додатку") })
        }
    ) { paddingValues ->
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            val listMovies by viewModel.movies.collectAsState()
            MovieGridComponent(listMovies)
        }
    }
}

@Composable
fun MovieGridComponent(movies: List<Movie>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(8.dp),
        content = {
            items(movies.size) { index ->
                MovieEntry(movies[index])
            }
        }
    )
}

@Composable
fun MovieEntry(movie: Movie) {
    Box(
        contentAlignment = Alignment.BottomCenter,
        modifier = Modifier
            .shadow(5.dp, RoundedCornerShape(10.dp))
            .clip(RoundedCornerShape(10.dp))
            .aspectRatio(0.7f)
    ) {
        val painter = rememberAsyncImagePainter(model = ImageRequest.Builder(LocalContext.current)
            .data(Constants.BASE_IMAGE_URL_SMALL_QUALITY + movie.partOfPosterUrl)
            .error(R.drawable.placeholder)
            .build())

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp)
        ) {
            Image(
                painter = painter,
                contentDescription = "MoviePoster",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .weight(0.85f)
                    .fillMaxWidth()
            )
            MovieText(movie.movieName)
            MovieText(movie.dateOfProduction)
        }
    }
}

@Composable
fun MovieText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp / 2)
    )
}
