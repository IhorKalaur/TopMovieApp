package ihor.kalaur.topmovieapp.util

import ihor.kalaur.topmovieapp.data.local.dao.entities.MovieEntity
import ihor.kalaur.topmovieapp.data.remote.response.MovieDetailDto
import ihor.kalaur.topmovieapp.data.remote.response.MovieDto
import ihor.kalaur.topmovieapp.model.Movie
import ihor.kalaur.topmovieapp.model.MovieDetail

fun MovieEntity.toMovie(): Movie {
    return Movie(
        movieId = movieId,
        movieName = movieName,
        yearOfProduction = yearOfProduction,
        partOfPosterUrl = partOfPosterUrl
    )
}

fun MovieDto.toEntity(): MovieEntity {
    return MovieEntity(
        movieId = movieId,
        movieName = movieName,
        yearOfProduction = yearOfProduction,
        partOfPosterUrl = partOfPosterUrl
    )
}

fun MovieDetailDto.toMovieDetail(): MovieDetail {
    return MovieDetail(
        title = title,
        tagline = tagline,
        rating = rating,
        releaseDate = releaseDate,
        runtime = runtime,
        budget = budget,
        revenue = revenue,
        overview = overview,
        partPosterUrl = partPosterUrl
    )
}


