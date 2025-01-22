package banquemisr.challenge05.data.mapper

import banquemisr.challenge05.data.model.MovieDTO
import banquemisr.challenge05.data.model.MovieResponse
import banquemisr.challenge05.domain.models.ListData
import banquemisr.challenge05.domain.models.ListMovies



fun MovieResponse.mapToListData(): ListData {
    val domainList = this.results.map { it.toDomainProduct() }
    return ListData(
        movieList = domainList,
        totalPages = this.totalPages,
        totalResults = this.totalResults
    )
}

private fun MovieDTO.toDomainProduct(): ListMovies {
    return ListMovies(
        id = this.id,
        title = this.title,
        releaseDate = this.releaseDate,
        posterPath = this.posterPath,
        overview = this.overview
    )
}