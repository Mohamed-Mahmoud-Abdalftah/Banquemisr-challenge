package banquemisr.challenge05.movie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import banquemisr.challenge05.detail.presentation.detailScreen.DetailScreen
import banquemisr.challenge05.home.presentation.HomeScreen
import banquemisr.challenge05.movie.ui.theme.BanquemisrChallenge05Theme
import banquemisr.challenge05.navigation.AppNavigation
import banquemisr.challenge05.navigation.Navigator
import banquemisr.challenge05.navigation.graph.DetailScreens
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SingleActivity : ComponentActivity() {

    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BanquemisrChallenge05Theme {
                AppNavigation(
                    navigator = navigator,
                    homeScreen = {
                        HomeScreen()
                    },
                    detailScreen = {
                      DetailScreen(it)
                    },
                    detailScreenWithGraph = DetailScreens(
                        detailMain = {
                            DetailScreen(it)
                        }
                    )
                )
            }
        }
    }
}
