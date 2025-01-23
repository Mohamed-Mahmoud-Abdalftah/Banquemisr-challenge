package banquemisr.challenge05.data.mapper

import banquemisr.challenge05.data.local.entities.NowPlayingMovie
import banquemisr.challenge05.data.local.entities.PopularMovie
import banquemisr.challenge05.data.local.entities.UpcomingMovie
import banquemisr.challenge05.domain.models.ListMovies


// Mapping from NowPlayingMovie entity to domain Movie model
fun NowPlayingMovie.toDomain(): ListMovies {
    return ListMovies(
        id = this.id,
        title = this.title,
        releaseDate = this.releaseDate,
        posterPath = this.image,
        overview = this.overview
    )
}

fun ListMovies.toNowPlayingEntity(): NowPlayingMovie {
    return NowPlayingMovie(
        id = this.id,
        title = this.title,
        releaseDate = this.releaseDate,
        image = this.posterPath,
        overview = this.overview
    )
}

// Mapping from PopularMovie entity to domain Movie model
fun PopularMovie.toDomain(): ListMovies {
    return ListMovies(
        id = this.id,
        title = this.title,
        releaseDate = this.releaseDate,
        posterPath = this.image,
        overview = this.overview
    )
}

fun ListMovies.toPopularEntity(): PopularMovie {
    return PopularMovie(
        id = this.id,
        title = this.title,
        releaseDate = this.releaseDate,
        image = this.posterPath,
        overview = this.overview
    )
}

// Mapping from UpcomingMovie entity to domain Movie model
fun UpcomingMovie.toDomain(): ListMovies {
    return ListMovies(
        id = this.id,
        title = this.title,
        releaseDate = this.releaseDate,
        posterPath = this.image,
        overview = this.overview
    )
}

fun ListMovies.toUpcomingEntity(): UpcomingMovie {
    return UpcomingMovie(
        id = this.id,
        title = this.title,
        releaseDate = this.releaseDate,
        image = this.posterPath,
        overview = this.overview
    )
}
