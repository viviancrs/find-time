package com.vivianrosa.findtime.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.vivianrosa.findtime.android.ui.MainView

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainView {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary),
                    title = {
                        when (it) {
                            0 -> Text(text = stringResource(R.string.world_clocks))
                            else -> Text(text = stringResource(R.string.find_meeting))
                        }
                    })
            }
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme { }
}
