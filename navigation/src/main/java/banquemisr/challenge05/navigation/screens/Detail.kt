package banquemisr.challenge05.navigation.screens

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import androidx.navigation.navArgument
import banquemisr.challenge05.navigation.utils.ArgsScreen
import banquemisr.challenge05.navigation.utils.DestinationRoute
import kotlin.collections.List

object Detail : ArgsScreen<Boolean> {
    override val route: String = "detail/{isOpenSheet}"
    override val arguments: List<NamedNavArgument> =
        listOf(navArgument("isOpenSheet") { type = NavType.BoolType })

    override fun objectParser(entry: NavBackStackEntry): Boolean =
        entry.arguments?.getBoolean("isOpenSheet") ?: false

    override fun destination(arg: Boolean): DestinationRoute = "detail/$arg"
}
