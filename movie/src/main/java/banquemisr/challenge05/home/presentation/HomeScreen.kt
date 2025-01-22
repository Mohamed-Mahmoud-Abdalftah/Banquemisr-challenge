package banquemisr.challenge05.home.presentation

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
    val pagingItems = viewModel.moviesData.collectAsLazyPagingItems()
    val tabs = listOf(MovieTab.NOW_PLAYING, MovieTab.POPULAR, MovieTab.UPCOMING)
    var selectedTab by remember { mutableStateOf(MovieTab.NOW_PLAYING) }

    LaunchedEffect(Unit) {
        viewModel.onEvent(MovieUIEvent.LoadInitialHome)
    }

    Column {
        // TabRow for category selection
        TabRow(selectedTabIndex = tabs.indexOf(selectedTab)) {
            tabs.forEach { tab ->
                Tab(
                    selected = selectedTab == tab,
                    onClick = {
                        selectedTab = tab
                        viewModel.onEvent(MovieUIEvent.OnTabClicked(tab))  // Use tab name
                    },
                    text = { Text(tab.name.replace("_", " ")) }
                )
            }
        }

        when {
            state.isLoading -> {
                LoadingComponent()
            }

            state.error != null -> {
                Log.d("TAG", "HomeScreen: state.error = ${state.error}")
                ErrorComponent(error = state.error)
            }

            else -> {
                ListContent(pagingItems)
            }
        }
    }

    BackHandler(enabled = true) {
        viewModel.onEvent(MovieUIEvent.Dismiss)
    }
}
