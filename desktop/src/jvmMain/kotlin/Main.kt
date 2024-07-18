import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.vivianrosa.findtime.compose.ui.MyApplicationTheme
import com.vivianrosa.findtime.compose.ui.screens.main.MainView

fun main() {
    application {
        val windowState = rememberWindowState(position = WindowPosition(alignment = Alignment.Center))

        Window(
            onCloseRequest = ::exitApplication,
            state = windowState,
            title = "Timezone"
        ) {
            Surface(modifier = Modifier.fillMaxSize()) {
                MyApplicationTheme {
                    MainView()
                }
            }
        }
    }
}