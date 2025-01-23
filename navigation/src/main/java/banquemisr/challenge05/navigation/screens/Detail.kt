package banquemisr.challenge05.navigation.screens

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import androidx.navigation.navArgument
import banquemisr.challenge05.navigation.utils.ArgsScreen
import banquemisr.challenge05.navigation.utils.DestinationRoute
import kotlin.collections.List

object Detail : ArgsScreen<Int> {
    override val route: String = "detail/{id}"
    override val arguments: List<NamedNavArgument> =
        listOf(navArgument("id") { type = NavType.IntType})


    override fun objectParser(entry: NavBackStackEntry): Int =
        entry.arguments?.getInt("id") ?: 0

    override fun destination(arg: Int): DestinationRoute = "detail/$arg"
}

