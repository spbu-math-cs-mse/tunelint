package org.goalteam.tunelint.view

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import org.goalteam.tunelint.viewmodel.RedactorScreenViewModel

@Composable
fun MusicSheetView(vm: RedactorScreenViewModel) {
    // TODO: create MusicSheetView
    Column {
        Text(text = "this is sheet view stub")
        Text(text = "change event text: ${vm.state.collectAsState().value}")
        Button(onClick = { vm.interactionEvent() }) {
            Text("click me")
        }
    }
}
