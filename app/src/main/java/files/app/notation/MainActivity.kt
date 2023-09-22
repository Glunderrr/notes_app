package files.app.notation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import files.app.notation.navigation.MainScreenRoot

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreenRoot()
        }
    }
}