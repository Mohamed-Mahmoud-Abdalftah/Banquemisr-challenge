package banquemisr.challenge05.home.presentation.uievent

import banquemisr.challenge05.home.presentation.MovieTab

sealed class MovieUIEvent {
    data  object LoadInitialHome : MovieUIEvent()
    data class OnTabClicked(val tabName: MovieTab) : MovieUIEvent()
    data   object OnMovieClicked : MovieUIEvent()
    data object Dismiss : MovieUIEvent()
}