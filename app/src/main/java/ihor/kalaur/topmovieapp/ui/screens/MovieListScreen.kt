package ihor.kalaur.topmovieapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ihor.kalaur.topmovieapp.R

const val NUMBER_OF_COLUMN: Int = 3

@Composable
fun MovieListScreen() {
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize()
    ) {
        MovieGridComponent(items = listOf("first", "second", "third", "fourth"))

    }
}

@Preview
@Composable
fun PreviewMovieListScreen(){
    MovieGridComponent(items = listOf("first", "second", "third", "fourth"))
}

@Composable
fun MovieGridComponent(items: List<String>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(NUMBER_OF_COLUMN),
        content = {
            items(items.size) { index ->
                MovieEntry(items[index])
            }
        }
    )
}

@Composable
fun MovieEntry(movieName: String) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .shadow(5.dp, RoundedCornerShape(10.dp))
            .clip(RoundedCornerShape(10.dp))
            .aspectRatio(1f)
    ) {
        Column {
            val placeholderPainter = painterResource(id = R.drawable.placeholder) //TODO downloadImageFromResource
            Image(painter = placeholderPainter,
                contentDescription = "MovieName",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(120.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Text(
                text = movieName, //TODO getFromEntry
                fontFamily = FontFamily.Cursive,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = "YearOfProduction", //TODO getFromEntry
                fontFamily = FontFamily.Serif,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMovieEntry() {
    MovieEntry("name")
}
