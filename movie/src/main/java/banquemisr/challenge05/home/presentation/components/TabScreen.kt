package banquemisr.challenge05.home.presentation.components

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.paging.compose.LazyPagingItems
import banquemisr.challenge05.core.components.ErrorComponent
import banquemisr.challenge05.core.components.LoadingComponent
import banquemisr.challenge05.domain.models.ListMovies
import banquemisr.challenge05.home.presentation.state.MovieUIState

@SuppressLint("UnrememberedMutableState")
@Composable
fun TabScreen(state: MovieUIState, pagingItems: LazyPagingItems<ListMovies>) {
    val tabs = listOf("Tab 1", "Tab 2", "Tab 3")
    var selectedTabIndex by mutableStateOf(0)

    Column(modifier = Modifier.fillMaxSize()) {
        // TabRow for tabs
        TabRow(
            selectedTabIndex = selectedTabIndex,
            contentColor = Color.Black, // Tab text color
            indicator = { tabPositions ->
                // Custom Tab Indicator
                Box(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex])
                ) {
                    CircularProgressIndicator()
                }
            }
        ) {
            // Tab content
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    text = { Text(text = title) }
                )
            }
        }

        // Content based on selected tab
        when (selectedTabIndex) {
            0 -> TabContent( state,pagingItems)
            1 -> TabContent(state,pagingItems)
            2 -> TabContent(state,pagingItems)
        }
    }
}

@Composable
fun TabContent(state: MovieUIState, pagingItems: LazyPagingItems<ListMovies>) {
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
}

