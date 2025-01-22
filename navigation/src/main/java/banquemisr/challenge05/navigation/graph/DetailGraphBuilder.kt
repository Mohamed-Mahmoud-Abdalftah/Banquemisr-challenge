package banquemisr.challenge05.navigation.graph

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation

@Immutable
data class DetailScreens(
    val detailMain: @Composable () -> Unit,

)

internal fun NavGraphBuilder.detailGraph(
    screens: DetailScreens
) {
    navigation(
        startDestination = DetailGraph.startDestination,
        route = DetailGraph.route
    ) {
        composable(DetailGraph.detailMain.route) {
            screens.detailMain()
        }
    }
}

