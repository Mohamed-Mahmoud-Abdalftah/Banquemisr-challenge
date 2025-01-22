package banquemisr.challenge05.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import banquemisr.challenge05.navigation.graph.DetailScreens
import banquemisr.challenge05.navigation.graph.detailGraph
import banquemisr.challenge05.navigation.screens.Detail
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AppNavigation(
    navigator: Navigator,
    homeScreen: @Composable () -> Unit,
    detailScreen: @Composable (Boolean) -> Unit,
    detailScreenWithGraph: DetailScreens
) {
    val navController = rememberNavController()

    LaunchedEffect(Unit) {
        navigator.actions.collectLatest { action ->
            when (action) {
                Navigator.Action.Back -> navController.navigateUp()
                is Navigator.Action.Navigate -> navController.navigate(
                    route = action.destination,
                    builder = action.navOptions
                    )
             }
        }
    }

    NavHost(navController, startDestination = Destination.home.route) {
        detailGraph(detailScreenWithGraph)
        composable(Destination.home.route) {
            homeScreen()
        }

        composable(Destination.detail.route, Destination.detail.arguments) {
            val isOpenSheet = Detail.objectParser(it)
            detailScreen(isOpenSheet)
        }
    }

}
