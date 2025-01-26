package banquemisr.challenge05.home

import androidx.paging.PagingData
import app.cash.turbine.test
import banquemisr.challenge05.core.navigation.NavigationService
import banquemisr.challenge05.domain.models.ListMovies
import banquemisr.challenge05.domain.useCase.GetPlayingMoviesUseCase
import banquemisr.challenge05.domain.useCase.GetPopularMoviesUseCase
import banquemisr.challenge05.domain.useCase.GetUpcomingMoviesUseCase
import banquemisr.challenge05.home.presentation.HomeViewModel
import banquemisr.challenge05.home.presentation.uievent.MovieUIEvent
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeViewModelTest {
    private val getPlayingMoviesUseCase: GetPlayingMoviesUseCase = mockk()
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase = mockk()
    private val getUpcomingMoviesUseCase: GetUpcomingMoviesUseCase = mockk()
    private val navigator: NavigationService = mockk(relaxed = true)

    private lateinit var viewModel: HomeViewModel

    @Before
    fun setup() {
        viewModel = HomeViewModel(
            getPlayingMoviesUseCase = getPlayingMoviesUseCase,
            getPopularMoviesUseCase = getPopularMoviesUseCase,
            getUpcomingMoviesUseCase = getUpcomingMoviesUseCase,
            navigator = navigator
        )
    }
    @Test
    fun `getPlayingMovies emits data`() = runTest {
        // Mock the UseCase
        val mockPagingData = PagingData.from(listOf(ListMovies(
            1, "Movie 1",
            releaseDate = "21/1/2024",
            posterPath = "",
            overview = ""
        ), ListMovies(
            2, "Movie 2",
            releaseDate = "21/1/2024",
            posterPath = "",
            overview = ""
        )))

        coEvery { getPlayingMoviesUseCase.execute() } returns flowOf(mockPagingData)

        // Trigger the event
        viewModel.handleEvent(MovieUIEvent.LoadInitialHome)

        // Collect and verify the emitted PagingData
        viewModel.moviesData.test {
            val emittedData = awaitItem()
            assert(emittedData != PagingData.empty<ListMovies>())
            cancelAndConsumeRemainingEvents()
        }
    }

}
