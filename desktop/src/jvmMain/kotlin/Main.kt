import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyShortcut
import androidx.compose.ui.window.MenuBar
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.vivianrosa.findtime.compose.ui.MyApplicationTheme
import com.vivianrosa.findtime.compose.ui.screens.main.MainView

data class WindowInfo(val windowName: String, val windowState: WindowState)

fun main() {
    application {
        var initialized by remember { mutableStateOf(false) }
        var windowCount by remember { mutableStateOf(1) }
        val windowList = remember { SnapshotStateList<WindowInfo>() }

        if (!initialized) {
            windowList.add(
                WindowInfo(
                    "Timezone-${windowCount}", rememberWindowState(
                        position = WindowPosition(
                            Alignment.Center
                        )
                    )
                )
            )
            initialized = true
        }

        windowList.forEachIndexed { i, window ->
            Window(
                onCloseRequest = {
                    windowList.removeAt(i)
                },
                state = window.windowState,
                title = window.windowName
            ) {
                MenuBar {
                    Menu("File", mnemonic = 'F') {
                        val nextWindowState = rememberWindowState()
                        Item(
                            text = "New",
                            onClick = {
                                windowCount++
                                windowList.add(
                                    WindowInfo(
                                        "Timezone-${windowCount}",
                                        nextWindowState
                                    )
                                )
                            },
                            shortcut = KeyShortcut(Key.N, ctrl = true)
                        )
                        Item(
                            text = "Open",
                            onClick = { },
                            shortcut = KeyShortcut(Key.O, ctrl = true)
                        )
                        Item(
                            text = "Close",
                            onClick = { windowList.removeAt(i) },
                            shortcut = KeyShortcut(Key.W, ctrl = true)
                        )
                        Item(
                            text = "Save",
                            onClick = { },
                            shortcut = KeyShortcut(Key.S, ctrl = true)
                        )
                        Separator()
                        Item(
                            text = "Exit",
                            onClick = { windowList.clear() },
                        )
                    }
                    Menu("Edit", mnemonic = 'E') {
                        Item(
                            text = "Cut",
                            onClick = { },
                            shortcut = KeyShortcut(Key.X, ctrl = true)
                        )
                        Item(
                            text = "Copy",
                            onClick = { },
                            shortcut = KeyShortcut(Key.C, ctrl = true)
                        )
                        Item(
                            text = "Paste",
                            onClick = { },
                            shortcut = KeyShortcut(Key.V, ctrl = true)
                        )
                    }
                }
                Surface(modifier = Modifier.fillMaxSize()) {
                    MyApplicationTheme {
                        MainView()
                    }
                }
            }
        }
    }
}

@Composable
private fun AppMenuBar() {

}