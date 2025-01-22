package banquemisr.challenge05.domain.models


data class ListData(
    val movieList: List<ListMovies>?,
    val totalResults: Int?,
    val totalPages: Int?
)


data class ListMovies(
    val id: Int,
    val title: String,
    val releaseDate: String,
    val posterPath: String,
    val overview: String,
)