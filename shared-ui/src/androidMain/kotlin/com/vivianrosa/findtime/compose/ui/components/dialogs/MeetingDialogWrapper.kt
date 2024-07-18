package com.vivianrosa.findtime.compose.ui.components.dialogs

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Dialog
import com.vivianrosa.findtime.compose.ui.onDismissType

@Composable
actual fun MeetingDialogWrapper(onDismiss: onDismissType, content: @Composable () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        content()
    }
}