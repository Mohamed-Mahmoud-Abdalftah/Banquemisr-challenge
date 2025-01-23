package banquemisr.challenge05.detail.presentation.detailScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import banquemisr.challenge05.core.components.ErrorComponent
import banquemisr.challenge05.core.components.LoadingComponent
import banquemisr.challenge05.detail.presentation.detailScreen.components.DescriptionDetail
import banquemisr.challenge05.detail.presentation.detailScreen.components.ImageDetail
import banquemisr.challenge05.detail.presentation.detailScreen.components.TitleDetail
import banquemisr.challenge05.detail.presentation.detailScreen.uievent.DetailUIEvent


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun DetailScreen(id: Int) {
    val viewModel: DetailViewModel = hiltViewModel()
    val state by viewModel.uiState.collectAsState()


    LaunchedEffect(true) {

        viewModel.onEvent(DetailUIEvent.LoadInitialDetail(id))
    }

    when {
        state.isLoading -> { LoadingComponent() }
        state.error != null -> { ErrorComponent(error = state.error) }
        state.moviesData != null -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(state = rememberScrollState())
            ) {
                ImageDetail(viewModel.uiState.value.moviesData?.posterPath,backClick = {
                    viewModel.onEvent(DetailUIEvent.Dismiss)
                })
                TitleDetail(viewModel.uiState.value.moviesData?.title)
                DescriptionDetail(viewModel.uiState.value.moviesData?.overview)

            }
        }
    }
}