package banquemisr.challenge05.home.presentation.uievent


sealed class MovieUIEvent {
    data object OnMovieClicked : MovieUIEvent()
    data object LoadInitialHome : MovieUIEvent()
    data object OnTabClicked : MovieUIEvent()
    data object Dismiss : MovieUIEvent()
}