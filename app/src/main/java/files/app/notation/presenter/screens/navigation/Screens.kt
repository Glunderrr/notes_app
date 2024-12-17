package files.app.notation.presenter.screens.navigation

sealed class Screens(val route: String) {
    object Search : Screens("search")
    object Main : Screens("main")
    object Note : Screens("note")
}
