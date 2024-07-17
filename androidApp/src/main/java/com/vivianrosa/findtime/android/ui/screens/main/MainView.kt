package com.vivianrosa.findtime.android.ui.screens.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.vivianrosa.findtime.android.MyApplicationTheme
import com.vivianrosa.findtime.android.ui.EmptyComposable
import com.vivianrosa.findtime.android.ui.components.dialogs.AddTimeZoneDialog
import com.vivianrosa.findtime.android.ui.screens.timezone.TimeZoneScreen
import com.vivianrosa.findtime.android.ui.screens.findmeetingtime.FindMeetingTimeScreen
import com.vivianrosa.findtime.android.ui.topBarFun

sealed class Screen(val title: String) {
    data object TimeZoneScreen : Screen("Timezones")
    data object FindTimeScreen : Screen("Find Time")
}

data class BottomItem(
    val route: String,
    val icon: ImageVector,
    val iconContentDescription: String,
)

val bottomNavigationItems = listOf(
    BottomItem(
        route = Screen.TimeZoneScreen.title,
        icon = Icons.Filled.Language,
        iconContentDescription = "Timezones"
    ),
    BottomItem(
        route = Screen.FindTimeScreen.title,
        icon = Icons.Filled.Place,
        iconContentDescription = "Find Time"
    )
)

@Composable
fun MainView(actionBarFun: topBarFun = { EmptyComposable() }) {
    val showAddDialog = remember { mutableStateOf(false) }
    val currentTimezoneStrings = remember { SnapshotStateList<String>() }
    val selectedIndex = remember { mutableIntStateOf(0) }

    MyApplicationTheme {
        Scaffold(
            topBar = { actionBarFun(selectedIndex.intValue) },
            floatingActionButton = {
                if (selectedIndex.intValue == 0) {
                    AddTimeZoneButton {
                        showAddDialog.value = true
                    }
                }
            },
            bottomBar = {
                BottomBar(
                    selectedIndex = selectedIndex.intValue,
                    onClick = { newIndex -> selectedIndex.intValue = newIndex }
                )
            }
        ) { padding ->
            Box(modifier = Modifier.padding(padding)) {
                if (showAddDialog.value) {
                    AddTimeZoneDialog(
                        onAdd = { newTimeZones ->
                            showAddDialog.value = false
                            for (zone in newTimeZones) {
                                if (!currentTimezoneStrings.contains(zone)) {
                                    currentTimezoneStrings.add(zone)
                                }
                            }
                        },
                        onDismiss = { showAddDialog.value = false }
                    )
                }
                when (selectedIndex.intValue) {
                    0 -> TimeZoneScreen(currentTimezoneStrings)
                    1 -> FindMeetingTimeScreen(currentTimezoneStrings)
                }
            }
        }
    }
}

@Composable
private fun AddTimeZoneButton(onClick: () -> Unit) {
    FloatingActionButton(
        modifier = Modifier.padding(16.dp),
        shape = FloatingActionButtonDefaults.largeShape,
        containerColor = MaterialTheme.colorScheme.secondary,
        onClick = onClick
    ) {
        Icon(imageVector = Icons.Default.Add, contentDescription = "Add Timezone")
    }
}

@Composable
private fun BottomBar(selectedIndex: Int, onClick: (selectedIndex: Int) -> Unit) {
    NavigationBar(containerColor = MaterialTheme.colorScheme.primary) {
        bottomNavigationItems.forEachIndexed { i, bottomNavigationItem ->
            NavigationBarItem(
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.White,
                    selectedTextColor = Color.White,
                    unselectedIconColor = Color.Black,
                    unselectedTextColor = Color.Black,
                    indicatorColor = MaterialTheme.colorScheme.primary
                ),
                label = {
                    Text(bottomNavigationItem.route, style = MaterialTheme.typography.bodyMedium)
                },
                icon = {
                    Icon(
                        imageVector = bottomNavigationItem.icon,
                        contentDescription = bottomNavigationItem.iconContentDescription
                    )
                },
                selected = selectedIndex == i,
                onClick = { onClick(i) }
            )
        }
    }
}