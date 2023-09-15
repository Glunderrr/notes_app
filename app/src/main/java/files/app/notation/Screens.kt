package files.app.notation

sealed class Screens(val route: String){
    object Search: Screens("search")
    object Main: Screens("main")
    object Note: Screens("note")
}
