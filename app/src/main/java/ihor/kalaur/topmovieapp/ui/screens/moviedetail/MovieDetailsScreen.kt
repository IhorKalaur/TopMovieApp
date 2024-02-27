package ihor.kalaur.topmovieapp.ui.screens.moviedetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import ihor.kalaur.topmovieapp.R
import ihor.kalaur.topmovieapp.util.Constants
import java.text.NumberFormat
import java.util.Locale

@Composable
fun MovieDetailsScreen(
    viewModel: MovieDetailsViewModel = hiltViewModel(), movieId: String
) {
    LaunchedEffect(movieId) {
        viewModel.fetchMovieDetail(movieId)
    }

    val movieDetailState by viewModel.movieDetailState.collectAsState()

    when (movieDetailState) {
        is MovieDetailState.Loading -> {
            Text(text = stringResource(id = R.string.downloading_message))
            CircularProgressIndicator()
        }

        is MovieDetailState.Success -> {
            val movieDetail = (movieDetailState as MovieDetailState.Success).movieDetail
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                val painter = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(Constants.BASE_IMAGE_URL_GOOD_QUALITY + movieDetail.partPosterUrl)
                        .error(R.drawable.placeholder).build()
                )
                MoviePoster(painter)
                MovieTitleAndDescription(
                    title = movieDetail.title, description = movieDetail.tagline
                )
                MovieInfo(
                    releaseDate = movieDetail.releaseDate,
                    rating = movieDetail.rating,
                    runtime = movieDetail.runtime,
                    budget = movieDetail.budget,
                    revenue = movieDetail.revenue
                )
                Overview(text = movieDetail.overview)
            }
        }

        is MovieDetailState.Error -> {
            Text(text = stringResource(id = R.string.download_data_error_message))
        }
    }
}

@Composable
fun MoviePoster(painter: Painter) {
    Image(
        painter = painter,
        contentDescription = "Movie Poster",
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 100.dp),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun MovieTitleAndDescription(title: String, description: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.headlineMedium,
        modifier = Modifier.padding(8.dp)
    )
    Text(
        text = description,
        style = MaterialTheme.typography.titleMedium,
        modifier = Modifier.padding(8.dp)
    )
}

@Composable
fun MovieInfo(releaseDate: String, rating: Double, runtime: Int, budget: Long, revenue: Long) {
    Column(modifier = Modifier.padding(8.dp)) {
        Text(text = "Release Date: $releaseDate")
        Text(text = "Rating: $rating")
        Text(text = "Runtime: $runtime minutes")
        Text(text = "Budget: $${budget.formatAsCurrency()}")
        Text(text = "Revenue: $${revenue.formatAsCurrency()}")
    }
}

@Composable
fun Overview(text: String) {
    Column(modifier = Modifier.padding(8.dp)) {
        Text(text = "Overview", style = MaterialTheme.typography.titleLarge)
        Text(text = text, style = MaterialTheme.typography.bodyLarge)
    }
}

fun Long.formatAsCurrency(): String {
    val formatter = NumberFormat.getCurrencyInstance(Locale.US)
    return formatter.format(this)
}