package banquemisr.challenge05.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
interface MovieEntity {
    val id: Int
    val title: String
    val releaseDate: String
    val image: String
    val overview: String
}

@Entity(tableName = "now_playing_movies")
data class NowPlayingMovie(
    @PrimaryKey(autoGenerate = false) override val id: Int,
    override val title: String,
    override val releaseDate: String,
    override val image: String,
    override val overview: String
) : MovieEntity

@Entity(tableName = "upcoming_movies")
data class UpcomingMovie(
    @PrimaryKey(autoGenerate = false) override val id: Int,
    override val title: String,
    override val releaseDate: String,
    override val image: String,
    override val overview: String
) : MovieEntity

@Entity(tableName = "popular_movies")
data class PopularMovie(
    @PrimaryKey(autoGenerate = false) override val id: Int,
    override val title: String,
    override val releaseDate: String,
    override val image: String,
    override val overview: String
) : MovieEntity