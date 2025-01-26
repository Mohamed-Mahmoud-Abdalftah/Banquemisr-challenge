package banquemisr.challenge05.detail.presentation.detailScreen

import androidx.lifecycle.viewModelScope
import banquemisr.challenge05.core.navigation.NavigationService
import banquemisr.challenge05.core.presentation.StateAndEventViewModel
import banquemisr.challenge05.detail.presentation.detailScreen.state.DetailUIState
import banquemisr.challenge05.detail.presentation.detailScreen.uievent.DetailUIEvent
import banquemisr.challenge05.domain.useCase.MovieDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val movieDetailsUseCase: MovieDetailsUseCase,
    private val navigator: NavigationService
) : StateAndEventViewModel<DetailUIState, DetailUIEvent>(DetailUIState()) {


    public override suspend fun handleEvent(event: DetailUIEvent) {
        when (event) {

            is DetailUIEvent.Dismiss -> {
                handleBack()
            }

            is DetailUIEvent.LoadInitialDetail -> {
                loadMovieDetails(event.id)

            }
        }
    }


    private fun loadMovieDetails(movieId: Int) {
        updateUiState { copy(isLoading = true, error = null) }
        viewModelScope.launch {
            try {
                val movieDetail = movieDetailsUseCase.execute(movieId)
                updateUiState { copy(isLoading = false, moviesData = movieDetail) }
            } catch (e: Exception) {
                updateUiState { copy(isLoading = false, error = e) }
            }
        }
    }


    private fun handleBack() {
        navigator.goBack()
    }

}