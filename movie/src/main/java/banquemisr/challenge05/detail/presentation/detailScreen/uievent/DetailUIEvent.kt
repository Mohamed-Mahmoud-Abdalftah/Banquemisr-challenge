package banquemisr.challenge05.detail.presentation.detailScreen.uievent


sealed class DetailUIEvent {
    data  class LoadInitialDetail(val id: Int) : DetailUIEvent()
    data object Dismiss : DetailUIEvent()
}