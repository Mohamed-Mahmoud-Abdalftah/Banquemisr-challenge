package banquemisr.challenge05.home.presentation

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import banquemisr.challenge05.core.components.ErrorComponent
import banquemisr.challenge05.core.components.LoadingComponent
import banquemisr.challenge05.home.presentation.components.ListContent
import banquemisr.challenge05.home.presentation.uievent.MovieUIEvent


@Composable
fun HomeScreen() {
    val viewModel: HomeViewModel = hiltViewModel()
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    // Collect the PagingData as LazyPagingItems
    val pagingItems = viewModel.moviesData.collectAsLazyPagingItems()

    LaunchedEffect(Unit) {
        viewModel.onEvent(MovieUIEvent.LoadInitialHome)
    }


    when {
        state.isLoading -> {
            LoadingComponent()
        }

        state.error != null -> {
            Log.d("TAG", "HomeScreen: state.error = ${state.error}")
            ErrorComponent(error = state.error)
        }

        state.moviesData != null -> {
            ListContent(pagingItems)
        }

        else -> {
            Log.d("TAG", "HomeScreen: else")
        }
    }

    BackHandler(enabled = true) {
        viewModel.onEvent(MovieUIEvent.Dismiss)
    }
}
