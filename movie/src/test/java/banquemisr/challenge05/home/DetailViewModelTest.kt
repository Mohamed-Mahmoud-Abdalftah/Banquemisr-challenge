package banquemisr.challenge05.home

import app.cash.turbine.test
import banquemisr.challenge05.core.navigation.NavigationService
import banquemisr.challenge05.detail.presentation.detailScreen.DetailViewModel
import banquemisr.challenge05.detail.presentation.detailScreen.uievent.DetailUIEvent
import banquemisr.challenge05.domain.models.MovieDetail
import banquemisr.challenge05.domain.useCase.MovieDetailsUseCase
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class DetailViewModelTest {

    private val movieDetailsUseCase: MovieDetailsUseCase = mockk()
    private val navigator: NavigationService = mockk(relaxed = true)

    private lateinit var viewModel: DetailViewModel

    @Before
    fun setup() {
        viewModel = DetailViewModel(
            movieDetailsUseCase = movieDetailsUseCase,
            navigator = navigator
        )
    }

    @Test
    fun `loadMovieDetails updates UI state when successful`() = runTest {
        // Mock successful response from use case
        val movieDetail = MovieDetail(
            id = 1, title = "Title",
            adult = true,
            releaseDate ="22/3/2023",
            revenue = 0,
            runtime = 0,
            overview = "Overview",
            homepage = "",
            posterPath = ""
        )
        coEvery { movieDetailsUseCase.execute(1) } returns movieDetail

        // Trigger event
        viewModel.handleEvent(DetailUIEvent.LoadInitialDetail(1))

        // Collect the UI state and assert the expected behavior
        viewModel.uiState.test {
            val initialState = awaitItem()
            assert(initialState.isLoading)  // Check that loading state is true before data is fetched

            val successState = awaitItem()
            assert(!successState.isLoading)  // Check that loading state is false after data is fetched
            assert(successState.moviesData == movieDetail)  // Ensure correct movie data is set
        }
    }

    @Test
    fun `loadMovieDetails updates UI state with error when failed`() = runTest {
        // Mock error response from use case
        val exception = Exception("Failed to fetch movie details")
        coEvery { movieDetailsUseCase.execute(1) } throws exception

        // Trigger event
        viewModel.handleEvent(DetailUIEvent.LoadInitialDetail(1))

        // Collect the UI state and assert the expected behavior
        viewModel.uiState.test {
            val initialState = awaitItem()
            assert(initialState.isLoading)  // Check that loading state is true before data is fetched

            val errorState = awaitItem()
            assert(!errorState.isLoading)  // Check that loading state is false after failure
            assert(errorState.error is Exception)  // Ensure the error state is set
        }
    }

    @Test
    fun `dismiss event calls goBack`() = runTest {
        // Trigger dismiss event
        viewModel.handleEvent(DetailUIEvent.Dismiss)

        // Verify that navigator.goBack() was called
        verify { navigator.goBack() }
    }
}
