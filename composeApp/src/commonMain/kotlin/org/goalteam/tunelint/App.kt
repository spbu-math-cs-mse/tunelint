package org.goalteam.tunelint

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.goalteam.tunelint.model.core.impl.MelodyImpl
import org.goalteam.tunelint.view.MusicSheetView
import org.goalteam.tunelint.viewmodel.RedactorScreenViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        // TODO: create main view
        val container = MelodyImpl("", mutableListOf())
        val vm = RedactorScreenViewModel(container)
        Column {
            Text(text = "this is main ui stub")
            Box(
                modifier =
                    Modifier
                        .border(
                            width = 5.dp,
                            color = Color.Black,
                        ).padding(all = 50.dp),
            ) {
                MusicSheetView(vm)
            }
        }
    }
}
