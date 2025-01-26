package banquemisr.challenge05.home.presentation

import android.util.Log
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import banquemisr.challenge05.core.navigation.NavigationService
import banquemisr.challenge05.core.presentation.StateAndEventViewModel
import banquemisr.challenge05.domain.models.ListMovies
import banquemisr.challenge05.domain.useCase.GetPlayingMoviesUseCase
import banquemisr.challenge05.domain.useCase.GetPopularMoviesUseCase
import banquemisr.challenge05.domain.useCase.GetUpcomingMoviesUseCase
import banquemisr.challenge05.home.presentation.state.MovieUIState
import banquemisr.challenge05.home.presentation.uievent.MovieUIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPlayingMoviesUseCase: GetPlayingMoviesUseCase,
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getUpcomingMoviesUseCase: GetUpcomingMoviesUseCase,
    private val navigator: NavigationService
) : StateAndEventViewModel<MovieUIState, MovieUIEvent>(MovieUIState()) {
    private val _moviesData = MutableStateFlow<PagingData<ListMovies>>(PagingData.empty())
    val moviesData: StateFlow<PagingData<ListMovies>> = _moviesData


    public override suspend fun handleEvent(event: MovieUIEvent) {
        when (event) {
            MovieUIEvent.LoadInitialHome -> {
                getPlayingMovies()  // Load default tab data
            }

            is MovieUIEvent.OnTabClicked -> {
                when (event.tabName) {
                    MovieTab.NOW_PLAYING -> getPlayingMovies()
                    MovieTab.POPULAR -> getPopularMovies()
                    MovieTab.UPCOMING -> getUpcomingMovies()
                }
            }

            is MovieUIEvent.OnMovieClicked -> {
                onMovieClicked(event.id)
            }

            is MovieUIEvent.Dismiss -> {
                handleBack()
            }
        }
    }


        private fun getPlayingMovies() {
        viewModelScope.launch {
            getPlayingMoviesUseCase.execute()
                .cachedIn(viewModelScope) // Cache PagingData in the ViewModel scope to retain during configuration changes
                .onStart {
                    updateUiState { copy(isLoading = true) } // Show loading before data is fetched
                }.catch { error ->
                    Log.e("TAG", "getPlayingMovies: error $error")
                    updateUiState {
                        copy(
                            isLoading = false, error = error
                        )
                    } // Show error if any occurs
                }.collectLatest { pagingData ->
                    _moviesData.emit(pagingData) // Use emit to ensure state flow updates correctly
                    updateUiState {
                        copy(
                            moviesData = pagingData, isLoading = false, error = null
                        )
                    }
                }
        }
    }




    private fun getPopularMovies() {
        viewModelScope.launch {
            getPopularMoviesUseCase.execute()
                .cachedIn(viewModelScope) // Cache PagingData in the ViewModel scope to retain during configuration changes
                .onStart {
                    updateUiState { copy(isLoading = true) } // Show loading before data is fetched
                }.catch { error ->
                    Log.e("TAG", "getPlayingMovies: error $error")
                    updateUiState {
                        copy(
                            isLoading = false, error = error
                        )
                    } // Show error if any occurs
                }.collectLatest { pagingData ->
                    _moviesData.emit(pagingData) // Use emit to ensure state flow updates correctly
                    updateUiState {
                        copy(
                            moviesData = pagingData, isLoading = false, error = null
                        )
                    }
                }
        }
    }

    private fun getUpcomingMovies() {
        viewModelScope.launch {
            getUpcomingMoviesUseCase.execute()
                .cachedIn(viewModelScope) // Cache PagingData in the ViewModel scope to retain during configuration changes
                .onStart {
                    updateUiState { copy(isLoading = true) } // Show loading before data is fetched
                }.catch { error ->
                    updateUiState {
                        copy(
                            isLoading = false, error = error
                        )
                    } // Show error if any occurs
                }.collectLatest { pagingData ->
                    _moviesData.emit(pagingData) // Use emit to ensure state flow updates correctly
                    updateUiState {
                        copy(
                            moviesData = pagingData, isLoading = false, error = null
                        )
                    }
                }
        }
    }






    // Route with Detail Graph
    private fun onMovieClicked(id: Int) {
        navigator.navigateTo("detail/$id") {
            launchSingleTop = true
            restoreState = true
        }
    }


    private fun handleBack() {
        navigator.goBack()
    }

}