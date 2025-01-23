package banquemisr.challenge05.navigation.graph

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import banquemisr.challenge05.navigation.screens.Detail

@Immutable
data class DetailScreens(
    val detailMain: @Composable (Int) -> Unit,
)

internal fun NavGraphBuilder.detailGraph(
    screens: DetailScreens
) {
    navigation(
        startDestination = DetailGraph.startDestination,
        route = DetailGraph.route
    ) {
        composable(DetailGraph.detailMain.route , DetailGraph.detailMain.arguments) {
            val id = Detail.objectParser(it)
            screens.detailMain(id)
        }
    }
}

