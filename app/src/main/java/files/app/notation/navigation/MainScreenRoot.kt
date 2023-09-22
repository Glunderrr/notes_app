package files.app.notation.navigation

import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.TransformOrigin
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import files.app.notation.layouts.MainScreen
import files.app.notation.layouts.SearchScreen
import files.app.notation.layouts.TextView

@Composable
fun MainScreenRoot() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.Main.route) {
        composable(
            Screens.Main.route,
            enterTransition = {
                scaleIn(initialScale = 1f)
            }
        ) {
            MainScreen(navController, settings)
        }
        composable(
            Screens.Search.route,
            enterTransition = {
                scaleIn(
                    transformOrigin = TransformOrigin(0.8f, 0.05f),
                    initialScale = 0.9f
                )
            },
            exitTransition = {
                scaleOut(targetScale = 1f)
            }
        ) {
            SearchScreen(navController)
        }
        composable(
            Screens.Note.route,
            enterTransition = {
                scaleIn(initialScale = 0.8f)
            },
            exitTransition = {
                scaleOut(targetScale = 1f)
            }) {
            TextView(navController = navController, settings)
        }
    }
}

private val settings = listOf("Setting 1", "Setting 2", "Setting 3", "Setting 4", "Setting 5")
