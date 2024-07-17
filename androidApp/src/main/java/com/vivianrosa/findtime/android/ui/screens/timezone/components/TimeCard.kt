package com.vivianrosa.findtime.android.ui.screens.timezone.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TimeCard(timezone: String, hours: Double, time: String, date: String) {
    Box(
        modifier = Modifier
//        .fillMaxSize()
            .height(120.dp)
            .background(Color.White)
            .padding(8.dp)
    ) {
        Card(
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(1.dp, Color.Gray),
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .background(color = Color.White)
                    .padding(16.dp)
            ) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    ZoneColumn(timezone = timezone, hours = hours)
                    Spacer(modifier = Modifier.weight(1.0f))
                    DateTimeColumn(date = date, time = time)
                }
            }
        }
    }
}

@Composable
private fun ZoneColumn(timezone: String, hours: Double) {
    Column(horizontalAlignment = Alignment.Start) {
        Text(
            timezone,
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.weight(1.0f))
        Row {
            Text(hours.toString(), style = MaterialTheme.typography.labelSmall)
            Text(" hours from local", style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
private fun DateTimeColumn(date: String, time: String) {
    Column(horizontalAlignment = Alignment.End) {
        Text(
            text = time, style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.weight(1.0f))
        Text(text = date, style = MaterialTheme.typography.bodySmall)
    }
}

@Preview
@Composable
fun Preview() {
    TimeCard("BR", hours = 12.0, time = "16:20", date = "Saturday, July 8")
}