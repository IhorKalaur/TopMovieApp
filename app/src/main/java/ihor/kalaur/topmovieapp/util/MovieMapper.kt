package ihor.kalaur.topmovieapp.util

import ihor.kalaur.topmovieapp.data.local.entities.MovieEntity
import ihor.kalaur.topmovieapp.data.remote.response.MovieDto
import ihor.kalaur.topmovieapp.model.Movie

fun MovieEntity.toMovie(): Movie {
    return Movie(
        movieId = movieId,
        movieName = movieName,
        dateOfProduction = dateOfProduction,
        partOfPosterUrl = partOfPosterUrl
    )
}

fun MovieDto.toMovie() : Movie {
    return Movie(
        movieId = movieId,
        movieName = movieName,
        dateOfProduction = dateOfProduction,
        partOfPosterUrl = partOfPosterUrl
    )
}

fun MovieDto.toEntity() : MovieEntity {
    return MovieEntity(
        movieId = movieId,
        movieName = movieName,
        dateOfProduction = dateOfProduction,
        partOfPosterUrl = partOfPosterUrl
    )
}


