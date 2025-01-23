package banquemisr.challenge05.navigation.graph

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import banquemisr.challenge05.navigation.utils.ArgsScreen
import banquemisr.challenge05.navigation.utils.DestinationRoute

object DetailMain: ArgsScreen<Unit> {
    override fun destination(arg: Unit): DestinationRoute = route

    override val route: String = "detail/{id}"
    override val arguments: List<NamedNavArgument> = emptyList()

    override fun objectParser(entry: NavBackStackEntry){}
}