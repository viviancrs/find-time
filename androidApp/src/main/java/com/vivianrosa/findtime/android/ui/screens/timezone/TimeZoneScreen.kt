package com.vivianrosa.findtime.android.ui.screens.timezone

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vivianrosa.findtime.android.ui.screens.timezone.components.AnimatedSwipeDismiss
import com.vivianrosa.findtime.android.ui.screens.timezone.components.DismissBackground
import com.vivianrosa.findtime.android.ui.screens.timezone.components.LocalTimeCard
import com.vivianrosa.findtime.android.ui.screens.timezone.components.TimeCard
import com.vivianrosa.findtime.timezone.TimeZoneHelper
import com.vivianrosa.findtime.timezone.TimeZoneHelperImpl
import kotlinx.coroutines.delay

const val timeMillis = 1000 * 60L // 1 minute

@Composable
fun TimeZoneScreen(currentTimezoneStrings: SnapshotStateList<String>) {
    val timezoneHelper: TimeZoneHelper = TimeZoneHelperImpl()
    val listState = rememberLazyListState()

    Column(modifier = Modifier.fillMaxSize()) {
        var time by remember { mutableStateOf(timezoneHelper.currentTime()) }
        LaunchedEffect(Unit) {
            while (true) {
                time = timezoneHelper.currentTime()
                delay(timeMillis)
            }
        }

        LocalTimeCard(
            city = timezoneHelper.currentTimeZone(),
            time = time,
            date = timezoneHelper.getDate(timezoneHelper.currentTimeZone())
        )

        Spacer(modifier = Modifier.size(16.dp))

        LazyColumn(state = listState) {
            items(
                count = currentTimezoneStrings.size,
                key = { timezone -> timezone }) { index ->
                val timezoneString = currentTimezoneStrings[index]
                AnimatedSwipeDismiss(
                    item = timezoneString,
                    background = {
                        DismissBackground()
                    },
                    content = {
                        TimeCard(
                            timezone = timezoneString,
                            hours = timezoneHelper.hoursFromTimeZone(timezoneString),
                            time = timezoneHelper.getTime(timezoneString),
                            date = timezoneHelper.getDate(timezoneString)
                        )
                    },
                    onDismiss = { zone ->
                        if (currentTimezoneStrings.contains(zone)) {
                            currentTimezoneStrings.remove(zone)
                        }
                    }
                )
            }
        }
    }
}