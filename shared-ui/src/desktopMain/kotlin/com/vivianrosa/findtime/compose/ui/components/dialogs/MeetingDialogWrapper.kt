package com.vivianrosa.findtime.compose.ui.components.dialogs

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.Dp.Companion.Unspecified
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogWindow
import androidx.compose.ui.window.rememberDialogState
import com.vivianrosa.findtime.compose.ui.onDismissType

@Composable
actual fun MeetingDialogWrapper(onDismiss: onDismissType, content: @Composable () -> Unit) {
    DialogWindow(
        onCloseRequest = { onDismiss() },
        state = rememberDialogState(size = DpSize(width = 400.dp, height = Unspecified)),
        title = "Meetings",
        content = {
            content()
        }
    )
}