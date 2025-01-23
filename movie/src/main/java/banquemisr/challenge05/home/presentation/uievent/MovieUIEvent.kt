package banquemisr.challenge05.home.presentation.uievent

import banquemisr.challenge05.home.presentation.MovieTab

sealed class MovieUIEvent {
    data  object LoadInitialHome : MovieUIEvent()
    data class OnTabClicked(val tabName: MovieTab) : MovieUIEvent()
    data class OnMovieClicked(val id: Int) : MovieUIEvent()
    data object Dismiss : MovieUIEvent()
}