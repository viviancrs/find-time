package com.vivianrosa.findtime.compose.ui.screens.findmeetingtime

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vivianrosa.findtime.compose.ui.components.dialogs.MeetingDialog
import com.vivianrosa.findtime.compose.ui.components.dialogs.isSelected
import com.vivianrosa.findtime.compose.ui.screens.findmeetingtime.components.NumberTimeCard
import com.vivianrosa.findtime.timezone.TimeZoneHelper
import com.vivianrosa.findtime.timezone.TimeZoneHelperImpl

@Composable
fun FindMeetingTimeScreen(timezoneStrings: List<String>) {
    val listState = rememberLazyListState()
    val startTime = remember { mutableIntStateOf(8) }
    val endTime = remember { mutableIntStateOf(17) }
    val selectedTimeZones = remember {
        val selected = SnapshotStateMap<Int, Boolean>()
        for (i in timezoneStrings.indices) selected[i] = true
        selected
    }

    val timezoneHelper: TimeZoneHelper = TimeZoneHelperImpl()
    val showMeetingDialog = remember { mutableStateOf(false) }
    val meetingHours = remember { SnapshotStateList<Int>() }

    if (showMeetingDialog.value) {
        MeetingDialog(
            hours = meetingHours,
            onDismiss = { showMeetingDialog.value = false }
        )
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.size(16.dp))
        Header()
        Spacer(modifier = Modifier.size(16.dp))
        TimeRange(startTime = startTime, endTime = endTime)
        Spacer(modifier = Modifier.size(16.dp))
        TimeZones(
            listState = listState,
            timezoneStrings = timezoneStrings,
            selectedTimeZones = selectedTimeZones,
            onClickSearch = {
                meetingHours.clear()
                meetingHours.addAll(
                    timezoneHelper.search(
                        startHour = startTime.intValue,
                        endHour = endTime.intValue,
                        timeZoneStrings = getSelectedTimeZones(timezoneStrings, selectedTimeZones)
                    )
                )
                showMeetingDialog.value = true
            }
        )
    }
}

@Composable
fun ColumnScope.TimeZones(
    listState: LazyListState,
    timezoneStrings: List<String>,
    selectedTimeZones: SnapshotStateMap<Int, Boolean>,
    onClickSearch: () -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .weight(0.6F)
            .fillMaxWidth(),
        contentPadding = PaddingValues(16.dp),
        state = listState
    ) {
        itemsIndexed(timezoneStrings) { i, timezone ->
            Surface(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Checkbox(
                        checked = isSelected(selectedTimeZones, i),
                        onCheckedChange = {
                            selectedTimeZones[i] = it
                        }
                    )
                    Text(timezone, modifier = Modifier.align(Alignment.CenterVertically))
                }
            }
        }
    }
    Spacer(modifier = Modifier.weight(0.1f))

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .weight(0.2f)
            .wrapContentWidth(Alignment.CenterHorizontally)
            .padding(start = 4.dp, end = 4.dp)
    ) {
        OutlinedButton(
            colors = ButtonDefaults.outlinedButtonColors(containerColor = MaterialTheme.colorScheme.primary),
            onClick = { onClickSearch() }
        ) { Text("Search") }
    }

    Spacer(modifier = Modifier.size(16.dp))
}

@Composable
private fun Header() {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.CenterHorizontally),
        text = "Time Range",
        style = MaterialTheme.typography.headlineSmall.copy(color = MaterialTheme.colorScheme.onBackground)
    )
}

@Composable
private fun TimeRange(startTime: MutableState<Int>, endTime: MutableState<Int>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 4.dp, end = 4.dp)
            .wrapContentWidth(Alignment.CenterHorizontally)
    ) {
        Spacer(modifier = Modifier.size(16.dp))
        NumberTimeCard(label = "Start", hour = startTime)
        Spacer(modifier = Modifier.size(32.dp))
        NumberTimeCard(label = "End", hour = endTime)
    }

    Spacer(modifier = Modifier.size(16.dp))

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 4.dp, end = 4.dp)
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally),
            text = "Time Zones",
            style = MaterialTheme.typography.headlineSmall.copy(color = MaterialTheme.colorScheme.onBackground)
        )
    }
}

private fun getSelectedTimeZones(
    timezoneStrings: List<String>,
    selectedStates: Map<Int, Boolean>,
): List<String> {
    val selectedTimezones = mutableListOf<String>()
    selectedStates.keys.map { selectedState ->
        val timezone = timezoneStrings[selectedState]
        if (isSelected(selectedStates, selectedState) && !selectedTimezones.contains(timezone)) {
            selectedTimezones.add(timezone)
        }
    }
    return selectedTimezones
}