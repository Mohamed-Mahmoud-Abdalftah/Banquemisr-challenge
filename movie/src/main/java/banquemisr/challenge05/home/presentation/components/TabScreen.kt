package banquemisr.challenge05.home.presentation.components

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import banquemisr.challenge05.core.components.ErrorComponent
import banquemisr.challenge05.core.components.LoadingComponent
import banquemisr.challenge05.domain.models.ListMovies
import banquemisr.challenge05.home.presentation.state.MovieUIState

@Composable
fun TabGridExample() {
    // Tab titles
    val tabs = listOf("Fruits", "Vegetables", "Dairy")

    // Track selected tab index
    var selectedTabIndex by remember { mutableStateOf(0) }

    // Data sets for each tab
    val data = listOf(
        listOf("Apple", "Banana", "Cherry", "Grapes", "Mango"),
        listOf("Carrot", "Potato", "Broccoli", "Spinach", "Tomato"),
        listOf("Milk", "Cheese", "Butter", "Yogurt", "Cream")
    )

    Column {
        // Tab Row
        TabRow(selectedTabIndex = selectedTabIndex) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    text = { Text(title) }
                )
            }
        }

        // Grid that displays data based on selected tab
        LazyVerticalGrid(
            columns = GridCells.Fixed(2), // 2 columns
            modifier = Modifier.fillMaxSize()
        ) {
            items(data[selectedTabIndex]) { item ->
                Card(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                ) {
                    Text(
                        text = item,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
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

@Preview(showBackground = true)
@Composable
fun PreviewTabGridExample() {
    TabGridExample()
}