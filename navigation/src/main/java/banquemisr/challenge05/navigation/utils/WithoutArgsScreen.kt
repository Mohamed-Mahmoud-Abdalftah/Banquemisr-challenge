package banquemisr.challenge05.navigation.utils

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import banquemisr.challenge05.navigation.utils.DestinationRoute
import banquemisr.challenge05.navigation.utils.NavDestination
import banquemisr.challenge05.navigation.utils.NodeScreen

abstract class WithoutArgsScreen : NodeScreen, NavDestination<Unit> {
    override val arguments: List<NamedNavArgument> get() = emptyList()
    override fun objectParser(entry: NavBackStackEntry) {}
    override fun destination(arg: Unit): DestinationRoute = route
}