package com.vivianrosa.findtime.android.ui.screens.timezone.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DismissBackground() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .height(50.dp)
            .background(Color.Red)
            .padding(start = 20.dp, end = 20.dp)
    ) {
        val alpha = 1f
        Icon(
            Icons.Filled.Delete,
            contentDescription = "Delete",
            modifier = Modifier.align(Alignment.CenterEnd),
            tint = Color.White.copy(alpha = alpha)
        )
    }
}