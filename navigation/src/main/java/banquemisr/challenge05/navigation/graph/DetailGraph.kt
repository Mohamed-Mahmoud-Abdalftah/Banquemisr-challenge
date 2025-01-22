package banquemisr.challenge05.navigation.graph

import banquemisr.challenge05.navigation.utils.NavigationGraph

object DetailGraph : NavigationGraph {
    override val route: String
        get() = "detailgraph"
    override val startDestination: String
        get() = detailMain.destination(Unit)

    val detailMain = DetailMain
}